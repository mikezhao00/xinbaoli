package com.george.xinbaoli.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.george.xinbaoli.entity.cu.CmsCustomerApp;

/**
 * 
 * <PRE>
 * Filename:    CmsCustomerAppService.java
 * Description: 企业客户流程申请service
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月17日 上午9:55:06
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月17日      GONGZHAO     1.0         新建
 * </PRE>
 */
public interface CmsCustomerAppService{


	/**
	 * 
	 * @Description: 在这里添加方法的注释
	 * @author GONGZHAO 
	 * @Created 2018年12月17日上午9:56:37
	 * @since 1.0
	 * @param paramMap
	 * @return
	 */
    public List<CmsCustomerApp> getCmsCustomerApps(Map<String, Object> paramMap);

    /**
     * 新增用户信息
     *
     * @param cmsCustomerApp
     */
    public boolean addCmsCustomerApp(CmsCustomerApp cmsCustomerApp);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    public HashMap getCmsCustomerApp(long id);

    /**
     * 更新用户信息
     *
     * @param cmsCustomerApp
     */
    public boolean updateCmsCustomerApp(CmsCustomerApp cmsCustomerApp);

    /**
     * 删除用户信息
     *
     * @param id
     */
    public boolean deleteCmsCustomerApp(long id);
}
