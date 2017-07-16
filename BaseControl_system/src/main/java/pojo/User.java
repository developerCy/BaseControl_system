package pojo;

import java.io.Serializable;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public class User implements Serializable{
    private String iAgent_id;
    private String iAgent_name;
    private String pass_word;
    private String phone;
    private String address;
    private String email;
    private String logo;
    private String login_type;
    private String status;
    private String create_time;
    private String modify_time;

    public String getiAgent_id() {
        return iAgent_id;
    }

    public void setiAgent_id(String iAgent_id) {
        this.iAgent_id = iAgent_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getiAgent_name() {
        return iAgent_name;
    }

    public void setiAgent_name(String iAgent_name) {
        this.iAgent_name = iAgent_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }
}
