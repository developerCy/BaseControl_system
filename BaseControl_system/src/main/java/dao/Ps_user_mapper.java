package dao;

import pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public interface Ps_user_mapper {
    List<User> select_user_byName(String user_name);
    void insert_user(User user);
    void update_user_byName(User user);
    void del_user(String user_name);
    void del_work_sign(String user_name);
    void insert_work_start(Map<String,String> map);
    void update_work(Map<String,String> map);
    List<Map<String,String>> select_work_sign_byUser(String user_name);
    int select_max_id(String user_name);
}
