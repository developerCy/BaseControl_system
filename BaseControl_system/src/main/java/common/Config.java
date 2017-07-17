package common;

import net.sf.json.JSONArray;

import java.util.List;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public class Config {
    //失败
    public static final String LOGIN_FALSE="{\"code\":-1,\"status\":\"false\",\"desc\":\"无效的用户名或密码！\"}";
    //用户状态无效
    public static final String USER_FASLE="{\"code\":-1,\"status\":\"false\",\"desc\":\"用户被停用！\"}";
    //成功
    public static final String SUCCESS(String desc){
        if(desc ==null){
            desc="null";
        }
        return "{\"code\":1,\"status\":\"success\",\"desc\":\""+desc+"\"}";
    }
    //其他异常
    public static   String  ERROR(String message){
        return "{\"code\":-1,\"status\":\"false\",\"desc\":\""+message+"\"}";
    }
    //Redis地址
    public static  final String REDIS_IP="localhost";
    //端口
    public static final int REDIS_PORT=6379;
    //标签名称
    public static final String TITLE="Base_control";
}
