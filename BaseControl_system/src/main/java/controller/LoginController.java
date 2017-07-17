package controller;

import common.Config;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.EtpsInfo;
import pojo.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import service.Ps_etps_service;
import service.Ps_userinfo_service;
import util.*;

import javax.security.sasl.SaslServer;
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
    private Ps_etps_service ps_etps_service;

    /**
     * 登陆
     *
     * @param user_name
     * @param pass_word
     * @param request
     * @param response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void Login(@RequestParam(value = "user_name") String user_name,
                      @RequestParam(value = "pass_word") String pass_word,
                      @RequestParam(value = "login_type") String login_type,
                      HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Jedis jedis = new Jedis(Config.REDIS_IP, Config.REDIS_PORT);
            if (user_name == null || pass_word == null) {
                JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                return;
            }
            if("商户".equals(login_type)){
                Map<String,String> map=new HashMap<String, String>();
                map.put("etps_name",user_name);
                List<EtpsInfo> list=ps_etps_service.select_etpsinfo(map);
                if(list.isEmpty()){
                    JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                    return;
                }
                if (!Md5Util.getMD5(pass_word).equalsIgnoreCase(list.get(0).getEtps_login_password())) {
                    JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                    return;
                }
                session.setAttribute("user",list.get(0));
                session.setAttribute("login_type","商户");
                session.setAttribute("iEtps_id",list.get(0).getiEtps_id());
            }else{
                Map<String, String> map = new HashMap<String, String>();
                map.put("iAgent_name", user_name);
                List<User> user = ps_userinfo_service.select_user_byName(map);
                if (user.isEmpty()) {
                    JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                    return;
                }
                if (!Md5Util.getMD5(pass_word).equalsIgnoreCase(user.get(0).getPass_word())) {
                    JsonUtil.sendJson(response, Config.LOGIN_FALSE);
                    return;
                }
                if ("无效".equals(user.get(0).getStatus())) {
                    JsonUtil.sendJson(response, Config.USER_FASLE);
                    return;
                }
                session.setAttribute("user", user.get(0));
                session.setAttribute("login_type", user.get(0).getLogin_type());
                session.setAttribute("iAgent_id", user.get(0).getiAgent_id());
                /**
                 * 获取站内信
                 */
                List<Map<String, String>> message_list = ps_userinfo_service.select_message(user.get(0).getiAgent_id());
                if (!message_list.isEmpty()) {
                    session.setAttribute("message", message_list);
                    session.setAttribute("message_num", message_list.size());
                }
            }
            String login_token = request.getRemoteAddr();
            /**
             * 保存登陆地址
             */
            jedis.hset("login_token", user_name, login_token);

            JsonUtil.sendJson(response, Config.SUCCESS);
        } catch (JedisConnectionException e) {
            log.error("redis连接异常..");
            JsonUtil.sendJson(response, Config.ERROR("redis连接异常.."));
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
    public ModelAndView user_edit(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            if ("del".equals(request.getParameter("sign"))) {
                ps_userinfo_service.del_user(request.getParameter("iAgent_id"));
                return new ModelAndView("user_group");
            }
            if ("sel_one".equals(request.getParameter("sign"))) {
                map.put("iAgent_id", request.getParameter("iAgent_id"));
                List<User> users = ps_userinfo_service.select_user_byName(map);
                User user = users.get(0);
                model.addAttribute("user_info", user);
                return new ModelAndView("user_info");
            }
            if ("sel".equals(request.getParameter("sign"))) {
                List<User> users = ps_userinfo_service.select_user_byName(null);
                model.addAttribute("userinfo_list", users);
                return new ModelAndView("user_group");
            }
            String iAgent_name = request.getParameter("iAgent_name");
            String password = request.getParameter("pass_word");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String logo = request.getParameter("logo");
            String login_type = request.getParameter("login_type");
            String email = request.getParameter("email");
            String status = request.getParameter("status");
            User user = new User();
            user.setiAgent_name(iAgent_name);
            user.setPhone(phone);
            user.setAddress(address);
            user.setPass_word(Md5Util.getMD5(password));
            user.setLogin_type(login_type);
            user.setEmail(email);
            user.setStatus(status);
            if (logo != null || logo != "") {
                user.setLogo(logo);
            }
            if ("new".equals(request.getParameter("sign"))) {
                user.setCreate_time(DateUtil.getTimestamp(DateUtil.SIMPLE));
                map.put("iAgent_name", request.getParameter("iAgent_name"));
                List<User> list = ps_userinfo_service.select_user_byName(map);
                if (!list.isEmpty()) {
                    model.addAttribute("error", Config.USER_EXIST);
                    return new ModelAndView("../error");
                }
                user.setiAgent_name(iAgent_name);
                ps_userinfo_service.insert_user(user);
                return new ModelAndView("user_group");
            }
            if ("edit".equals(request.getParameter("sign"))) {
                user.setiAgent_id(request.getParameter("iAgent_id"));
                user.setModify_time(DateUtil.getTimestamp(DateUtil.SIMPLE));
                ps_userinfo_service.update_user_byName(user);
                return new ModelAndView("user_group");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(e.getMessage());
            return new ModelAndView("../error");
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
        } catch (JedisConnectionException e) {
            log.error("redis连接异常..");
            JsonUtil.sendJson(response, Config.ERROR("redis连接异常.."));
            return;
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
            return;
        }
    }

    /**
     * 站内信
     */
    @RequestMapping("/message_send")
    public ModelAndView message_moudle(@RequestParam(value = "message") String message,
                                       @RequestParam(value = "iAgent_id", required = false) String iAgent_id,
                                       Model model) {
        try {
            List<User> userList = ps_userinfo_service.select_user_byName(null);
            Map<String, String> map = new HashMap<String, String>();
            for (User user : userList) {
                map.clear();
                map.put("iAgent_id", user.getiAgent_id());
                map.put("message", message);
                map.put("create_time", DateUtil.getTimestamp(DateUtil.SIMPLE));
                ps_userinfo_service.insert_message(map);
            }
            return new ModelAndView("index");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }

    /**
     * 我的站内信页面
     */
    @RequestMapping("/my_message")
    public ModelAndView my_message(Model model,HttpServletRequest request) {
        try {
            List<Map<String, String>> list=ps_userinfo_service.select_message(UserUtil.getUser_id(request));
            model.addAttribute("message_list",list);
            return new ModelAndView("my_message");
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }
    @RequestMapping("/del_message")
    public void del_message(HttpServletRequest request,HttpServletResponse response){
        ps_userinfo_service.del_message(request.getParameter("id"));
        JsonUtil.sendJson(response,"{\"code\":\"1000\"}");
    }
}
