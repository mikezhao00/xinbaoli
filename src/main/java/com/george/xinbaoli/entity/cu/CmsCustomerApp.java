package com.george.xinbaoli.entity.cu;

import com.george.xinbaoli.annotation.Column;
import com.george.xinbaoli.annotation.Id;
import com.george.xinbaoli.annotation.Table;
import com.george.xinbaoli.entity.BaseEntity;

/**
 * 
 * <PRE>
 * Filename:    CmsCustomerApp.java
 * Description: 企业客户申请model
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月14日 下午3:44:59
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月14日      GONGZHAO     1.0         新建
 * </PRE>
 */
@Table(value="CMS_CUSTOMER_APP")
public class CmsCustomerApp extends BaseEntity {
	
	/**
	 * 主键
	 */
	private String ID;
	/**
	 * 客户名称
	 */
	private String cusName;
	/**
	 * 客户编号
	 */
	private String cusNo;
	/**
	 * 企业地址
	 */
	private String address;
	/**
	 * 企业法人
	 */
	private String manager;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 企业电话
	 */
	private String phone;
	
	/**
	 * 流程状态
	 */
	private String processState;
	
	@Id(value="ID")
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
    @Column(value = "CUSNAME")
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
    @Column(value = "CUSNO")
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
    @Column(value = "ADDRESS")	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    @Column(value = "MANAGER")	
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
    @Column(value = "REMARKS")	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    @Column(value = "PHONE")	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(value="PROCESS_STATE")
	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}
}
