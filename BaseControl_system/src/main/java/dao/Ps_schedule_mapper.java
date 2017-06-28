package dao;

import pojo.Schedule;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public interface Ps_schedule_mapper {
    void insert_schedule(Schedule schedule);
    Schedule select_schedule_byId(String startTime);
    List<Schedule> select_all_schedule(String user_name);
    void del_schedule(Map<String,String> map);
    void update_schedle(Schedule schedule);
}
