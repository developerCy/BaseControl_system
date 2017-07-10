package service;

import pojo.EtpsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/10 0010.
 */
public interface Ps_etps_service {
    List<EtpsInfo> select_etpsinfo(Map<String,String> map);
    void insert_etpsinfo(EtpsInfo etpsInfo);
    void update_etpsinfo(Map<String,String> map);
    void del_etpsinfo(Map<String,String> map);
}
