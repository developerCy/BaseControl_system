package util;

import pojo.EtpsInfo;
import pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by changyu on 2017/6/21 0021.
 */
public class UserUtil {
    public static String getUser_name(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getiAgent_name();
    }
    public static String getLogin_type(HttpServletRequest request) {
        Object object =request.getSession().getAttribute("user");
        if(object instanceof EtpsInfo)
        return user.getLogin_type();
    }
    public static String getLogin_iAgent_id(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getiAgent_id();
    }
    public static String getLogin_iAgent_name(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getiAgent_name();
    }
    public static String getPhone(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getPhone();
    }
    public static String getAddress(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getAddress();
    }
    public static String getEmail(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getEmail();
    }

    /**
     * 商户类角色工具
     * @param request
     * @return
     */
    public static String getEtps_id(HttpServletRequest request) {
        EtpsInfo etpsInfo = (EtpsInfo) request.getSession().getAttribute("user");
        return etpsInfo.getiEtps_id();
    }
    public static String getEtps_name(HttpServletRequest request) {
        EtpsInfo etpsInfo = (EtpsInfo) request.getSession().getAttribute("user");
        return etpsInfo.getEtps_name();
    }
}
