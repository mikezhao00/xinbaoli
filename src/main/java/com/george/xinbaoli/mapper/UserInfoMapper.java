package com.george.xinbaoli.mapper;

import java.util.List;
import java.util.Map;

import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.UserRoleInfo;

public interface UserInfoMapper {

    /**
     * 获取用户信息
     * @param paramMap
     * @return List<UserInfo>
     */
    List<UserInfo> getUserInfos(Map<String, Object> paramMap);

    /**
     * 获取用户角色信息
     * @param paramMap
     * @return List<UserRoleInfo>
     */
    List<UserRoleInfo> getUserRoleInfos(Map<String, Object> paramMap);
}
