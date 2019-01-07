package com.george.xinbaoli.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.george.xinbaoli.entity.cu.Customer;
import com.george.xinbaoli.service.CustomerService;
import com.george.xinbaoli.utils.IdGenerateUtil;

/**
 * <PRE>
 * Filename:    CustomerMngController.java
 * Description: 企业客户管理控制器
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年11月29日 下午4:49:27
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月29日      GONGZHAO     1.0         新建
 * </PRE>
 */
@Controller
@RequestMapping(value="/customerMng")
public class CustomerMngController {
	
	@Autowired
	CustomerService customerService;

    @RequestMapping(value = "/goCustomerMngPage")
    public String goCustomerMngPage() {
        return "/menupages/customerMngPage";
    }
    
    @RequestMapping(value = "/queryCustomer")
    public @ResponseBody Map<String,Object> queryCustomer(int limit, int page)
    {
    	//构建查询参数
    	Map<String,Object> paramMap = new HashMap<>();
    	paramMap.put("offSet", (page-1)*limit);
    	paramMap.put("limit", page*limit);

        List<Customer> customerList ;
        customerList = customerService.getCustomerInfos(paramMap);
        int total = customerService.countCustomerInfosByParam(paramMap);
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", customerList);
        return map;
    }
    @RequestMapping(value = "/saveCustomer") 
    @ResponseBody
    public String saveCustomer(Model model,@RequestBody Customer customer) {
    	customer.setId(IdGenerateUtil.uuidGenerate());
    	customerService.addCustomerInfo(customer);
    	return "success";
    }
}
