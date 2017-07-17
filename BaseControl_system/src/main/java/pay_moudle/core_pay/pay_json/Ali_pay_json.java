package pay_moudle.core_pay.pay_json;

import com.alipay.api.response.*;
import common.AliPayConfig;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * Created by changyu on 2017/5/31 0031.
 */


public class Ali_pay_json {
    /**
     * 封装支付宝2.0正/反扫支付请求参数
     */
    public static JSONObject package_ali_pay_json(String out_trade_no, String auth_code, String total_amount, String discountable_amount, String undiscountable_amount, String body, String goods_detail, String store_id, String alipay_store_id) throws UnsupportedEncodingException {
        String subject = "这是一笔支付测试";
        String time_express = "3m";
        String extend_params = "{\"sys_service_provider_id\":" + AliPayConfig.ALI_PAY_ISV_PID + "}";//系统商编号 该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", out_trade_no);
        jsonObject.put("auth_code", auth_code);
        jsonObject.put("scene", "bar_code");
        jsonObject.put("subject", subject);
        jsonObject.put("total_amount", total_amount);
        if (discountable_amount != null) {
            jsonObject.put("discountable_amount", discountable_amount);
        }
        if (undiscountable_amount != null) {
            jsonObject.put("undiscountable_amount", undiscountable_amount);
        }
        if (body != null) {
            jsonObject.put("body", body);
        }
        if (goods_detail != null) {
            jsonObject.put("goods_detail", goods_detail);
        }
        if (alipay_store_id != null) {
            jsonObject.put("alipay_store_id", alipay_store_id);
        } else {
            jsonObject.put("store_id", store_id);
        }
        if (time_express != null) {
            jsonObject.put("time_express", time_express);
        }
        jsonObject.put("extend_params", extend_params);
        return jsonObject;
    }

    /**
     * 封装客户端的返回支付宝反扫报文
     *
     * @param responsePay
     * @return
     */
    public static JSONObject client_ali_trade_pay_response(AlipayTradePayResponse responsePay) {
        JSONObject client_response = new JSONObject();
        //支付成功
        if ("10000".equals(responsePay.getCode())) {
            client_response.put("code", "10000");
            client_response.put("method", AliPayConfig.TRADE_PAY);
            client_response.put("trade_status", "success");
            client_response.put("trade_no", responsePay.getTradeNo());
            client_response.put("out_trade_no", responsePay.getOutTradeNo());
            client_response.put("buyer_login_id", responsePay.getBuyerLogonId());
            client_response.put("total_amount", responsePay.getTotalAmount());
            client_response.put("receipt_amount", responsePay.getReceiptAmount());
            client_response.put("buyer_pay_amount", responsePay.getBuyerPayAmount());
            client_response.put("point_amount", responsePay.getPointAmount());
            client_response.put("invoice_amount", responsePay.getInvoiceAmount());
            client_response.put("gmt_payment", responsePay.getGmtPayment());
            client_response.put("fund_bill_list", responsePay.getFundBillList());
            client_response.put("card_balance", responsePay.getCardBalance());
            client_response.put("store_name", responsePay.getStoreName());
            client_response.put("buyer_user_id", responsePay.getBuyerUserId());
            client_response.put("discount_goods_detail", responsePay.getDiscountGoodsDetail());
            client_response.put("voucher_detail_list", responsePay.getVoucherDetailList());
            return client_response;
            //等待用户输入密码
        } else if ("10003".equals(responsePay.getCode())) {
            client_response.put("code", responsePay.getCode());
            client_response.put("method", AliPayConfig.TRADE_PAY);
            client_response.put("out_trade_no", responsePay.getOutTradeNo());
            client_response.put("trade_status", "WAIT_BUYER_PAY");
            client_response.put("desc", responsePay.getSubMsg());
            return client_response;
            //支付失败
        } else {
            client_response.put("code", "-1");
            client_response.put("method", AliPayConfig.TRADE_PAY);
            client_response.put("trade_status", "false");
            client_response.put("desc", responsePay.getSubMsg());
            return client_response;
        }
    }

    /**
     * 封装支付宝退款请求报文
     *
     * @return
     */
    public static JSONObject package_ali_refund_json(String out_trade_no, String refund_amount) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", out_trade_no);
        jsonObject.put("refund_amount", refund_amount);
        return jsonObject;
    }

    /**
     * 封装客户端的返回支付宝退款报文
     *
     * @param refundResponse
     * @return
     */
    public static JSONObject client_ali_refund_response(AlipayTradeRefundResponse refundResponse) {
        JSONObject client_response = new JSONObject();
        //退款成功
        if ("10000".equals(refundResponse.getCode())) {
            client_response.put("code", "10000");
            client_response.put("method", AliPayConfig.REFUND);
            client_response.put("refund_status", "success");
            client_response.put("out_trade_no", refundResponse.getOutTradeNo());
            client_response.put("trade_no", refundResponse.getTradeNo());
            client_response.put("open_id", refundResponse.getOpenId());
            client_response.put("buyer_logon_id", refundResponse.getBuyerLogonId());
            client_response.put("fund_change", refundResponse.getFundChange());
            client_response.put("refund_fee", refundResponse.getRefundFee());
            client_response.put("gmt_refund_pay", refundResponse.getGmtRefundPay());
            client_response.put("refund_detail_item_list", refundResponse.getRefundDetailItemList());
            client_response.put("store_name", refundResponse.getStoreName());
            client_response.put("buyer_user_id", refundResponse.getBuyerUserId());
            return client_response;
            //退款失败
        } else {
            client_response.put("code", "-1");
            client_response.put("method", AliPayConfig.REFUND);
            client_response.put("refund_status", "false");
            client_response.put("desc", refundResponse.getSubMsg());
        }
        return null;
    }

    /**
     * 封装客户端支付宝交易撤销报文
     *
     * @param cancelResponse
     * @return
     */
    public static JSONObject client_ali_cancel_response(AlipayTradeCancelResponse cancelResponse) {
        JSONObject client_response = new JSONObject();
        //撤销成功
        if ("10000".equals(cancelResponse.getCode())) {
            client_response.put("code", "10000");
            client_response.put("method", AliPayConfig.REFUND);
            client_response.put("cancel_status", "success");
            client_response.put("trade_no", cancelResponse.getTradeNo());
            client_response.put("out_trade_no", cancelResponse.getOutTradeNo());
            client_response.put("retry_flag", cancelResponse.getRetryFlag());
            client_response.put("action", cancelResponse.getAction());
            //撤销失败
        } else {
            client_response.put("code", "-1");
            client_response.put("method", AliPayConfig.CANCEL);
            client_response.put("cancel_status", "false");
            client_response.put("desc", cancelResponse.getSubMsg());
        }
        return client_response;
    }

    /**
     * 封装支付宝交易查询/撤单请求报文
     *
     * @param request
     * @return
     */
    public static JSONObject package_ali_query_cancel_json(HttpServletRequest request) {
        String out_trade_no = request.getParameter("out_trade_no");
        String trade_no = request.getParameter("trade_no");
        JSONObject jsonObject = new JSONObject();
        if (out_trade_no != null) {
            jsonObject.put("out_trade_no", out_trade_no);
        }
        if (trade_no != null) {
            jsonObject.put("trade_no", trade_no);
        }
        return jsonObject;
    }

    /**
     * 封装客户端支付宝交易查询报文
     *
     * @param queryResponse
     * @return
     */
    public static JSONObject client_ali_query_response(AlipayTradeQueryResponse queryResponse) {
        JSONObject client_response = new JSONObject();
        //查询成功
        if ("10000".equals(queryResponse.getCode())) {
            client_response.put("code", "10000");
            client_response.put("method", AliPayConfig.QUERY);
            client_response.put("query_status", "success");
            client_response.put("trade_no", queryResponse.getTradeNo());
            client_response.put("out_trade_no", queryResponse.getOutTradeNo());
            client_response.put("open_id", queryResponse.getOpenId());
            client_response.put("buyer_logon_id", queryResponse.getBuyerLogonId());
            client_response.put("trade_status", queryResponse.getTradeStatus());
            client_response.put("total_amount", queryResponse.getTotalAmount());
            client_response.put("receipt_amount", queryResponse.getReceiptAmount());
            client_response.put("buyer_pay_amount", queryResponse.getBuyerPayAmount());
            client_response.put("point_amount", queryResponse.getPointAmount());
            client_response.put("invoice_amount", queryResponse.getInvoiceAmount());
            client_response.put("send_pay_date", queryResponse.getSendPayDate());
            client_response.put("alipay_store_id", queryResponse.getAlipayStoreId());
            client_response.put("store_id", queryResponse.getStoreId());
            client_response.put("terminal_id", queryResponse.getTerminalId());
            client_response.put("fund_bill_list", queryResponse.getFundBillList());
            client_response.put("store_name", queryResponse.getStoreName());
            client_response.put("buyer_user_id", queryResponse.getBuyerUserId());
            client_response.put("discount_goods_detail", queryResponse.getDiscountGoodsDetail());
            client_response.put("industry_sepc_detail", queryResponse.getIndustrySepcDetail());
            client_response.put("voucher_detail_list", queryResponse.getVoucherDetailList());
            return client_response;
            //查询失败
        } else {
            client_response.put("code", "-1");
            client_response.put("method", AliPayConfig.QUERY);
            client_response.put("query_status", "false");
            client_response.put("desc", queryResponse.getSubMsg());
        }
        return null;
    }
}
