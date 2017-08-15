package com.sup.task.busin;

import java.util.List;

import com.pm.framework.models.TreeModel;
import com.pm.task.bean.Task;
import com.sup.order.bean.OrderFrom;

public interface SupTaskBusin {
    
    /**
     * 保存一个任务
     * @param task
     */
    void saveTask(Task task,String userId);
    
    /**
     * 得到一个树形菜单
     * @param taskId
     * @return
     */
    TreeModel getTreeModel(String taskId);
    
    /**
     * 获取任务下所有的订单
     * @param taskId
     * @return
     */
    List<OrderFrom> getorderForm(String taskId);
    
    /**
     * 根据节点名称和Id对数据进行处理,并进行转发
     * @param id
     * @param nodeName
     * @return
     */
    String TaskDispose(String id,String hierarchy, String taskTypeId);
    
    /**
     * 取出任务的最大Id
     * @return
     */
    String getTaskId();
    
    /**
     * 提交一个任务
     * @param id
     */
    void submitTask(String id);
    /**
     * 删除任务信息
     * @param id
     */
    void removeTask(String id);
    
    /**
     * 根据任务id得到任务名称
     * @param id
     * @return
     */
    String getTaskIdByTaskName(String id);
    
}
