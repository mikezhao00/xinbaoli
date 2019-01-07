package com.george.xinbaoli.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.george.xinbaoli.service.WorkFlowCommonService;

/**
 * <PRE>
 * Filename:    WorkFlowCommonServiceImpl.java
 * Description: 工作流实现类
 * </PRE>
 * @author      GONGZHAO
 * @version     1.0
 * <PRE>
 * Create at:   2018年12月18日 上午10:20:23
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2018年12月18日      GONGZHAO     1.0         新建
 * </PRE>
 */
@Service
public class WorkFlowCommonServiceImpl implements WorkFlowCommonService {


	@Autowired  
	private RuntimeService runtimeService;  
	@Autowired  
	private TaskService taskService;  
	@Autowired  
	private HistoryService historyService;  
	@Autowired  
	private RepositoryService repositoryService;  
	@Autowired  
	private ProcessEngineConfigurationImpl processEngineConfiguration; 
	
	private Logger log = LoggerFactory.getLogger(WorkFlowCommonServiceImpl.class);
	
	@Override
	public String startProcesses(String processId, String bizId) {
		log.info("process ready start,processId= "+processId+" ,bizId = "+bizId);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processId, bizId);//流程图id，业务表id		
		log.info("process is started , pi.getId() = "+pi.getId());
		return pi.getId();
	}

	@Override
	public List<Task> findTasksByUserId(String processId, String userId) {
		List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey(processId).taskCandidateOrAssigned(userId).list();
		return resultTask;

	}

	@Override
	public String completeTask(String taskId, String userId, String result) {

		//获取流程实例
		taskService.claim(taskId, userId);
		
		Map<String,Object> vars = new HashMap<String,Object>();
		vars.put("sign", result);
		taskService.complete(taskId, vars);
		return "success";
	}

	@Override
	public void queryProImg(String processInstanceId) {
		//获取历史流程实例  
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();  
       
		//根据流程定义获取输入流
		InputStream is = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
		FileOutputStream fos = null;
		try {
			BufferedImage bi = ImageIO.read(is);
			File file = new File("demo2.png");
			if(!file.exists()) file.createNewFile();
			fos = new FileOutputStream(file);
			ImageIO.write(bi, "png", fos);
		} catch (Exception e) {
			log.info(e.getMessage());
		}finally {
			try {
				if(fos!=null)
					fos.close();
				if(is !=null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		log.info("图片生成成功");
 
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("userId").list();
		for(Task t : tasks) {
			System.out.println(t.getName());
		}

	}

	@Override
	public void queryProHighLighted(String processInstanceId) {
		
		//获取历史流程实例
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();  
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());  

        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();  

        //高亮环节id集合  
        List<String> highLightedActivitis = new ArrayList<String>();
      //高亮线路id集合  
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList); 

        for(HistoricActivityInstance tempActivity : highLightedActivitList){  
            String activityId = tempActivity.getActivityId();  
            highLightedActivitis.add(activityId);  
        }  
        //配置字体
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows,"宋体","微软雅黑","黑体",null,2.0);
        FileOutputStream fos = null;
        try {
            BufferedImage bi = ImageIO.read(imageStream);
            File file = new File("demo2.png");
            if(!file.exists()) file.createNewFile();
            fos = new FileOutputStream(file);
            ImageIO.write(bi, "png", fos);
		} catch (Exception e) {
			log.info(e.getMessage());
		}finally {

	        try {
	        	if(fos!=null)
	        		fos.close();
	        	if(imageStream != null)
	        		imageStream.close();
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}

	}

	   /**  
     * 获取需要高亮的线  
     * @param processDefinitionEntity  
     * @param historicActivityInstances  
     * @return  
     */  
    private List<String> getHighLightedFlows(  
            ProcessDefinitionEntity processDefinitionEntity,  
            List<HistoricActivityInstance> historicActivityInstances) {  
          
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId  
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历  
            ActivityImpl activityImpl = processDefinitionEntity  
                    .findActivity(historicActivityInstances.get(i)  
                            .getActivityId());// 得到节点定义的详细信息  
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点  
            ActivityImpl sameActivityImpl1 = processDefinitionEntity  
                    .findActivity(historicActivityInstances.get(i + 1)  
                            .getActivityId());  
            // 将后面第一个节点放在时间相同节点的集合里  
            sameStartTimeNodes.add(sameActivityImpl1);  
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {  
                HistoricActivityInstance activityImpl1 = historicActivityInstances  
                        .get(j);// 后续第一个节点  
                HistoricActivityInstance activityImpl2 = historicActivityInstances  
                        .get(j + 1);// 后续第二个节点  
                if (activityImpl1.getStartTime().equals(  
                        activityImpl2.getStartTime())) {  
                    // 如果第一个节点和第二个节点开始时间相同保存  
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity  
                            .findActivity(activityImpl2.getActivityId());  
                    sameStartTimeNodes.add(sameActivityImpl2);  
                } else {  
                    // 有不相同跳出循环  
                    break;  
                }  
            }  
            List<PvmTransition> pvmTransitions = activityImpl  
                    .getOutgoingTransitions();// 取出节点的所有出去的线  
            for (PvmTransition pvmTransition : pvmTransitions) {  
                // 对所有的线进行遍历  
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition  
                        .getDestination();  
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示  
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {  
                    highFlows.add(pvmTransition.getId());  
                }  
            }  
        }  
        return highFlows;  
	}
}
