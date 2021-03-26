package com.example.test.flowabletest;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.app.AppModel;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@SpringBootTest
class FlowableTestApplicationTests {

    @Resource
    RepositoryService repositoryService;


    @Resource
    RuntimeService runtimeService;
    
    @Resource
    TaskService taskService;
    @Test
    void contextLoads() {
    
        // System.out.println(dataSource);
    }
    @Test
    void getRepositoryService() {
        
        System.out.println(repositoryService);
    }
    
    /*部署流程*/
    @Test
    void testRepositoryService() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("testExecutionListener.bpmn20.xml")
                .deploy();
        System.out.println("Deployment id : "+deploy.getId());
        System.out.println("Deployment 名字"+deploy.getName());
        // Deployment deploy = repositoryService.createDeployment()
        //         .addClasspathResource("holiday-request.bpmn20.xml")
        //         .deploy();
        // System.out.println("Deployment id : "+deploy.getId());
        // System.out.println("Deployment 名字"+deploy.getName());
    }
    @Test
    void deployRepositoryService() {
        
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        System.out.println("Deployment id : "+deploy.getId());
        System.out.println("Deployment 名字"+deploy.getName());
    }
    @Test
    void deployRepositoryService2() {
        
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("测试ExecutionListener.bpmn20.xml")
                .deploy();
        System.out.println("Deployment id : "+deploy.getId());
        System.out.println("Deployment 名字"+deploy.getName());
    }
    
    /*开启流程*/
    @Test
    void testStartInstance() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("peopleName1","qwert");
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("testExecutionListener",stringObjectHashMap);
    
        System.out.println("Deployment id : "+processInstance.getProcessInstanceId());
        System.out.println("Variable  : "+processInstance.getProcessVariables());
        System.out.println("Deployment 名字"+processInstance.getName());
    }
    @Test
    void testStartDeployInstance() {
       
        //必须要部署时，使用.bpmn20.xml结尾的文件
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest");
    
        System.out.println("ProcessInstance id : "+processInstance.getProcessInstanceId());
        System.out.println("ProcessInstance 名字"+processInstance.getName());
    }
    @Test
    void testStartDeployInstance2() {
       
        //必须要部署时，使用.bpmn20.xml结尾的文件
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("a1");
    
        System.out.println("ProcessInstance id : "+processInstance.getProcessInstanceId());
        System.out.println("ProcessInstance 名字"+processInstance.getName());
    }
    
    @Test
    void testStartDeployInstance3() {
       
        //必须要部署时，使用.bpmn20.xml结尾的文件
        
        Map<String, Object> map=new HashMap<>();
        map.put("employee0","jack");
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("a1",map);
    
        System.out.println("ProcessInstance id : "+processInstance.getProcessInstanceId());
        System.out.println("ProcessInstance 名字"+processInstance.getName());
    }
    
    /*执行流程*/
    @Test
    void runService(){
        HashMap<String, Object> variablesLocal = new HashMap<>();
        variablesLocal.put("local","南昌");
        HashMap<String, Object> variable = new HashMap<>();
        variable.put("temp","临时变量");
        taskService.createTaskQuery().taskAssignee("jack").list().forEach( a-> {System.out.println(a);
    
            taskService.setVariables(a.getId(),variable);
            taskService.setVariablesLocal(a.getId(),variablesLocal);
            taskService.complete(a.getId());
        });
        
    }
    
    @Test
    void runService2(){
        HashMap<String, Object> variable = new HashMap<>();
        variable.put("employee1","Hee");
        variable.put("employee3","Hee3");
        variable.put("employee4","Hee4");
        variable.put("employee2","Hee2");
        variable.put("employee6","Hee6");
        
        taskService.createTaskQuery().list().forEach( a-> {
            taskService.setVariables(a.getId(),variable);
            System.out.println("getScopeDefinitionId:"+a.getScopeDefinitionId());
            System.out.println("getScopeId:"+a.getScopeId());
            System.out.println("getId:"+a.getId());
            System.out.println("TreeMap getVariables:"+new TreeMap<>(taskService.getVariables(a.getId())));
            System.out.println("HashMap getVariables:"+taskService.getVariables(a.getId()).keySet().stream().sorted().collect(Collectors.toList()));
            
            // taskService.setVariables(a.getId(),variable);
            // taskService.complete(a.getId());
            
        });
        
    }
    
    /*查询部署信息*/
    @Test
    void deployRepositoryDetail(){
    
        String employee4 = "ASSIGNEE";
        // repositoryService.createProcessDefinitionQuery().list().forEach(a->
        //         {
        //             System.out.println("getDeploymentId:" + a.getDeploymentId());
        //             System.out.println("getKey:" + a.getKey());
        //             System.out.println("getName:" + a.getName());
        //         }
        // );
    
        System.out.println("ASSIGNEE : "+employee4.concat(String.valueOf(0)));
        // runtimeService.createProcessInstanceQuery().deploymentId("a4b58765-8c7e-11eb-b3e8-005056c00008").list().forEach(a->
        //         {
        //             System.out.println("getId:" + a.getId());
        //             System.out.println("getProcessInstanceId:" + a.getProcessInstanceId());
        //             System.out.println("getProcessDefinitionId:" + a.getProcessDefinitionId());
        //             System.out.println("getProcessDefinitionKey:" + a.getProcessDefinitionKey());
        //         }
        // );
    
    }
}
