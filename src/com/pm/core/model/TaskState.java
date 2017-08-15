package com.pm.core.model;

public class TaskState {
    /**
     * 未提交
     */
    public static final Integer WTJ = 1;

    /**
     * 已审核
     */
    public static final Integer YSH = 2;

    /**
     * 取消(客户,单据状态)
     */
    public static final Integer QX = 3;

    /**
     * 终止(客户,单据状态)
     */
    public static final Integer ZZ = 4;

    /**
     * 草稿(客户,单据状态)
     */
    public static final Integer CG = 5;
    
    /**
     * 处理中(客户,节点状态)
     */
    public static final Integer CLZ =6;
    
    
    
    /**
     * 已完成(客户,节点状态)
     */
    public static final Integer YWC = 7;

    /**
     * 未执行(客户,节点状态)
     */
    public static final Integer WZX = 8;
    /**
     * 已提交(客户)
     */
    public static final Integer YTJ=9;
    /**
     * 服务商已接收(服务商)
     */
    public static final Integer FWSYJS=10;
    /**
     * 服务商已拒绝(服务商)
     */
    public static final Integer FWSYJJ=11;
    
    
    /**
     *未分配
     */
    public static final Integer WFP=12;
    
    
    /**
     *已分配(客服)
     */
    public static final Integer YFP=13;
    /**
     * 已终止(客服)
     */
    public static final Integer YZZ=14;
    
}
