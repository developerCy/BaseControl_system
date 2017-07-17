package pojo;

import java.io.Serializable;

/**
 * Created by changyu on 2017/7/17 0017.
 */
public class Order_info implements Serializable{
    private String iAgent_id;
    private String iAgent_name;
    private String iEtps_id;
    private String etps_name;
    private String store_id;
    private String store_name;
    private String total_amount;
    private String channel;
    private String pay_status;
    private String out_trade_no;
    private String trade_no;
    private String receipt_amount;
    private String buyer_pay_amount;
    private String point_amount;
    private String invoice_amount;
    private String gmt_amount;
    private String order_date;
    private String pay_date;

    public String getiAgent_id() {
        return iAgent_id;
    }

    public void setiAgent_id(String iAgent_id) {
        this.iAgent_id = iAgent_id;
    }

    public String getiAgent_name() {
        return iAgent_name;
    }

    public void setiAgent_name(String iAgent_name) {
        this.iAgent_name = iAgent_name;
    }

    public String getiEtps_id() {
        return iEtps_id;
    }

    public void setiEtps_id(String iEtps_id) {
        this.iEtps_id = iEtps_id;
    }

    public String getEtps_name() {
        return etps_name;
    }

    public void setEtps_name(String etps_name) {
        this.etps_name = etps_name;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getReceipt_amount() {
        return receipt_amount;
    }

    public void setReceipt_amount(String receipt_amount) {
        this.receipt_amount = receipt_amount;
    }

    public String getBuyer_pay_amount() {
        return buyer_pay_amount;
    }

    public void setBuyer_pay_amount(String buyer_pay_amount) {
        this.buyer_pay_amount = buyer_pay_amount;
    }

    public String getPoint_amount() {
        return point_amount;
    }

    public void setPoint_amount(String point_amount) {
        this.point_amount = point_amount;
    }

    public String getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(String invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getGmt_amount() {
        return gmt_amount;
    }

    public void setGmt_amount(String gmt_amount) {
        this.gmt_amount = gmt_amount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }
}
