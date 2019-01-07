package com.george.xinbaoli.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.UserRoleInfo;
import com.george.xinbaoli.entity.cu.Customer;

/**
 * 
 * <PRE>
 * Filename:    CustomerService.java
 * Description: 企业客户操作接口
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月11日 下午5:07:25
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月11日      GONGZHAO     1.0         新建
 * </PRE>
 */
public interface CustomerService {

    /**
     * 批量获取企业客户信息
     *
     * @param paramMap
     * @return List<Customer>
     */
    public List<Customer> getCustomerInfos(Map<String, Object> paramMap);
    
    /**
     * 
     * @Description: 获取企业客户数量
     * @author GONGZHAO 
     * @Created 2018年12月12日下午3:04:35
     * @since 1.0
     * @param paramMap
     * @return
     */
    public int countCustomerInfosByParam(Map<String, Object> paramMap);


    /**
     * 新增企业客户信息
     *
     * @param customer
     */
    public boolean addCustomerInfo(Customer customer);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    public HashMap getUserInfo(String id);

    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    public boolean updateCustomerInfo(Customer customer);

    /**
     * 删除用户信息
     *
     * @param id
     */
    public boolean deleteCustomerInfo(String id);
}
