package imp;

import dao.Ps_schedule_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Schedule;
import service.Ps_schedule_service;

import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
@Service
public class Ps_schedule_service_imp implements Ps_schedule_service {
    @Autowired
    private Ps_schedule_mapper ps_schedule_mapper;
    public void insert_schedule(Schedule schedule) {
        ps_schedule_mapper.insert_schedule(schedule);
    }

    public Schedule select_schedule_byId(String startTime) {
        Schedule schedule1=ps_schedule_mapper.select_schedule_byId(startTime);
        return schedule1;
    }

    public List<Schedule> select_all_schedule(String user_name) {
        List<Schedule> list=ps_schedule_mapper.select_all_schedule(user_name);
        return list;
    }

    public void del_schedule(Map<String,String> map) {
        ps_schedule_mapper.del_schedule(map);
    }

    public void update_schedle(Schedule schedule) {
        ps_schedule_mapper.update_schedle(schedule);
    }

}
