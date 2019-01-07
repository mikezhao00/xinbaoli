package com.george.xinbaoli.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.UserRoleInfo;
import com.george.xinbaoli.service.UserService;

/**
 * 
 * <PRE>
 * Filename:    MyRealm.java
 * Description: 认证和授权具体实现
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年11月7日 下午2:57:57
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月7日      GONGZHAO     1.0         新建
 * </PRE>
 */
public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		Map<String, Object> params = new HashMap<>();
		params.put("userCode", super.getAvailablePrincipal(principalCollection));
		List<UserRoleInfo> userRoleInfos = userService.getUserRoleInfos(params);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(!userRoleInfos.isEmpty()) {
			for (UserRoleInfo userRoleInfo : userRoleInfos) {
				info.addRole(userRoleInfo.getRoleCode());
			}
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
		Map<String, Object> params = new HashMap<>();
		params.put("userCode", authenticationToken.getPrincipal());
		List<UserInfo> userInfos = userService.getUserInfos(params);
        if (userInfos.isEmpty()) {
            throw new UnknownAccountException();
        } else if(userInfos.size() > 1) {
            throw new DisabledAccountException();
        } else {
            UserInfo user = userInfos.get(0);
            // 校验密码
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), user.getUserPwd(), ByteSource.Util.bytes("2w@W"),  getName());
        }
	}

}
