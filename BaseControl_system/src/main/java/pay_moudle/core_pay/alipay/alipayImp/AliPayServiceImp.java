package pay_moudle.core_pay.alipay.alipayImp;

import common.AliPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import pay_moudle.core_pay.alipay.alipayService.AliPayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pay_moudle.core_pay.pay_json.Ali_pay_json;
import pojo.Order_info;
import service.Ps_orderinfo_service;
import util.DateUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by changyu on 2017/3/1 0001.
 */
@Service
public class AliPayServiceImp implements AliPayService {
    private static Logger log = Logger.getLogger(AliPayServiceImp.class);
    @Autowired
    private Ps_orderinfo_service ps_orderinfo_service;
    /**
     * 支付宝反扫支付
     *
     * @param ali_pay_json
     * @throws UnsupportedEncodingException
     */
    public String Ali_trade_pay(JSONObject ali_pay_json) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.ALI_GATE_WAY, AliPayConfig.ISV_APPID, AliPayConfig.ALI_PAY_ISV_PRIVATE_KEY, "json", "GBK", AliPayConfig.ALI_PAY_PUBLIC_KEY, "RSA");
        AlipayTradePayRequest requestPay = new AlipayTradePayRequest();
        requestPay.putOtherTextParam("notify_url",AliPayConfig.ALI_PAY_NOTIFY_URL);
        requestPay.setBizContent(ali_pay_json.toString());
        AlipayTradePayResponse responsePay = null;
        responsePay = alipayClient.execute(requestPay,null,AliPayConfig.APP_AUTH_TOKEN);
        if("10000".equals(responsePay.getCode())){
            Order_info order_info=new Order_info();
            order_info.setPay_status("1");
            order_info.setTrade_no(responsePay.getTradeNo());
            order_info.setReceipt_amount(responsePay.getReceiptAmount());
            order_info.setBuyer_pay_amount(responsePay.getBuyerPayAmount());
            order_info.setGmt_amount(String.valueOf(responsePay.getGmtPayment()));
            order_info.setInvoice_amount(responsePay.getInvoiceAmount());
            order_info.setPoint_amount(responsePay.getPointAmount());
            order_info.setPay_date(DateUtil.getTimestamp(DateUtil.SIMPLE));
            order_info.setOut_trade_no(responsePay.getOutTradeNo());
            ps_orderinfo_service.update_pay_status(order_info);
        }
        JSONObject client_alipay_response = Ali_pay_json.client_ali_trade_pay_response(responsePay);
        return client_alipay_response.toString();
    }
    /**
     * 支付宝退款
     *
     * @param ali_refund_json
     * @return
     */
    public String Ali_refund(JSONObject ali_refund_json) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.ALI_GATE_WAY, AliPayConfig.ISV_APPID, AliPayConfig.ALI_PAY_ISV_PRIVATE_KEY, "json", "GBK", AliPayConfig.ALI_PAY_PUBLIC_KEY, "RSA");
        AlipayTradeRefundRequest requestRefund = new AlipayTradeRefundRequest();
        requestRefund.setBizContent(ali_refund_json.toString());
        AlipayTradeRefundResponse responseRefund = null;
        responseRefund = alipayClient.execute(requestRefund, AliPayConfig.APP_AUTH_TOKEN);
        if("10000".equals(responseRefund.getCode())){
            Order_info order_info=new Order_info();
            order_info.setPay_status("2");
            order_info.setOut_trade_no(responseRefund.getOutTradeNo());
            ps_orderinfo_service.update_pay_status(order_info);
        }
        JSONObject client_ali_refund_response = Ali_pay_json.client_ali_refund_response(responseRefund);
        return client_ali_refund_response.toString();
    }

    /**
     * 支付宝交易撤销
     * @param ali_cancel_json
     * @return
     * @throws AlipayApiException
     */
    public String Ali_cancel(JSONObject ali_cancel_json)throws AlipayApiException{
    AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.ALI_GATE_WAY, AliPayConfig.ISV_APPID, AliPayConfig.ALI_PAY_ISV_PRIVATE_KEY, "json", "GBK", AliPayConfig.ALI_PAY_PUBLIC_KEY, "RSA");
    AlipayTradeCancelRequest requestCancel = new AlipayTradeCancelRequest();
    requestCancel.setBizContent(ali_cancel_json.toString());
    AlipayTradeCancelResponse responseCancel = alipayClient.execute(requestCancel,null,AliPayConfig.APP_AUTH_TOKEN);
    if("10000".equals(responseCancel.getCode())){
        Order_info order_info=new Order_info();
        order_info.setPay_status("3");
        order_info.setOut_trade_no(responseCancel.getOutTradeNo());
        ps_orderinfo_service.update_pay_status(order_info);
    }
    JSONObject client_ali_cancel_response = Ali_pay_json.client_ali_cancel_response(responseCancel);
    return client_ali_cancel_response.toString();
    }
    /**
     * 支付宝订单查询
     * @param ali_query_json
     * @return
     * @throws AlipayApiException
     */
    public String Ali_query(JSONObject ali_query_json)throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.ALI_GATE_WAY, AliPayConfig.ISV_APPID, AliPayConfig.ALI_PAY_ISV_PRIVATE_KEY, "json", "GBK", AliPayConfig.ALI_PAY_PUBLIC_KEY, "RSA");
        AlipayTradeQueryRequest requestQuery = new AlipayTradeQueryRequest();
        requestQuery.setBizContent(ali_query_json.toString());
        AlipayTradeQueryResponse responseQuery = null;
        responseQuery = alipayClient.execute(requestQuery,null,AliPayConfig.APP_AUTH_TOKEN);
        if("TRADE_SUCCESS".equals(responseQuery.getTradeStatus())){
            Order_info order_info=new Order_info();
            order_info.setPay_status("1");
            order_info.setTrade_no(responseQuery.getTradeNo());
            order_info.setReceipt_amount(responseQuery.getReceiptAmount());
            order_info.setBuyer_pay_amount(responseQuery.getBuyerPayAmount());
            order_info.setInvoice_amount(responseQuery.getInvoiceAmount());
            order_info.setPoint_amount(responseQuery.getPointAmount());
            order_info.setPay_date(DateUtil.getTimestamp(DateUtil.SIMPLE));
            order_info.setOut_trade_no(ali_query_json.getString("out_trade_no"));
            ps_orderinfo_service.update_pay_status(order_info);
        }
        JSONObject client_ali_refund_response = Ali_pay_json.client_ali_query_response(responseQuery);
        return client_ali_refund_response.toString();
    }


}
