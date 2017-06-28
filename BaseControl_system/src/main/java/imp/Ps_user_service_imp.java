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
    public List<User> select_user_byName(String user_name) {
        return ps_user_mapper.select_user_byName(user_name);
    }

    public void insert_user(User user) {
        ps_user_mapper.insert_user(user);
    }

    public void update_user_byName(User user) {
        ps_user_mapper.update_user_byName(user);
    }

    public void del_user(String user_name) {
        ps_user_mapper.del_user(user_name);
    }

    public void del_work_sign(String user_name) {
        ps_user_mapper.del_work_sign(user_name);
    }

    public void insert_work_start(Map<String, String> map) {
        ps_user_mapper.insert_work_start(map);
    }

    public void update_work(Map<String, String> map) {
        ps_user_mapper.update_work(map);
    }

    public List<Map<String, String>> select_work_sign_byUser(String user_name) {
       return ps_user_mapper.select_work_sign_byUser(user_name);
    }

    public int select_max_id(String user_name) {
        return ps_user_mapper.select_max_id(user_name);
    }
}
