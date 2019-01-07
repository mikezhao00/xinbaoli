package com.george.xinbaoli.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.george.xinbaoli.entity.cu.Customer;
import com.george.xinbaoli.mapper.cu.CustomerInfoMapper;
import com.george.xinbaoli.service.BaseServiceClient;
import com.george.xinbaoli.service.CustomerService;

@Service(value="customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BaseServiceClient baseServiceClient;
    
    @Autowired 
    private CustomerInfoMapper customerInfoMapper;

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Override
	public List<Customer> getCustomerInfos(Map<String, Object> paramMap) {
		return customerInfoMapper.queryCustomerInfoByPage(paramMap);
	}

	@Override
	public int countCustomerInfosByParam(Map<String, Object> paramMap) {
		return customerInfoMapper.countCustomerInfoByParam(paramMap);
	}

	@Override
	public boolean addCustomerInfo(Customer customer) {
        if(1 == baseServiceClient.insert(customer)){
            return true;
        } else {
            return false;
        }
	}

	@Override
	public HashMap getUserInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCustomerInfo(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomerInfo(String id) {
		// TODO Auto-generated method stub
		return false;
	}


}
