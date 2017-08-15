package com.pm.right.models;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.pm.framework.models.TreeModel;
import com.pm.framework.models.TreeStateModel;
import com.pm.right.bean.Authority;

/**
 * 树状结构处理类
 * 
 * @author youliang.fang
 */

public class AuthTreeModel {

    /**
     * 获得权限树
     */
    public static List<TreeModel> ToTree(List<Authority> authorities, List<String> selectedIds, boolean opened) {
        List<TreeModel> treeModels = new ArrayList<TreeModel>();
        if (authorities != null && authorities.size() > 0) {
            for (Authority authority : authorities) {
                String id = authority.getId();
                String name = authority.getAuthName();

                boolean selected = false;
                if (selectedIds != null && !selectedIds.isEmpty()) {
                    selected = selectedIds.contains(authority.getId());
                }

                List<TreeModel> children = null;
                if (authority.getChildren() != null && !authority.getChildren().isEmpty()) {
                    children = ToTree(authority.getChildren(), selectedIds, opened);
                }
                treeModels.add(new TreeModel(id, name, new TreeStateModel(selected, opened), children));
            }
        }
        return treeModels;
    }

    /**
     * 权限树JOSN
     */
    public static String ToJosn(List<Authority> authorities, List<String> selectedIds, boolean opened) {
        List<TreeModel> children = ToTree(authorities, selectedIds, opened);
        return JSONArray.fromObject(children, new JsonConfig()).toString();
    }
}
