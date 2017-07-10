package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.EtpsInfo;
import service.Ps_etps_service;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/10 0010.
 */
@Controller
@RequestMapping("/etps")
public class EtpsInfoController {
    @Autowired
    private Ps_etps_service ps_etps_service;
    @RequestMapping("/etpsinfo_list")
    public ModelAndView etpsinfo_list(Model model, HttpServletRequest request){
        try {
            String login_type = UserUtil.getLogin_type(request);
            if ("管理员".equals(login_type)) {
                List<EtpsInfo> etpsInfoList = ps_etps_service.select_etpsinfo(null);
                model.addAttribute("etps_info_list", etpsInfoList);
            }
            if ("渠道商".equals(login_type)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_name", request.getParameter("user_name"));
                List<EtpsInfo> etpsInfoList = ps_etps_service.select_etpsinfo(map);
                model.addAttribute("etps_info_list", etpsInfoList);
            }
            return new ModelAndView("etps_list");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute(e.getMessage());
            return new ModelAndView("../error");
        }
    }
    @RequestMapping("/etpsinfo_save")
    public ModelAndView save(
            @RequestParam(value = "contact_name",required = true)String contact_name,
            @RequestParam(value = "contact_phone",required = true)String contact_phone,
            @RequestParam(value = "contact_address",required = true)String contact_address,
            @RequestParam(value = "contact_mail",required = true)String contact_mail,
            @RequestParam(value = "etps_name",required = true)String etps_name,
            @RequestParam(value = "etps_login_name",required = true)String etps_login_name,
            @RequestParam(value = "status",required = true)String status,
            @RequestParam(value = "sign_channel",required = true)String sign_channel,
            Model model,HttpServletRequest request){
        try{

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute(e.getMessage());
            return new ModelAndView("../error");
        }
        return null;
    }
}
