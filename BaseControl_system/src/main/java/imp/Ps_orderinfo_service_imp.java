package imp;

import dao.Ps_orderInfo_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Order_info;
import service.Ps_orderinfo_service;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/13 0013.
 */
@Service
public class Ps_orderinfo_service_imp implements Ps_orderinfo_service {
    @Autowired
    private Ps_orderInfo_mapper ps_orderInfo_mapper;
    public List<Order_info> select_etps_order_info(Order_info order_info) {
        return ps_orderInfo_mapper.select_etps_order_info(order_info);
    }

    public Map<String, String> select_etps_order_count_pay_success(Order_info order_info) {
        return ps_orderInfo_mapper.select_etps_order_count_pay_success(order_info);
    }

    public Map<String, String> select_etps_order_count_refund(Order_info order_info) {
        return ps_orderInfo_mapper.select_etps_order_count_refund(order_info);
    }

    public void insert_order(Order_info order_info) {
        ps_orderInfo_mapper.insert_order(order_info);
    }

    public void update_pay_status(Order_info order_info) {
        ps_orderInfo_mapper.update_pay_status(order_info);
    }
}
