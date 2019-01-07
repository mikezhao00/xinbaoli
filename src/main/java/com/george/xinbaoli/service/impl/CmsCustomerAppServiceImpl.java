package com.george.xinbaoli.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.cu.CmsCustomerApp;
import com.george.xinbaoli.service.BaseServiceClient;
import com.george.xinbaoli.service.CmsCustomerAppService;
import com.george.xinbaoli.utils.XblBeanUtils;
@Service
public class CmsCustomerAppServiceImpl implements CmsCustomerAppService {

    @Autowired
    private BaseServiceClient baseServiceClient;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
	@Override
	public List<CmsCustomerApp> getCmsCustomerApps(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCmsCustomerApp(CmsCustomerApp cmsCustomerApp) {
        if(1 == baseServiceClient.insert(cmsCustomerApp)){
            return true;
        } else {
            return false;
        }
	}

	@Override
	public HashMap getCmsCustomerApp(long id) {
        return baseServiceClient.query(id, CmsCustomerApp.class);
	}

	@Override
	public boolean updateCmsCustomerApp(CmsCustomerApp cmsCustomerApp) {
        if(1 == baseServiceClient.update(cmsCustomerApp)){
            return true;
        } else {
            return false;
        }
	}

	@Override
	public boolean deleteCmsCustomerApp(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
	/**
	 * 更改业务流程状态#{CmsCustomerAppServiceImpl.updateBizStatus(execution,"tj")}
	 * @param execution
	 * @param status
	 */
	public void updateBizStatus(DelegateExecution execution,String status) {
		String bizId = execution.getProcessBusinessKey();
		//根据业务id自行处理业务表
		Map<String, Object> map = this.getCmsCustomerApp(Long.parseLong(bizId));
		CmsCustomerApp cmsCustomerApp = (CmsCustomerApp) XblBeanUtils.mapToObjectByApacheUtils(map, CmsCustomerApp.class);
		if(cmsCustomerApp != null) {
			cmsCustomerApp.setProcessState(status);
			this.updateCmsCustomerApp(cmsCustomerApp);
		}
			
		System.out.println("业务表["+bizId+"]状态更改成功，状态更改为："+status);
	}
	
	
	//流程节点权限用户列表${CmsCustomerAppServiceImpl.findUsers(execution,sign)}
	public List<String> findUsersForSL(DelegateExecution execution){
		return Arrays.asList("sly1","sly2");
	}
	
	//流程节点权限用户列表${CmsCustomerAppServiceImpl.findUsers(execution,sign)}
	public List<String> findUsersForSP(DelegateExecution execution){
		return Arrays.asList("spy1","uspy2");
	}


}
