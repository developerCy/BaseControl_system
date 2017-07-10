package pojo;

import java.io.Serializable;

/**
 * Created by changyu on 2017/7/10 0010.
 */
public class EtpsInfo implements Serializable{
    /**
     * 基本信息
     */
    private String contact_name;
    private String contact_address;
    private String contact_phone;
    private String contact_mail;
    private String etps_name;
    private String etps_login_name;
    private String etps_login_password;
    private String user_name;//归属
    private String status;
    private String create_time;
    private String modify_time;
    /**
     * 签约信息
     */
    private String alipay_token;
    private String sign_channel;

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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


    public String getSign_channel() {
        return sign_channel;
    }

    public void setSign_channel(String sign_channel) {
        this.sign_channel = sign_channel;
    }
}
