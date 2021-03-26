package com.example.test.flowabletest.Listener;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.variable.api.persistence.entity.VariableInstance;

import java.util.Map;

/**
 * @author 胡昊宁
 * @date 2021/3/19
 */
public class ExecutionListenerDemo implements ExecutionListener {
    @Override
    public void notify(final DelegateExecution execution) {
        Map<String, Object> variables = execution.getVariables();
        Map<String, Object> variableInstancesLocal = execution.getVariablesLocal();
       
        Map<String, VariableInstance> variableInstances = execution.getVariableInstances();
        System.out.println("变量："+variables);
        System.out.println("临时变量："+variableInstancesLocal);
        System.out.println("实例变量"+variableInstances);
        System.out.println("first aaaaa");
    }
}
