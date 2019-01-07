package com.george.xinbaoli.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.george.xinbaoli.common.IConstants;
import com.george.xinbaoli.entity.JsonBean;
import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/testAuthorization")
    @ApiOperation(value = "test", notes = "测试admin访问权限")
	public String testAuthorization() {
		
		return "你拥有admin访问权限";
	}
	
    @ApiOperation(value = "getUser", notes = "管理员接口获取用户")
    @RequestMapping(method = RequestMethod.GET, value = "/user")
	public String getUser(@RequestParam long id) {
		JsonBean reData = new JsonBean();
		Map user = userService.getUserInfo(id);
        reData.setStatus(IConstants.RESULT_INT_SUCCESS);
        reData.setMessage("查询成功");
        reData.setData(user);
        return JSONObject.toJSONString(reData);
	}
    @ApiOperation(value = "addUser", notes = "管理员接口新增用户")
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public String addUser(@RequestParam String user) {
        JsonBean reData = new JsonBean();
        UserInfo userInfo = (UserInfo) JSONObject.parseObject(user, UserInfo.class);
        if (userService.addUserInfo(userInfo)) {
            reData.setStatus(IConstants.RESULT_INT_SUCCESS);
            reData.setMessage("新增成功");
        } else {
            reData.setStatus(IConstants.RESULT_INT_ERROR);
            reData.setMessage("新增失败");
        }
        return JSONObject.toJSONString(reData);
    }

    @ApiOperation(value = "updateUser", notes = "管理员接口更新用户")
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public String updateUser(@RequestParam String user) {
        JsonBean reData = new JsonBean();
        UserInfo userInfo = (UserInfo) JSONObject.parseObject(user, UserInfo.class);
        if (userService.updateUserInfo(userInfo)) {
            reData.setStatus(IConstants.RESULT_INT_SUCCESS);
            reData.setMessage("更新成功");
        } else {
            reData.setStatus(IConstants.RESULT_INT_ERROR);
            reData.setMessage("更新失败");
        }
        return JSONObject.toJSONString(reData);
    }

    @ApiOperation(value = "delUser", notes = "管理员接口删除用户")
    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
    public String delUser(@RequestParam long id) {
        JsonBean reData = new JsonBean();
        if (userService.deleteUserInfo(id)) {
            reData.setStatus(IConstants.RESULT_INT_SUCCESS);
            reData.setMessage("删除成功");
        } else {
            reData.setStatus(IConstants.RESULT_INT_ERROR);
            reData.setMessage("删除失败");
        }
        return JSONObject.toJSONString(reData);
    }
}
