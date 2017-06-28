package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by changyu on 2017/2/28 0028.
 */
public class DateUtil {
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String DTLONG                  = "yyyyMMddHHmmss";

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String SIMPLE                  = "yyyy-MM-dd HH:mm:ss";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String DTSHORT                 = "yyyyMMdd";

    /**
     * 获取时间指定格式
     * @param style
     * @return
     */
    public static String getTimestamp(String style){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(style);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定时间之后/之前的时间
     * @param style
     * @return
     */
    public static String after_time_minute(String style,int minute){
        long currtime = System.currentTimeMillis();
        if(minute > 0 ){
            currtime = currtime + minute*60*1000;
        }else{
            currtime = currtime - minute*60*1000;
        }
        Date date = new Date(currtime);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(style);
        return simpleDateFormat.format(date);


    }
}
