package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.EtpsInfo;
import service.Ps_etps_service;
import service.Ps_userinfo_service;
import util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/10 0010.
 */
@Controller
@RequestMapping("/etps")
public class EtpsInfoController {
    private Logger log = Logger.getLogger(EtpsInfoController.class);
    @Autowired
    private Ps_etps_service ps_etps_service;
    @Autowired
    private Ps_userinfo_service ps_userinfo_service;

    /**
     * 商户信息列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/etpsinfo_list")
    public ModelAndView etpsinfo_list(Model model, HttpServletRequest request) {
        try {
            String login_type = UserUtil.getLogin_type(request);
            String iAgent_id=UserUtil.getLogin_iAgent_id(request);
            if ("管理员".equals(login_type)) {
                List<EtpsInfo> etpsInfoList = ps_etps_service.select_etpsinfo(null);
                String use_all=ps_etps_service.select_use_all();
                String count_all=ps_etps_service.select_count_all();
                String wait_check=ps_etps_service.select_checked_all("待审核");
                model.addAttribute("wait_check", StringUtil.defaultString(wait_check,"0"));
                model.addAttribute("checked",StringUtil.defaultString(use_all,"0"));
                model.addAttribute("count_all",StringUtil.defaultString(count_all,"0"));
                model.addAttribute("etps_info_list", etpsInfoList);
                if("check".equals(request.getParameter("sign"))){
                    return new ModelAndView("etps_check");
                }
            }
            if ("渠道商".equals(login_type)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("iAgent_id",iAgent_id);
                List<EtpsInfo> etpsInfoList = ps_etps_service.select_etpsinfo(map);
                model.addAttribute("etps_info_list", etpsInfoList);
            }
            return new ModelAndView("etps_list");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }

    /**
     * 商户签约信息
     *
     * @param contact_name
     * @param contact_phone
     * @param contact_address
     * @param etps_name
     * @param etps_login_name
     * @param alipay_account
     * @param alipay_account_name
     * @param alipay_appid
     * @param alipay_pid
     * @param alipay_rate
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/etpsinfo_save")
    public ModelAndView save(
            @RequestParam(value = "iEtps_id") String iEtps_id,
            @RequestParam(value = "contact_name") String contact_name,
            @RequestParam(value = "contact_phone") String contact_phone,
            @RequestParam(value = "contact_address") String contact_address,
            @RequestParam(value = "contact_mail") String contact_mail,
            @RequestParam(value = "etps_name") String etps_name,
            @RequestParam(value = "etps_login_name") String etps_login_name,
            @RequestParam(value = "alipay_account", required = false) String alipay_account,
            @RequestParam(value = "alipay_account_name", required = false) String alipay_account_name,
            @RequestParam(value = "alipay_appid", required = false) String alipay_appid,
            @RequestParam(value = "alipay_pid", required = false) String alipay_pid,
            @RequestParam(value = "alipay_rate", required = false) String alipay_rate,
            Model model, HttpServletRequest request) {
        try {
            EtpsInfo etpsInfo = new EtpsInfo();
            etpsInfo.setiEtps_id(iEtps_id);
            etpsInfo.setiAgent_id(UserUtil.getLogin_iAgent_id(request));
            etpsInfo.setiAgent_name(UserUtil.getUser_name(request));
            etpsInfo.setContact_name(contact_name);
            etpsInfo.setContact_phone(contact_phone);
            etpsInfo.setContact_address(contact_address);
            etpsInfo.setContact_mail(contact_mail);
            etpsInfo.setEtps_name(etps_name);
            etpsInfo.setEtps_login_name(etps_login_name);
            etpsInfo.setAlipay_account(alipay_account);
            etpsInfo.setAlipay_account_name(alipay_account_name);
            etpsInfo.setAlipay_appid(alipay_appid);
            etpsInfo.setAlipay_pid(alipay_pid);
            etpsInfo.setAlipay_rate(alipay_rate);
            etpsInfo.setEtps_login_password(Md5Util.getMD5("123456"));
            etpsInfo.setCreate_time(DateUtil.getTimestamp(DateUtil.SIMPLE));
            etpsInfo.setCheck_status("待审核");
            if("update".equals(request.getParameter("sign"))){
                etpsInfo.setCheck_status("待审核");
                etpsInfo.setModify_time(DateUtil.getTimestamp(DateUtil.SIMPLE));
                ps_etps_service.update_etpsinfo(etpsInfo);
            }else{
                etpsInfo.setStatus("新入网");
                ps_etps_service.insert_etpsinfo(etpsInfo);
            }
            return new ModelAndView("etps_list");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }

    /**
     * 获取商户信息
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getEtps_info")
    public ModelAndView get_etps_info(Model model, HttpServletRequest request) {
        try {
            String iEtps_id = request.getParameter("iEtps_id");
            Map<String, String> map = new HashMap<String, String>();
            map.put("iEtps_id",iEtps_id);
            List<EtpsInfo> etpsInfoList = ps_etps_service.select_etpsinfo(map);
            model.addAttribute("etps_info", etpsInfoList.get(0));
            if("check".equals(request.getParameter("sign"))){
                model.addAttribute("check","check");
            }
            if("update".equals(request.getParameter("sign"))){
                model.addAttribute("update","update");
            }
            return new ModelAndView("etps_edit");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }

    /**
     * 删除商户信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/delEtps_info")
    public ModelAndView del_etps_info(Model model, HttpServletRequest request) {
        try {
            String iEtps_id = request.getParameter("iEtps_id");
            Map<String, String> map = new HashMap<String, String>();
            map.put("iEtps_id",iEtps_id);
            ps_etps_service.del_etpsinfo(map);
            return new ModelAndView("etps_list");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }

    /**
     * 审核商户
     * @param iEtps_id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/check")
    public ModelAndView check_etps_info(@RequestParam(value = "iEtps_id")String iEtps_id, Model model,HttpServletRequest request){
        try {
            Map<String,String> map=new HashMap<String, String>();
            EtpsInfo etpsInfo=new EtpsInfo();
            etpsInfo.setCheck_status(request.getParameter("check_status"));
            etpsInfo.setCheck_desc(request.getParameter("check_desc"));
            if("审核成功".equals(request.getParameter("check_status"))){
                etpsInfo.setStatus("已开通");
                map.put("iEtps_id",iEtps_id);
                EtpsInfo etpsInfo1=ps_etps_service.select_etpsinfo(map).get(0);
                String iAgent_id=etpsInfo1.getiAgent_id();
                map.put("iAgent_id",iAgent_id);
                map.put("message","商户："+etpsInfo1.getEtps_name()+"  审核通过！");
                map.put("create_time",DateUtil.getTimestamp(DateUtil.SIMPLE));
                ps_userinfo_service.insert_message(map);
                map.clear();
                map.put("iAgent_id",iAgent_id);
                String agent_phone=ps_userinfo_service.select_user_byName(map).get(0).getPhone();
                Phone_messageUtil.send_message("SMS_77275034",agent_phone,etpsInfo1.getEtps_name());
            }else if("驳回".equals(request.getParameter("check_status"))){
                map.put("iEtps_id",iEtps_id);
                EtpsInfo etpsInfo1=ps_etps_service.select_etpsinfo(map).get(0);
                String iAgent_id=etpsInfo1.getiAgent_id();
                map.put("iAgent_id",iAgent_id);
                map.put("message","商户："+etpsInfo1.getEtps_name()+"  审核被驳回，原因："+request.getParameter("check_desc"));
                map.put("create_time",DateUtil.getTimestamp(DateUtil.SIMPLE));
                ps_userinfo_service.insert_message(map);
                map.clear();
                map.put("iAgent_id",iAgent_id);
                String agent_phone=ps_userinfo_service.select_user_byName(map).get(0).getPhone();
                Phone_messageUtil.send_message("SMS_77275034",agent_phone,etpsInfo1.getEtps_name());
            }
            etpsInfo.setiEtps_id(iEtps_id);
            ps_etps_service.update_etpsinfo(etpsInfo);
            return new ModelAndView("etps_list");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }

    /**
     * 商户信息导出excel
     * @param request
     * @param response
     */
    @RequestMapping("/etps_out")
    public void etps_out(HttpServletRequest request, HttpServletResponse response){
        try{
            List<EtpsInfo> list=null;
            if("管理员".equals(UserUtil.getLogin_type(request))){
                list=ps_etps_service.select_etpsinfo(null);
            }
            if("渠道商".equals(UserUtil.getLogin_type(request))){
                String iAgent_id=UserUtil.getLogin_iAgent_id(request);
                Map<String,String> map=new HashMap<String, String>();
                map.put("iAgent_id",iAgent_id);
                list=ps_etps_service.select_etpsinfo(map);
            }
            ExcelUtil.out_Etps_info_list_to_Excel(list,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
