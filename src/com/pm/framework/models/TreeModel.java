package com.pm.framework.models;

import java.util.List;

/**
 * 树状结构处理类
 * 
 * @author youliang.fang
 */

public class TreeModel {
    private String id;
    private String text;
    private TreeStateModel state;
    private List<TreeModel> children;
    private Integer hierarchy;
    private String taskTypeId;

    public TreeModel() {
    }
    
    public TreeModel(String id, String text, TreeStateModel state, List<TreeModel> children, Integer hierarchy, String taskTypeId) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.children = children;
        this.hierarchy = hierarchy;
        this.taskTypeId = taskTypeId;
    }
    
    public TreeModel(String id, String text, TreeStateModel state, List<TreeModel> children) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.children = children;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }
   
    public String getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(String taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TreeStateModel getState() {
        return state;
    }

    public void setState(TreeStateModel state) {
        this.state = state;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }
}
