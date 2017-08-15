package com.pm.organize.controller;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pm.organize.bean.UserAccount;

/**
 * 用户首页面
 * 
 * @author FYL5728
 */

@Controller
@RequestMapping("/organize/index")
public class IndexUserController extends OrganizeBaseController {

    /**
     * 用户首页
     * 
     * @return 首页
     */
    @RequestMapping("/userHome")
    public String userHome() {
        if (getCurrUser() != null) {
            String url=request.getParameter("url");
            if(url!=null){
                model.addAttribute("url", url);
            }
            return "/organize/index";
        }
        return "/index";
    }
    @RequestMapping("/getUserHome")
    @ResponseBody
    public  void getUserInfo(){
        JSONObject obj=new JSONObject();
        if (getCurrUser() != null) {
            UserAccount userAccount = organizeBusin.getUserAccount(getCurrUser().getId());
            Integer noticeCount=organizeBusin.getAdminNoticeCount(userAccount.getId());
            obj.put("userName", userAccount.getUserName());
            obj.put("noticeCount", noticeCount);
            obj.put("LoginName", userAccount.getUserMail());
          
          }
        writer(obj.toString());
    }
}
