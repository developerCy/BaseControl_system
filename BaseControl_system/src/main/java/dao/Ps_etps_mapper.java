package dao;

import pojo.EtpsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/10 0010.
 */
public interface Ps_etps_mapper {
    List<EtpsInfo> select_etpsinfo(Map<String,String> map);
    void insert_etpsinfo(EtpsInfo etpsInfo);
    void update_etpsinfo(EtpsInfo etpsInfo);
    void del_etpsinfo(Map<String,String> map);
    String  select_checked_all(String check_status);
    String  select_count_all();
    String  select_use_all();
}
