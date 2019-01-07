package com.george.xinbaoli.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.george.xinbaoli.common.IConstants;
import com.george.xinbaoli.entity.JsonBean;
import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.UserRoleInfo;
import com.george.xinbaoli.service.UserService;
import com.george.xinbaoli.utils.ParamUtil;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    
    @Autowired
	private UserService userService;
    @RequestMapping(value = "/login")
    public String login() {
        return "/login";
    }
    
    @RequestMapping(value = "/success")
    public String success() {
        return "/menupages/successPage"; 
    }
    


    @ResponseBody
    @RequestMapping(value = "/login_in", produces = "application/json;charset=UTF-8")
    public JsonBean loginIn(HttpServletRequest request) {
        JsonBean reJson = new JsonBean();
        Map paramMap = ParamUtil.handleServiceParameter(request);
        String userCode = MapUtils.getString(paramMap, "userCode");
        String userPwd = MapUtils.getString(paramMap, "userPwd");
        // shiro认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, userPwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            reJson.setMessage("账户不存在");
            return reJson;
        } catch (DisabledAccountException e) {
            reJson.setMessage("账户存在问题");
            return reJson;
        } catch (AuthenticationException e) {
            reJson.setMessage("密码错误");
            return reJson;
        } catch (Exception e) {
            log.info("登陆异常", e);
            reJson.setMessage("登陆异常");
            return reJson;
        }
        reJson.setStatus(IConstants.RESULT_INT_SUCCESS);
        String res = subject.getPrincipals().toString();
        if (subject.hasRole("admin")) {
            res = res + "----------你拥有admin权限";
        }
        if (subject.hasRole("guest")) {
            res = res + "----------你拥有guest权限";
        }
        reJson.setData(res);
        reJson.setMessage("登陆成功");
        //将登录人信息保存到session中
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("userCode", userCode);
        List<UserInfo> list = userService.getUserInfos(paramMap2);
        if(null != list && list.size()>0)
        	request.getSession().setAttribute("user", list.get(0));
        return reJson;
    }
}
