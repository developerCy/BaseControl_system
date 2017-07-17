package common;

import com.spinn3r.log5j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import service.Ps_userinfo_service;
import util.JsonUtil;
import util.UserUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/6/5 0005.
 */
public class Interceptor implements HandlerInterceptor {
    private Logger log = Logger.getLogger(Interceptor.class);
    @Autowired
    private Ps_userinfo_service ps_userinfo_service;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        /**
         * 1.验证签名
         * 2.token验证
         */
        try {
            Jedis jedis = new Jedis(Config.REDIS_IP, Config.REDIS_PORT);
            if (!request.getRequestURI().startsWith("/user/login")) {
                log.info("发生路径检查：" + request.getRequestURL());
                /**
                 * session是否过期
                 */
                if (request.getSession().getAttribute("user") == null) {
                    response.sendRedirect("/login.html");
                    return false;
                    /**
                     * 如果异地登陆，原地址将被踢出下线
                     */
                }
                if (!request.getRemoteAddr().equals(jedis.hget("login_token", UserUtil.getUser_name(request)))) {
                    response.sendRedirect("/login.html");
                    return false;
                }
            }
            return true;
        } catch (JedisConnectionException e) {
            log.error("redis连接异常..");
            JsonUtil.sendJson(response, Config.ERROR("redis连接异常.."));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
/**
 * 获取站内信
 */
        if("渠道商".equals(UserUtil.getLogin_type(request))){
            List<Map<String, String>> message_list = ps_userinfo_service.select_message(UserUtil.getUser_id(request));
            if (!message_list.isEmpty()) {
                request.getSession().setAttribute("message", message_list);
                request.getSession().setAttribute("message_num", message_list.size());
            }else{
                request.getSession().setAttribute("message_num",null);
            }
        }
    }
}
