package util;

import pojo.EtpsInfo;
import pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by changyu on 2017/6/21 0021.
 */
public class UserUtil {
    public static String getUser_name(HttpServletRequest request) {
        Object object =request.getSession().getAttribute("user");
        if(object instanceof EtpsInfo){
            return ((EtpsInfo)object).getEtps_name();
        }
        if(object instanceof User){
            return ((User)object).getiAgent_name();
        }
        return null;
    }
    public static String getLogin_type(HttpServletRequest request) {
        Object object =request.getSession().getAttribute("user");
        if(object instanceof EtpsInfo){
            return "商户";
        }
        if(object instanceof User){
            return ((User)object).getLogin_type();
        }
        return null;
    }
    public static String getUser_id(HttpServletRequest request) {
        Object object =request.getSession().getAttribute("user");
        if(object instanceof EtpsInfo){
            return ((EtpsInfo)object).getiEtps_id();
        }
        if(object instanceof User){
            return ((User)object).getiAgent_id();
        }
        return null;
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
}
