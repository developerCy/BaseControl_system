package dao;

import pojo.User;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public interface Ps_user_mapper {
    List<User> select_user_byName(Map<String,String> map);
    void insert_user(User user);
    void update_user_byName(User user);
    void del_user(String iAgent_id);
    void insert_message(Map<String,String> map);
    List<Map<String,String>> select_message(String iAgent_id);
    void del_message(String id);
}
