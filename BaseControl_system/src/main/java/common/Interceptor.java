package common;

import com.spinn3r.log5j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import util.JsonUtil;
import util.UserUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by changyu on 2017/6/5 0005.
 */
public class Interceptor implements HandlerInterceptor {
    private Logger log = Logger.getLogger(Interceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        /**
         * 1.验证签名
         * 2.token验证
         */
        try {
            if(!request.getRequestURI().startsWith("/user/login")){
                log.info("发生路径检查："+request.getRequestURL());
                /**
                 * token验证
                 */
                Jedis jedis = new Jedis(Config.REDIS_IP,Config.REDIS_PORT);
                Cookie cookies[] = request.getCookies();
                String client_token = null;
                for (int i = 0; i < cookies.length; i++) {
                    if ("login_token".equals(cookies[i].getName())) {
                        client_token = cookies[i].getValue();
                        break;
                    }
                }
                if (client_token == null || client_token == "") {
                    response.sendRedirect(request.getContextPath());
                    return false;
                }
                String token = jedis.hget("login_token", UserUtil.getUser_name(request));
                if (!client_token.equals(token)) {
                    response.sendRedirect(request.getContextPath());
                    return false;
                }

                /**
                 * session是否过期
                 */
                if(request.getSession().getAttribute("user")==null){
                    request.getRequestDispatcher("welcome").forward(request,response);
                    return false;
                }
            }
            return true;
        }catch (JedisConnectionException e){
            log.error("redis连接异常..");
            JsonUtil.sendJson(response,Config.ERROR("redis连接异常.."));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtil.sendJson(response, Config.ERROR(e.getMessage()));
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
