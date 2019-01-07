package com.george.xinbaoli.entity.cu;

import com.george.xinbaoli.annotation.Column;
import com.george.xinbaoli.annotation.Id;
import com.george.xinbaoli.annotation.Table;

/**
 * <PRE>
 * Filename:    Customer.java
 * Description: 在这里添加类的文档注释
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年11月29日 下午4:58:09
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年11月29日      GONGZHAO     1.0         新建
 * </PRE>
 */
@Table(value = "CMS_CUSTOMER_INFO")
public class Customer {
	private String id;
	private String cusName;
	private String cusNo;
	private String address;
	private String manager;
	private String remarks;
	private String phone;
	
	@Id(value = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    @Column(value = "cusName")
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
    @Column(value = "cusNo")
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
    @Column(value = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    @Column(value = "manager")
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
    @Column(value = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    @Column(value = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
