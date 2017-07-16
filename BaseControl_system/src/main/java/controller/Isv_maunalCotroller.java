package controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import common.AliPayConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.EtpsInfo;
import service.Ps_etps_service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by changyu on 2017/7/13 0013.
 */
@Controller
@RequestMapping("/isv")
public class Isv_maunalCotroller {
    Logger log=Logger.getLogger(Isv_maunalCotroller.class);
    @Autowired
    private Ps_etps_service ps_etps_service;
    @RequestMapping("/auth")
    public void isv_auth(@RequestParam("app_auth_code")String app_auth_code,
                         @RequestParam("app_id")String app_id,
            HttpServletResponse response){
        try{
            AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.ALI_GATE_WAY,AliPayConfig.ISV_APPID,AliPayConfig.ALI_PAY_ISV_PRIVATE_KEY, "json","gbk",AliPayConfig.ALI_PAY_PUBLIC_KEY, "RSA");
            AlipayOpenAuthTokenAppRequest req = new AlipayOpenAuthTokenAppRequest();
            req.setBizContent("{" +
                    "    \"grant_type\":\"authorization_code\"," +
                    "    \"code\":\""+app_auth_code+"\"" +
                    "  }");
            AlipayOpenAuthTokenAppResponse resp= alipayClient.execute(req);
            String app_auth_token=resp.getAppAuthToken();
            String user_id=resp.getUserId();
            String etps_app_id=resp.getAuthAppId();
            log.info("ISV授权商户返回："+resp.getBody());
            EtpsInfo etpsInfo=new EtpsInfo();
            etpsInfo.setAlipay_token(app_auth_token);
            etpsInfo.setAlipay_appid(etps_app_id);
            etpsInfo.setAlipay_pid(user_id);
            ps_etps_service.update_etpsinfo(etpsInfo);
            response.getWriter().println("<h1>商户appid:"+app_id+"</h1></br><h1>商户user_id:"+user_id+"</h1>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
