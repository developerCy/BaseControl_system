package imp;

import dao.Ps_etps_mapper;
import dao.Ps_user_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EtpsInfo;
import service.Ps_etps_service;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/10 0010.
 */
@Service
public class Ps_etps_service_imp implements Ps_etps_service{
    @Autowired
    private Ps_etps_mapper ps_etps_mapper;
    public List<EtpsInfo> select_etpsinfo(Map<String, String> map) {
        return ps_etps_mapper.select_etpsinfo(map);
    }

    public void insert_etpsinfo(EtpsInfo etpsInfo) {
        ps_etps_mapper.insert_etpsinfo(etpsInfo);
    }

    public void update_etpsinfo(EtpsInfo etpsInfo) {
        ps_etps_mapper.update_etpsinfo(etpsInfo);
    }

    public void del_etpsinfo(Map<String, String> map) {
        ps_etps_mapper.del_etpsinfo(map);
    }

    public String select_checked_all(String check_status) {
        return ps_etps_mapper.select_checked_all(check_status);
    }

    public String select_count_all() {
        return ps_etps_mapper.select_count_all();
    }

    public String select_use_all() {
        return ps_etps_mapper.select_use_all();
    }
}
