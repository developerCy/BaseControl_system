package pay_moudle.core_pay.controller;

import com.sun.tools.corba.se.idl.constExpr.Or;
import common.Config;
import org.springframework.web.bind.annotation.RequestParam;
import pay_moudle.core_pay.alipay.alipayService.AliPayService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pay_moudle.core_pay.pay_json.Ali_pay_json;
import pojo.EtpsInfo;
import pojo.Order_info;
import pojo.Store_info;
import service.Ps_etps_service;
import service.Ps_orderinfo_service;
import service.Ps_store_service;
import service.Ps_userinfo_service;
import util.DateUtil;
import util.JsonUtil;
import util.OrderNoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/2/28 0028.
 */
@Controller
@RequestMapping("/pay")
public class Core_pay_controller {
    private static Logger log = Logger.getLogger(Core_pay_controller.class);
    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private Ps_store_service ps_store_service;
    @Autowired
    private Ps_etps_service ps_etps_service;
    @Autowired
    private Ps_userinfo_service ps_userinfo_service;
    @Autowired
    private Ps_orderinfo_service ps_orderinfo_service;

    /**
     * 支付宝扫码支付、微信刷卡支付（反扫）
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/trade_pay", method = RequestMethod.POST)
    public void tradePay(@RequestParam(value = "store_id") String store_id,
                         @RequestParam(value = "total_amount") String total_amount,
                         @RequestParam(value = "out_trade_no") String out_trade_no,
                         @RequestParam(value = "channel", required = false) String channel,
                         @RequestParam(value = "auth_code") String auth_code,
                         @RequestParam(value = "discountable_amount", required = false) String discountable_amount,
                         @RequestParam(value = "undiscountable_amount", required = false) String undiscountable_amount,
                         @RequestParam(value = "body", required = false) String body,
                         @RequestParam(value = "goods_detail", required = false) String goods_detail,
                         HttpServletResponse response) {
        try {
            Store_info store_info = new Store_info();
            store_info.setStore_id(store_id);
            List<Store_info> list = ps_store_service.select_store(store_info);
            if (list.isEmpty()) {
                JsonUtil.sendJson(response, Config.ERROR("门店不存在！"));
                return;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("iEtps_id", list.get(0).getiEtps_id());
            List<EtpsInfo> list1 = ps_etps_service.select_etpsinfo(map);
            if (list1.isEmpty()) {
                JsonUtil.sendJson(response, Config.ERROR("商户不存在！"));
                return;
            }
            String check_status = list1.get(0).getCheck_status();
            if (!"审核成功".equals(check_status)) {
                JsonUtil.sendJson(response, Config.ERROR("商户签约未通过审核"));
                return;
            }
            //订单初始化，入库
            create_init_order(store_id, total_amount, out_trade_no, list, list1, ps_orderinfo_service);
            JSONObject ali_pay_json = Ali_pay_json.package_ali_pay_json(out_trade_no, auth_code, total_amount, discountable_amount, undiscountable_amount, body, goods_detail, store_id, list.get(0).getAlipay_shop_id());
            log.info("支付宝_反扫支付_请求:" + ali_pay_json.toString());
            String client_alipay_response = aliPayService.Ali_trade_pay(ali_pay_json);
            log.info("支付宝_反扫支付_响应:" + client_alipay_response);
            JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response,Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 初始化订单
     *
     * @param store_id
     * @param total_amount
     * @param list
     * @param list1
     * @param ps_orderinfo_service
     */
    private static void create_init_order(@RequestParam(value = "store_id") String store_id,
                                          @RequestParam(value = "total_amount") String total_amount,
                                          @RequestParam(value = "out_trade_no") String out_trade_no,
                                          List<Store_info> list, List<EtpsInfo> list1, Ps_orderinfo_service ps_orderinfo_service) {
        Order_info order_info = new Order_info();
        order_info.setiAgent_id(list1.get(0).getiAgent_id());
        order_info.setiAgent_name(list1.get(0).getiAgent_name());
        order_info.setStore_id(store_id);
        order_info.setStore_name(list.get(0).getStore_name());
        order_info.setChannel("10");
        order_info.setEtps_name(list1.get(0).getEtps_name());
        order_info.setiEtps_id(list1.get(0).getiEtps_id());
        order_info.setTotal_amount(total_amount);
        order_info.setOut_trade_no(out_trade_no);
        order_info.setPay_status("0");
        order_info.setOrder_date(DateUtil.getTimestamp(DateUtil.SIMPLE));
        ps_orderinfo_service.insert_order(order_info);
    }

    /**
     * 支付宝
     *
     * @param request
     * @param response
     */
    @RequestMapping("/cancel")
    public void cancel(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONObject ali_pay_json = Ali_pay_json.package_ali_query_cancel_json(request);
            log.info("支付宝_撤单_请求:" + ali_pay_json.toString());
            String client_alipay_response = aliPayService.Ali_cancel(ali_pay_json);
            log.info("支付宝_撤单_响应:" + client_alipay_response);
            JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response,Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 支付宝
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/refund")
    public void refund(@RequestParam(value = "out_trade_no")String out_trade_no,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            Order_info order_info=new Order_info();
            order_info.setOut_trade_no(out_trade_no);
            List<Order_info> list=ps_orderinfo_service.select_etps_order_info(order_info);
            if(list.isEmpty()){
                JsonUtil.sendJson(response, Config.ERROR("该订单不存在！"));
                return;
            }
            String amount=list.get(0).getTotal_amount();
            JSONObject ali_pay_json = Ali_pay_json.package_ali_refund_json(out_trade_no,amount);
            log.info("支付宝_退款_请求:" + ali_pay_json.toString());
            String client_alipay_response = aliPayService.Ali_refund(ali_pay_json);
            log.info("支付宝_退款_响应:" + client_alipay_response);
            JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response,Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 支付宝订单查询接口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONObject ali_pay_json = Ali_pay_json.package_ali_query_cancel_json(request);
            log.info("支付宝_查询_请求:" + ali_pay_json.toString());
            String client_alipay_response = aliPayService.Ali_query(ali_pay_json);
            log.info("支付宝_查询_响应:" + client_alipay_response);
            JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response,Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 支付宝交易结果异步通知
     *
     * @param request
     * @param response
     */
    @RequestMapping("/pay_result")
    public void pay_result(HttpServletRequest request, HttpServletResponse response) {
        String trade_status = request.getParameter("trade_status");
        String total_amount = request.getParameter("total_amount");
        String receipt_amount = request.getParameter("receipt_amount");
        String trade_no = request.getParameter("trade_no");
        String buyer_pay_amount = request.getParameter("buyer_pay_amount");
        String gmt_amount = request.getParameter("gmt_amount");
        String invoice_amount = request.getParameter("invoice_amount");
        String point_amount = request.getParameter("point_amount");
        log.info("接受支付通知,交易状态：" + trade_status + ",交易金额：" + total_amount + "元,实收金额：" + receipt_amount + "元");
        if ("TRADE_SUCCESS".equals(trade_status)) {
            Order_info order_info = new Order_info();
            order_info.setPay_status("1");
            order_info.setTrade_no(trade_no);
            order_info.setReceipt_amount(receipt_amount);
            order_info.setBuyer_pay_amount(buyer_pay_amount);
            order_info.setGmt_amount(gmt_amount);
            order_info.setInvoice_amount(invoice_amount);
            order_info.setPoint_amount(point_amount);
            order_info.setPay_date(DateUtil.getTimestamp(DateUtil.SIMPLE));
            ps_orderinfo_service.update_pay_status(order_info);
        }
    }

    public static void main(String args[]) {
        System.out.println(OrderNoUtil.getOrderNo());
    }
}
