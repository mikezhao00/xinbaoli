package com.george.xinbaoli.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.george.xinbaoli.entity.UserInfo;
import com.george.xinbaoli.entity.cu.CmsCustomerApp;
import com.george.xinbaoli.entity.cu.Customer;
import com.george.xinbaoli.service.CmsCustomerAppService;
import com.george.xinbaoli.service.WorkFlowCommonService;
import com.george.xinbaoli.utils.IdGenerateUtil;

/**
 * 
 * <PRE>
 * Filename:    WorkFlowController.java
 * Description: Activiti工作流Controller
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月13日 下午3:15:50
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月13日      GONGZHAO     1.0         新建
 * </PRE>
 */
@RestController
@RequestMapping("/workFlow")
public class WorkFlowController {
	
	@Autowired
	RepositoryService repositoryService;

	@Autowired  
	private RuntimeService runtimeService;  
	
	@Autowired  
	private TaskService taskService; 
	
	@Autowired
	private CmsCustomerAppService cmsCustomerAppService;
	
	@Autowired
	private WorkFlowCommonService workFlowCommonService;


	@RequestMapping("/firstDemo")
	public void firstDemo() {
 
		//根据bpmn文件部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("demo1.bpmn").deploy();
		//获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("流程创建成功，当前流程实例ID："+processId);
		
		Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第一次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());
 
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第二次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());
 
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("task为null，任务执行完毕："+task);

	}
	
	@RequestMapping("/submitCmsCustomerApp")
	public String submitCmsCustomerApp(@RequestBody CmsCustomerApp cmsCustomerApp) {
		String msg = "提交成功";
		cmsCustomerApp.setID(IdGenerateUtil.uuidGenerate());
		//保存业务数据
		cmsCustomerAppService.addCmsCustomerApp(cmsCustomerApp);
		//启动流程
		workFlowCommonService.startProcesses("customerApplyFlow", cmsCustomerApp.getID());
		return msg;
	}
	@RequestMapping("/findTasksByUserId")
	public List<Task> findTasksByUserId(HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		System.out.println();
		Map  map = request.getParameterMap();
		Method[] ms = map.getClass().getDeclaredMethods();
		for (Method method : ms) {
			if(method.getName().equals("setLocked")){
				method.invoke(map, false);
			}
		}
		map.put("AA", "ssd");
		System.out.println(request.getParameterMap().get("AA"));
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		List<Task> taskList = workFlowCommonService.findTasksByUserId("customerApplyFlow", user.getUserCode());
		return taskList;
	}
}
