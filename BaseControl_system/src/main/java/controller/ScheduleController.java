package controller;

import common.Config;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.Schedule;
import pojo.User;
import service.Ps_schedule_service;
import sun.java2d.pipe.SpanShapeRenderer;
import util.DateUtil;
import util.JsonUtil;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
@Controller
@RequestMapping("/Schedule")
public class ScheduleController {
    private  Logger log=Logger.getLogger(ScheduleController.class);
    @Autowired
    private Ps_schedule_service ps_schedule_service;

    /**
     * 创建日程
     * @param request
     * @param response
     */
    @RequestMapping("/create_schedule")
    public ModelAndView create_Schedule(Model model, HttpServletRequest request, HttpServletResponse response){
        try {
           String user_name=UserUtil.getUser_name(request);
            String title = request.getParameter("title");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            Schedule schedule = new Schedule();
            schedule.setUser_name(user_name);
            schedule.setTitle(title);
            schedule.setStartTime(startTime);
            schedule.setEndTime(endTime);
            schedule.setCreateTime(DateUtil.getTimestamp(DateUtil.SIMPLE));
            String id=request.getParameter("id");
            if(id==null ||"".equals(id)){
                log.info("新增日程..");
                ps_schedule_service.insert_schedule(schedule);
            }
            if(id!=null){
                log.info("修改日程..");
                schedule.setId(id);
                ps_schedule_service.update_schedle(schedule);
            }
            JsonUtil.sendJson(response, Config.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
        }
        return null;
    }



    /**
     * 获取
     * @param request
     * @param response
     */
    @RequestMapping("/get_schedule")
    public void get_schedule(HttpServletRequest request, HttpServletResponse response){
       try{
           String startTime=request.getParameter("startTime");
           Schedule schedule=ps_schedule_service.select_schedule_byId(startTime);
           JsonUtil.sendJson(response,"{\"code\":\"1\",\"time\":\"" + schedule.getStartTime() + "\",\"title\":\""+schedule.getTitle()+"\",\"desc\":\"success\"}");
       }catch (Exception e){
           e.printStackTrace();
           JsonUtil.sendJson(response,"{\"code\":\"1\",\"data\":\"null\",\"msg\":\"false\"}");
       }

    }

    /**
     * 获取Events数据源
     * @param request
     * @param response
     */
    @RequestMapping("/get_scheduleList")
    public void get_scheduleList(HttpServletRequest request, HttpServletResponse response){
        try{
           List<Schedule> list= ps_schedule_service.select_all_schedule(UserUtil.getUser_name(request));
           if(list.isEmpty()){
               JsonUtil.sendJson(response,Config.DATA_RETURN(JSONArray.fromObject(list)));
               return;
           }
           /*
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            for(Schedule schedule:list){
                jsonObject.put("id",schedule.getId());
                jsonObject.put("title",schedule.getTitle());
                jsonObject.put("startTime",schedule.getStartTime());
                jsonArray.add(jsonObject);
            }
            */
            JsonUtil.sendJson(response,Config.DATA_RETURN(JSONArray.fromObject(list)));
        }catch (Exception e){
            e.printStackTrace();
            JsonUtil.sendJson(response,Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 删除日程
     * @param request
     * @param response
     */
    @RequestMapping("/del_schedule")
    public void del_schedule(@RequestParam(value = "id",required = true)String id, HttpServletRequest request, HttpServletResponse response){
        if(id==null){
            JsonUtil.sendJson(response,Config.ERROR("id 不能为空"));
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("id",id);
        ps_schedule_service.del_schedule(map);
        JsonUtil.sendJson(response, Config.SUCCESS);
    }
}
