package util;

/**
 * Created by changyu on 2017/3/1 0001.
 */
public class OrderNoUtil {
    public static String getOrderNo(){
        return System.currentTimeMillis()+String.valueOf((int)Math.random()*1000000+9000000);
    }
}
