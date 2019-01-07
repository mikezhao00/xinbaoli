package com.george.xinbaoli.service;

import java.util.List;

import org.activiti.engine.task.Task;

/**
 * <PRE>
 * Filename:    WorkFlowCommonService.java
 * Description: 工作流公共Service
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月18日 上午10:02:34
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月18日      GONGZHAO     1.0         新建
 * </PRE>
 */
public interface WorkFlowCommonService {

	/**
	 * 
	 * @Description: 启动流程
	 * @author GONGZHAO 
	 * @Created 2018年12月18日上午10:04:52
	 * @since 1.0
	 * @param processId
	 * @param bizId
	 * @return
	 */
	public String startProcesses(String processId, String bizId);
	
	/**
	 * 
	 * @Description: 根据用户ID查询代办任务
	 * @author GONGZHAO 
	 * @Created 2018年12月18日上午10:07:07
	 * @since 1.0
	 * @param userId
	 * @return
	 */
	public List<Task> findTasksByUserId(String processId,String userId);
	
	/**
	 * 
	 * @Description: 任务审批
	 * @author GONGZHAO 
	 * @Created 2018年12月18日上午10:13:36
	 * @since 1.0
	 * @param taskId
	 * @param userId
	 * @param result
	 * @return
	 */
	public String completeTask(String taskId,String userId,String result);
	
	/**
	 * 
	 * @Description: 生成流程图
	 * @author GONGZHAO 
	 * @Created 2018年12月18日上午10:18:20
	 * @since 1.0
	 * @param processInstanceId
	 */
	public void queryProImg(String processInstanceId);
	
	/**
	 * 
	 * @Description: 流程图高亮显示
	 * @author GONGZHAO 
	 * @Created 2018年12月18日上午10:18:53
	 * @since 1.0
	 * @param processInstanceId
	 */
	public void queryProHighLighted(String processInstanceId);
	
}
