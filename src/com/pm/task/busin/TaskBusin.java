package com.pm.task.busin;


import com.pm.framework.models.TreeModel;
import com.pm.task.bean.Task;

/**
 * 任务业务接口
 * @author Administrator
 *
 */
public interface TaskBusin {
    
    /**
     * 保存一个任务
     * @param task
     */
    void saveTask(Task task,String userId);
  
    /**
     * 根据订单id对数据进行处理，并进行重定向
     * @param id
     * @param hierarchy
     * @param taskTypeId
     * @return
     */
    String OrderDispose(String id,String hierarchy, String taskTypeId);
    
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
     * 删除一个任务
     * @param id
     */
    void removeTask(String id);
    
    /**
     * 根据任务id得到一条任务信息
     * @param id
     * @return
     */
    Task getTask(String id);
    /**
     * 根据订单ID得到一个树形菜单
     * @param orderId
     * @return
     */
    TreeModel  getOrderTreeModel(String orderId);
    
    String OrderDisposeByPm(String id,String hierarchy, String taskTypeId);
    
}
