package com.common.common.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.organize.controller.OrganizeBaseController;
import com.pm.organize.util.RSAUtils;

@Controller
@RequestMapping("/common/index")
public class IndexController extends OrganizeBaseController {

    /** 保存密钥 */
    public static final String SECURITYKEY = "Security_Key";

    /**
     * 系统首页面供应商
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/common/index";
    }
    
    /**
     * @Description 管理员登录页面
     * @author Chu Zhaocheng
     * @date 2017年6月22日 下午2:33:56
     * @action admin
     * @return String
     */
    @RequestMapping(value = "/admin")
    public String admin() {
        return "/common/adminLogin";
    }
    
    /**
     * @Description 供应商登录页面
     * @author Chu Zhaocheng
     * @date 2017年6月22日 下午2:34:28
     * @action supplier
     * @return String
     */
    @RequestMapping(value = "/supplier")
    public String supplier() {
        return "/common/supplierLogin";
    }

    /**
     * 获取加密系数和指数
     */
    @RequestMapping(value = "/jsonSecurityKey")
    @ResponseBody
    public void jsonSecurityKey() {
        HashMap<String, Object> map = RSAUtils.getKeyPairMap();
        request.getSession().setAttribute(SECURITYKEY, map);
        writer(RSAUtils.getPublicKeyToJson(map));
    }
}
