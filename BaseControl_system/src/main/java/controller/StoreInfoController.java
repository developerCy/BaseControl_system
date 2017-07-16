package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pojo.Store_info;
import service.Ps_store_service;
import util.StringUtil;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by changyu on 2017/7/13 0013.
 */
@Controller
@RequestMapping("/store")
public class StoreInfoController {
    @Autowired
    private Ps_store_service ps_store_service;

    /**
     * 门店信息列表
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/storeinfo_list")
    public ModelAndView store_info_list(Model model, HttpServletRequest request, HttpServletResponse response){
        try{
            List<Store_info> list=null;
            String count=null;
            String login_type= UserUtil.getLogin_type(request);
            if("管理员".equals(login_type)){
                list=ps_store_service.select_etps_order_info(null);
                count=ps_store_service.select_count(null);
            }
            if("渠道商".equals(login_type)){
                String iAgent_id=UserUtil.getLogin_iAgent_id(request);
                Store_info store_info=new Store_info();
                store_info.setiAgent_id(iAgent_id);
                list=ps_store_service.select_etps_order_info(store_info);
                count=ps_store_service.select_count(iAgent_id);

            }
            String koubei=ps_store_service.select_count_koubei();
            model.addAttribute("koubei_count", StringUtil.defaultString(koubei,"0"));
            model.addAttribute("count",StringUtil.defaultString(count,"0"));
            model.addAttribute("store_info_list",list);
            return new ModelAndView("store_list");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return new ModelAndView("../error");
        }
    }
    /**
     * 获取单个门店信息
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getStore_info")
    public ModelAndView get_store_info(Model model, HttpServletRequest request) {
        try {
            String store_id = request.getParameter("store_id");
            Store_info store_info=new Store_info();
            store_info.setiAgent_id(store_id);
            List<Store_info> store_infoList =ps_store_service.select_etps_order_info(store_info);
            model.addAttribute("store_info", store_infoList.get(0));
            return new ModelAndView("store_edit");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }
    /**
     * 门店保存/删除
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/store_info_edit")
    public ModelAndView store_info_edit(@RequestParam(value = "store_name",required = false)String store_name,
                                        @RequestParam(value = "store_id",required = false)String store_id,
                                        @RequestParam(value = "store_address",required = false)String store_address,
                                        @RequestParam(value = "business_area",required = false)String business_area,
                                        @RequestParam(value = "iAgent_id",required = false)String iAgent_id,
                                        @RequestParam(value = "iEtps_id",required = false)String iEtps_id,
                                        @RequestParam(value = "etps_name",required = false)String etps_name,
                                        @RequestParam(value = "alipay_shop_id",required = false)String alipay_shop_id,
                                        @RequestParam(value = "shop_owner_name",required = false)String shop_owner_name,
                                        @RequestParam(value = "sign")String sign,
            Model model, HttpServletRequest request) {
        try {
            Store_info store_info=new Store_info();
            if("update".equals(sign)){
                store_info.setiAgent_id(store_id);
                store_info.setStore_name(store_name);
                store_info.setStore_address(store_address);
                store_info.setAlipay_shop_id(alipay_shop_id);
                store_info.setBusiness_area(business_area);
                store_info.setShop_owner_name(shop_owner_name);
                ps_store_service.update_etps_order_info(store_info);
                return new ModelAndView("store_list");
            }
            if("new".equals(sign)){
                store_info.setiAgent_id(iAgent_id);
                store_info.setEtps_name(etps_name);
                store_info.setiEtps_id(iEtps_id);
                store_info.setStore_name(store_name);
                store_info.setStore_address(store_address);
                store_info.setShop_owner_name(shop_owner_name);
                store_info.setBusiness_area(business_area);
                store_info.setAlipay_shop_id(alipay_shop_id);
                ps_store_service.insert_etps_order_info(store_info);
                return new ModelAndView("store_list");
            }
            if("del".equals(sign)){
                if(store_id!=null){
                    store_info.setStore_id(store_id);
                }
                if(iAgent_id!=null){
                    store_info.setiAgent_id(iAgent_id);
                }
                if(iEtps_id!=null){
                    store_info.setiEtps_id(iEtps_id);
                }
                    ps_store_service.del_store(store_info);
                return new ModelAndView("store_list");

            }

            return new ModelAndView("store_edit");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("../error");
        }
    }
}
