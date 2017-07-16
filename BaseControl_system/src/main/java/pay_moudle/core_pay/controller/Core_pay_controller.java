package pay_moudle.core_pay.controller;

import common.AliPayConfig;
import common.CommonConfig;
import org.springframework.web.bind.annotation.RequestParam;
import pay_moudle.core_pay.alipay.alipayService.AliPayService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pay_moudle.core_pay.pay_json.Ali_pay_json;
import util.JsonUtil;
import util.OrderNoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
    /**
     * 支付宝扫码支付、微信刷卡支付（反扫）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/trade_pay", method = RequestMethod.POST)
    public void tradePay(@RequestParam(value = "store_id")String store_id,
                         @RequestParam(value = "total_amount")String total_amount,
                         @RequestParam(value = "channel")String channel,
            HttpServletRequest request, HttpServletResponse response) {
        try {

                JSONObject ali_pay_json = Ali_pay_json.package_ali_pay_json(request, AliPayConfig.TRADE_PAY);
                log.info("支付宝_反扫支付_请求:" + ali_pay_json.toString());
            Map<String,String> map=new HashMap<String, String>();

            String client_alipay_response = aliPayService.Ali_trade_pay(ali_pay_json);
                log.info("支付宝_反扫支付_响应:" + client_alipay_response);
                JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, CommonConfig.PAY_RESULT_FALSE);
        }
    }

    /**
     * 支付宝扫码支付、微信刷卡支付（正扫）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/precreate_trade_pay", method = RequestMethod.POST)
    public void precreate_trade_pay(HttpServletRequest request, HttpServletResponse response) {
        try {
                JSONObject ali_pay_json = Ali_pay_json.package_ali_pay_json(request, AliPayConfig.PRECREATE_TRADE_PAY);
                log.info("支付宝_正扫_支付请求:" + ali_pay_json.toString());
                String client_alipay_response = aliPayService.Ali_precreate_trade_pay(ali_pay_json);
                log.info("支付宝_正扫_支付响应:" + client_alipay_response);
                JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, CommonConfig.PAY_RESULT_FALSE);
        }
    }

    /**
     * 支付宝/微信撤单
     *
     * @param request
     * @param response
     */
    @RequestMapping("/cancel")
    public void cancel(HttpServletRequest request, HttpServletResponse response) {
        try {
            String channel = request.getParameter("channel");
                JSONObject ali_pay_json = Ali_pay_json.package_ali_query_cancel_json(request);
                log.info("支付宝_撤单_请求:" + ali_pay_json.toString());
                String client_alipay_response = aliPayService.Ali_cancel(ali_pay_json);
                log.info("支付宝_撤单_响应:" + client_alipay_response);
                JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, CommonConfig.CANCEL_RESULT_FALSE);
        }
    }

    /**
     * 支付宝/微信退款
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/refund")
    public void refund(HttpServletRequest request, HttpServletResponse response) {
        try {
                JSONObject ali_pay_json = Ali_pay_json.package_ali_refund_json(request);
                log.info("支付宝_退款_请求:" + ali_pay_json.toString());
                String client_alipay_response = aliPayService.Ali_refund(ali_pay_json);
                log.info("支付宝_退款_响应:" + client_alipay_response);
                JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, CommonConfig.REFUND_RESULT_FALSE);
        }
    }

    /**
     * 支付宝/微信订单查询接口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response) {
        try {
            String channel = request.getParameter("channel");

                JSONObject ali_pay_json = Ali_pay_json.package_ali_query_cancel_json(request);
                log.info("支付宝_查询_请求:" + ali_pay_json.toString());
                String client_alipay_response = aliPayService.Ali_query(ali_pay_json);
                log.info("支付宝_查询_响应:" + client_alipay_response);
                JsonUtil.sendJson(response, client_alipay_response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response,CommonConfig.QUERY_RESULT_FALSE);
        }
    }

    /**
     * 支付宝交易结果异步通知
     * @param request
     * @param response
     */
    @RequestMapping("/pay_result")
    public void pay_result(HttpServletRequest request, HttpServletResponse response) {
        String trade_status = request.getParameter("trade_status");
        String total_amount = request.getParameter("total_amount");
        String receipt_amount = request.getParameter("receipt_amount");
        log.info("接受支付通知,交易状态：" + trade_status + ",交易金额：" + total_amount + "元,实收金额：" + receipt_amount + "元");
        if ("TRADE_SUCCESS".equals(trade_status)) {
            JsonUtil.sendJson(response, "success");
        }
    }

    public static void main(String args[]) {
        System.out.println(OrderNoUtil.getOrderNo());
    }
}
