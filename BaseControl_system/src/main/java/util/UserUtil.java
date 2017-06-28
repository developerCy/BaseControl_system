package util;

import pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by changyu on 2017/6/21 0021.
 */
public class UserUtil {
    public static String getUser_name(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getUser_name();
    }
}
