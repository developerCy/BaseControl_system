package common;

import net.sf.json.JSONArray;

import java.util.List;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public class Config {
    //失败
    public static final String LOGIN_FALSE="{\"code\":-1,\"status\":\"false\",\"desc\":\"无效的用户名或密码！\"}";
    //成功
    public static final String SUCCESS="{\"code\":1,\"status\":\"success\"}";
    //用户存在
    public static final String USER_EXIST="{\"code\":\"-1\",\"desc\":\"用户名已经存在！\"}";
    //token无效
    public static final String TOKEN_REFUESD="{\"code\":\"-1\",\"desc\":\"token无效\"}";
    //session过期
    public static final String SESSION_REFUESD="{\"code\":\"-1\",\"desc\":\"登录超时,请重新登录！\"}";
    //服务端数据异常
    public static   String  ERROR(String message){
        return "{\"code\":-1,\"status\":\"false\",\"desc\":\""+message+"\"}";
    }
    //GridManager查询/FullSchedule查询
    public static String DATA_RETURN(JSONArray jsonArray){
        return "{\"data\":" + jsonArray.toString()+ ",\"totals\":" + jsonArray.size() + "}";
    }
    //Redis地址
    public static  final String REDIS_IP="localhost";
    //端口
    public static final int REDIS_PORT=6379;
}
