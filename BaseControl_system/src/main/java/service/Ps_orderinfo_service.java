package service;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public interface Ps_orderinfo_service {
    List<Map<String,String>> select_etps_order_info(Map<String,String> map);
    Map<String,String> select_etps_order_count_pay_success(Map<String,String> map);
    Map<String,String> select_etps_order_count_refund(Map<String,String> map);
}
