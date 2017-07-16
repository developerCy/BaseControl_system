package imp;

import dao.Ps_store_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Store_info;
import service.Ps_store_service;

import java.util.List;

/**
 * Created by changyu on 2017/7/13 0013.
 */
@Service
public class Ps_store_service_imp implements Ps_store_service{
    @Autowired
    private Ps_store_mapper ps_store_mapper;
    public List<Store_info> select_etps_order_info(Store_info store_info) {
        return ps_store_mapper.select_etps_order_info(store_info);
    }

    public String select_count(String iAgent_id) {
        return ps_store_mapper.select_count(iAgent_id);
    }

    public String select_count_koubei() {
        return ps_store_mapper.select_count_koubei();
    }

    public void update_etps_order_info(Store_info store_info) {
        ps_store_mapper.update_etps_order_info(store_info);
    }

    public void insert_etps_order_info(Store_info store_info) {
        ps_store_mapper.insert_etps_order_info(store_info);
    }

    public void del_store(Store_info store_info) {
        ps_store_mapper.del_store(store_info);
    }
}
