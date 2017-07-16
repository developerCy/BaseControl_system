package pojo;

import java.io.Serializable;

/**
 * Created by changyu on 2017/7/10 0010.
 */
public class EtpsInfo implements Serializable{
    /**
     * 基本信息
     */
    private String iEtps_id;
    private String iAgent_id;
    private String contact_name;
    private String contact_address;
    private String contact_phone;
    private String contact_mail;
    private String etps_name;
    private String etps_login_name;
    private String etps_login_password;
    private String iAgent_name;//归属
    private String status;
    private String create_time;
    private String modify_time;
    private String alipay_rate;
    /**
     * 签约信息
     */
    private String alipay_token;
    private String alipay_account;
    private String alipay_account_name;
    private String alipay_appid;
    private String alipay_pid;
    private String check_status;
    private String check_desc;

    public String getCheck_desc() {
        return check_desc;
    }

    public void setCheck_desc(String check_desc) {
        this.check_desc = check_desc;
    }

    public String getiEtps_id() {
        return iEtps_id;
    }

    public void setiEtps_id(String iEtps_id) {
        this.iEtps_id = iEtps_id;
    }

    public String getiAgent_id() {
        return iAgent_id;
    }

    public void setiAgent_id(String iAgent_id) {
        this.iAgent_id = iAgent_id;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getAlipay_rate() {
        return alipay_rate;
    }

    public void setAlipay_rate(String alipay_rate) {
        this.alipay_rate = alipay_rate;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }


    public String getAlipay_account_name() {
        return alipay_account_name;
    }

    public void setAlipay_account_name(String alipay_account_name) {
        this.alipay_account_name = alipay_account_name;
    }

    public String getAlipay_appid() {
        return alipay_appid;
    }

    public void setAlipay_appid(String alipay_appid) {
        this.alipay_appid = alipay_appid;
    }

    public String getAlipay_pid() {
        return alipay_pid;
    }

    public void setAlipay_pid(String alipay_pid) {
        this.alipay_pid = alipay_pid;
    }

    public String getContact_mail() {
        return contact_mail;
    }

    public void setContact_mail(String contact_mail) {
        this.contact_mail = contact_mail;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }


    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getEtps_name() {
        return etps_name;
    }

    public void setEtps_name(String etps_name) {
        this.etps_name = etps_name;
    }

    public String getEtps_login_name() {
        return etps_login_name;
    }

    public void setEtps_login_name(String etps_login_name) {
        this.etps_login_name = etps_login_name;
    }

    public String getEtps_login_password() {
        return etps_login_password;
    }

    public void setEtps_login_password(String etps_login_password) {
        this.etps_login_password = etps_login_password;
    }

    public String getiAgent_name() {
        return iAgent_name;
    }

    public void setiAgent_name(String iAgent_name) {
        this.iAgent_name = iAgent_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlipay_token() {
        return alipay_token;
    }

    public void setAlipay_token(String alipay_token) {
        this.alipay_token = alipay_token;
    }

}
