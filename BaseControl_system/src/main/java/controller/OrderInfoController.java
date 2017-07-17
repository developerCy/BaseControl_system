package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Order_info;
import service.Ps_orderinfo_service;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changyu on 2017/7/13 0013.
 */
@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    private Ps_orderinfo_service ps_orderinfo_service;

    /**
     * 渠道商查看商户交易明细
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/select_all_etps_order_info")
    public ModelAndView select_all_etps_order_info(Model model, HttpServletRequest request, HttpServletResponse response){
        try{
            Order_info order_info=new Order_info();
            String iAgent_id=null;
            if(!"管理员".equals(UserUtil.getLogin_type(request))){
                iAgent_id=UserUtil.getUser_id(request);
                order_info.setiAgent_id(iAgent_id);
            }
                List<Order_info> order_info_list= ps_orderinfo_service.select_etps_order_info(order_info);
                Map<String,String> order_success= ps_orderinfo_service.select_etps_order_count_pay_success(order_info);
                Map<String,String> order_refund= ps_orderinfo_service.select_etps_order_count_refund(order_info);
                model.addAttribute("order_info_list",order_info_list);
                model.addAttribute("order_success",order_success);
                model.addAttribute("order_refund",order_refund);
            return new ModelAndView("order_list");
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("../error");
        }
    }
}
