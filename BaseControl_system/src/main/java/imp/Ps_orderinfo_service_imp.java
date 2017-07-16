package imp;

import dao.Ps_orderInfo_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<Map<String, String>> select_etps_order_info(Map<String, String> map) {
        return ps_orderInfo_mapper.select_etps_order_info(map);
    }

    public Map<String, String> select_etps_order_count_pay_success(Map<String, String> map) {
        return ps_orderInfo_mapper.select_etps_order_count_pay_success(map);
    }

    public Map<String, String> select_etps_order_count_refund(Map<String, String> map) {
        return ps_orderInfo_mapper.select_etps_order_count_refund(map);
    }
}
