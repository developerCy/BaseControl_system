package service;

import pojo.Store_info;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public interface Ps_store_service {
    List<Store_info> select_store(Store_info store_info);
    String select_count(Store_info store_info);
    void insert_store(Store_info store_info);
    void update_store(Store_info store_info);
    void del_store(Store_info store_info);
}
