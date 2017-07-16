package util;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;

/**
 * Created by changyu on 2017/7/13 0013.
 */
public class Phone_messageUtil {
    public static void send_message(String moudle_id,String phone,String msg) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest","23345241","1e19121c2d5b6595dc850e5164498366");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("炫生活");
        req.setSmsParam("{\"name\":\""+msg+"\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode(moudle_id);
        client.execute(req);
    }
}
