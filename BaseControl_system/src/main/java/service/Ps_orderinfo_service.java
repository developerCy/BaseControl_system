package service;

import pojo.Order_info;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public interface Ps_orderinfo_service {
    List<Order_info> select_etps_order_info(Order_info order_info);
    Map<String,String> select_etps_order_count_pay_success(Order_info order_info);
    Map<String,String> select_etps_order_count_refund(Order_info order_info);
    void insert_order(Order_info order_info);
    void update_pay_status(Order_info order_info);
}
