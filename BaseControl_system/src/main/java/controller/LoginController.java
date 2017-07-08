package controller;

import common.Config;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.Schedule;
import pojo.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import service.Ps_schedule_service;
import service.Ps_userinfo_service;
import util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/19 0019.
 */
@RequestMapping("/user")
@Controller
public class LoginController {
    private static Logger log = Logger.getLogger(LoginController.class);
    @Autowired
    private Ps_userinfo_service ps_userinfo_service;
    @Autowired
    private Ps_schedule_service ps_schedule_service;

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void Login(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            Jedis jedis=new Jedis(Config.REDIS_IP,Config.REDIS_PORT);
            if (username == null || password == null) {
                JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                return;
            }
            List<User> user = ps_userinfo_service.select_user_byName(username);
            if (user.isEmpty()) {
                JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                return;
            }
            if (!Md5Util.getMD5(password).equalsIgnoreCase(user.get(0).getPass_word())) {
                JsonUtil.sendJson(response, Config.LOGIN_FALSE);
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user.get(0));
            session.setAttribute("user_name",username);
            session.setAttribute("login_type", user.get(0).getLogin_type());
            String login_token =request.getRemoteAddr();
            jedis.hset("login_token",username,login_token);
            JsonUtil.sendJson(response, Config.SUCCESS);
        }catch (JedisConnectionException e){
            log.error("redis连接异常..");
            JsonUtil.sendJson(response,Config.ERROR("redis连接异常.."));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 用户信息修改/新建/删除/获取
     *
     * @param request
     * @param response
     */
    @RequestMapping("/edit")
    public ModelAndView user_edit(Model model,HttpServletRequest request, HttpServletResponse response) {
        try {
            if ("del".equals(request.getParameter("sign"))) {
                ps_userinfo_service.del_user(request.getParameter("user_name"));
                ps_userinfo_service.del_work_sign(request.getParameter("user_name"));
                Map<String,String> map=new HashMap<String, String>();
                map.put("user_name",request.getParameter("user_name"));
                ps_schedule_service.del_schedule(map);
                return new ModelAndView("user_group");
            }
            if("sel_one".equals(request.getParameter("sign"))){
                List<User> users = ps_userinfo_service.select_user_byName(request.getParameter("user_name"));
                User user=users.get(0);
                model.addAttribute("user_info",user);
                return new ModelAndView("user_info");
            }
            if ("sel".equals(request.getParameter("sign"))) {
                List<User> users = ps_userinfo_service.select_user_byName("");
                if (!users.isEmpty()) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject();
                    for (User user : users) {
                        jsonObject.put("logo",StringUtil.defaultString(user.getLogo(),"未上传"));
                        jsonObject.put("user_name", user.getUser_name());
                        jsonObject.put("phone", StringUtil.defaultString(user.getPhone(), "暂无"));
                        jsonObject.put("address", StringUtil.defaultString(user.getAddress(), "暂无"));
                        jsonObject.put("login_type", StringUtil.defaultString(user.getLogin_type(), "暂无"));
                        jsonObject.put("email", StringUtil.defaultString(user.getEmail(), "暂无"));
                        jsonArray.add(jsonObject);
                    }
                    JsonUtil.sendJson(response,Config.DATA_RETURN(jsonArray));
                }
                JsonUtil.sendJson(response, Config.DATA_RETURN(JSONArray.fromObject("[]")));
            }
            String user_name = UserUtil.getUser_name(request);
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String logo = request.getParameter("logo");
            String login_type = request.getParameter("login_type");
            String email=request.getParameter("email");
            User user = new User();
            user.setUser_name(user_name);
            user.setPhone(phone);
            user.setAddress(address);
            user.setPass_word(Md5Util.getMD5(password));
            user.setLogin_type(login_type);
            user.setEmail(email);
            if (logo != null || logo != "") {
                user.setLogo(logo);
            }
            if ("new".equals(request.getParameter("sign"))) {
                user_name=request.getParameter("user_name");
              List<User> list=ps_userinfo_service.select_user_byName(user_name);
                if(!list.isEmpty()){
                    JsonUtil.sendJson(response, Config.USER_EXIST);
                }
                user.setUser_name(user_name);
                ps_userinfo_service.insert_user(user);
               return new ModelAndView("user_group");
            }
            if ("edit".equals(request.getParameter("sign"))) {
                ps_userinfo_service.update_user_byName(user);
                return new ModelAndView("user_group");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
        }
        return null;
    }

    /**
     * 用户登出
     *
     * @param request
     * @param response
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            Jedis jedis = new Jedis(Config.REDIS_IP, Config.REDIS_PORT);
            jedis.hdel("login_token", UserUtil.getUser_name(request));
            request.getSession().invalidate();
            response.sendRedirect("/login.html");
        }catch (JedisConnectionException e){
            log.error("redis连接异常..");
            JsonUtil.sendJson(response,Config.ERROR("redis连接异常.."));
            return;
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
            return;
        }
    }

    /**
     * 工作签到
     *
     * @param request
     * @param response
     */
    @RequestMapping("/work_sign")
    public void work_sign(HttpServletRequest request, HttpServletResponse response) {
        try {
            String date = DateUtil.getTimestamp(DateUtil.SIMPLE);
            String morning_work = request.getParameter("morning_work");
            String afternoon_work = request.getParameter("afternoon_work");
            String work_end = request.getParameter("work_end");
            String user_name = request.getParameter("user_name");
            Map<String, String> map = new HashMap<String, String>();
            //开始工作签到
            if (work_end == null || work_end == "") {
                map.put("work_start", date);
                map.put("user_name", UserUtil.getUser_name(request));
                ps_userinfo_service.insert_work_start(map);
                JsonUtil.sendJson(response, Config.SUCCESS);
                return;
                //结束工作签到
            } else {
                int max_id = ps_userinfo_service.select_max_id(UserUtil.getUser_name(request));
                map.put("morning_work", morning_work);
                map.put("afternoon_work", afternoon_work);
                map.put("work_end", date);
                map.put("user_name", UserUtil.getUser_name(request));
                map.put("id", String.valueOf(max_id));
                ps_userinfo_service.update_work(map);
                JsonUtil.sendJson(response, Config.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
        }
    }

    /**
     * 获取签到信息
     *
     * @param request
     * @param response
     */
    @RequestMapping("/get_work_sign")
    public void get_work_sign(HttpServletRequest request, HttpServletResponse response) {
        try {
            String user_name = UserUtil.getUser_name(request);
            List<Map<String, String>> list = ps_userinfo_service.select_work_sign_byUser(user_name);
            if (!list.isEmpty()) {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                for (Map<String, String> map : list) {
                    jsonObject.put("user_name", map.get("user_name"));
                    jsonObject.put("work_start", map.get("work_start"));
                    jsonObject.put("work_end", StringUtil.defaultString(map.get("work_end"), "暂无"));
                    jsonObject.put("morning_work", StringUtil.defaultString(map.get("morning_work"), "暂无"));
                    jsonObject.put("afternoon_work", StringUtil.defaultString(map.get("afternoon_work"), "暂无"));
                    jsonArray.add(jsonObject);
                }
                JsonUtil.sendJson(response, Config.DATA_RETURN(jsonArray));
                return;
            }
            JsonUtil.sendJson(response, Config.DATA_RETURN(JSONArray.fromObject("[]")));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
        }
    }
}
