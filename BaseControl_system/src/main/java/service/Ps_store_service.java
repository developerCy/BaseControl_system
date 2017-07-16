package service;

import pojo.Store_info;

import java.util.List;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public interface Ps_store_service {
    List<Store_info> select_etps_order_info(Store_info store_info);
    String select_count(String iAgent_id);
    String select_count_koubei();
    void update_etps_order_info(Store_info store_info);
    void insert_etps_order_info(Store_info store_info);
    void del_store(Store_info store_info);
}
