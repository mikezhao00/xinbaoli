package com.george.xinbaoli.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.UserRoleInfo;

public interface UserService {

    /**
     * 批量获取用户信息
     *
     * @param paramMap
     * @return List<UserInfo>
     */
    public List<UserInfo> getUserInfos(Map<String, Object> paramMap);

    /**
     * 获取用户角色信息
     *
     * @param paramMap
     * @return List<UserRoleInfo>
     */
    public List<UserRoleInfo> getUserRoleInfos(Map<String, Object> paramMap);

    /**
     * 新增用户信息
     *
     * @param userInfo
     */
    public boolean addUserInfo(UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    public HashMap getUserInfo(long id);

    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    public boolean updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param id
     */
    public boolean deleteUserInfo(long id);
}
