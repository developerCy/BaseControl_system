package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by changyu on 2017/6/19 0019.
 */
public class JsonUtil {
    public static void sendJson(HttpServletResponse response,String message){
        PrintWriter printWriter=null;
        try {
            response.setContentType("application/json;charset=utf-8");
            printWriter=response.getWriter();
            printWriter.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.close();
        }


    }
}
