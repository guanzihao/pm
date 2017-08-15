package com.pm.framework.models;


/**
 * 树状结构状态处理类
 * 
 * @author youliang.fang
 */

public class TreeStateModel {
    private boolean selected;
    private boolean opened;

    public TreeStateModel(boolean selected, boolean opened) {
        this.opened = opened;
        this.selected = selected;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
