package pojo;

import java.io.Serializable;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public class Store_info implements Serializable{
    private String iAgent_id;
    private String iEtps_id;
    private String etps_name;
    private String store_name;
    private String store_id;
    private String store_address;
    private String alipay_shop_id;
    private String shop_owner_name;
   private String business_area;

    public String getBusiness_area() {
        return business_area;
    }

    public void setBusiness_area(String business_area) {
        this.business_area = business_area;
    }

    public String getiAgent_id() {
        return iAgent_id;
    }

    public void setiAgent_id(String iAgent_id) {
        this.iAgent_id = iAgent_id;
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

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getAlipay_shop_id() {
        return alipay_shop_id;
    }

    public void setAlipay_shop_id(String alipay_shop_id) {
        this.alipay_shop_id = alipay_shop_id;
    }

    public String getShop_owner_name() {
        return shop_owner_name;
    }

    public void setShop_owner_name(String shop_owner_name) {
        this.shop_owner_name = shop_owner_name;
    }
}
