package util;

/**
 * Created by changyu on 2017/6/22 0022.
 */
public class StringUtil {
    public static String defaultString(String str,String default_str){
        if(str==""||str==null){
            str=default_str;
        }
        return str;
    }
}
