package com.george.xinbaoli.mapper.cu;

import java.util.List;
import java.util.Map;

import com.george.xinbaoli.entity.cu.Customer;

/**
 * 
 * <PRE>
 * Filename:    CustomerInfoMapper.java
 * Description: 企业客户管理
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月12日 下午2:37:35
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月12日      GONGZHAO     1.0         新建
 * </PRE>
 */
public interface CustomerInfoMapper {

	/**
	 * 
	 * @Description: 分页查询企业客户信息
	 * @author GONGZHAO 
	 * @Created 2018年12月12日下午2:41:23
	 * @since 1.0
	 * @param paramMap
	 * @return
	 */
	List<Customer> queryCustomerInfoByPage(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Description: 查询企业客户信息数量
	 * @author GONGZHAO 
	 * @Created 2018年12月12日下午2:41:57
	 * @since 1.0
	 * @param paramMap
	 * @return
	 */
	int countCustomerInfoByParam(Map<String,Object> paramMap);
}
