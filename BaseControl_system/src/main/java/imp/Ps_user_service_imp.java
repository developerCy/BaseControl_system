package imp;

import dao.Ps_user_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.Ps_userinfo_service;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
@Service
public class Ps_user_service_imp implements Ps_userinfo_service{
    @Autowired
    private Ps_user_mapper ps_user_mapper;
    public List<User> select_user_byName(Map<String,String> map) {
        return ps_user_mapper.select_user_byName(map);
    }

    public void insert_user(User user) {
        ps_user_mapper.insert_user(user);
    }

    public void update_user_byName(User user) {
        ps_user_mapper.update_user_byName(user);
    }

    public void del_user(String iAgent_id) {
        ps_user_mapper.del_user(iAgent_id);
    }

    public void insert_message(Map<String, String> map) {
        ps_user_mapper.insert_message(map);
    }

    public List<Map<String, String>> select_message(String iAgent_id) {
        return ps_user_mapper.select_message(iAgent_id);
    }

    public void del_message(String id) {
        ps_user_mapper.del_message(id);
    }

}
