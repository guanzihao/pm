package com.pm.organize.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.pm.framework.models.TreeModel;
import com.pm.framework.models.TreeStateModel;
import com.pm.organize.bean.OrgDept;

/**
 * 树状结构处理类
 * 
 * @author youliang.fang
 */

public class DeptTreeModel {

    /**
     * 获得组织结构树
     */
    public static List<TreeModel> ToTree(List<OrgDept> orgDepts, List<String> selectedIds, boolean opened) {
        List<TreeModel> treeModels = new ArrayList<TreeModel>();
        if (orgDepts != null && orgDepts.size() > 0) {
            for (OrgDept orgDept : orgDepts) {
                String id = orgDept.getId();
                String name = "[" + orgDept.getDeptCode() + "]" + orgDept.getDeptName();

                boolean selected = false;
                if (selectedIds != null && !selectedIds.isEmpty()) {
                    selected = selectedIds.contains(orgDept.getId());
                }

                List<TreeModel> children = null;
                if (orgDept.getChildren() != null && !orgDept.getChildren().isEmpty()) {
                    children = ToTree(orgDept.getChildren(), selectedIds, opened);
                }
                treeModels.add(new TreeModel(id, name, new TreeStateModel(selected, opened), children));
            }
        }
        return treeModels;
    }

    /**
     * 组织树JOSN
     */
    public static String ToJosn(List<OrgDept> orgDepts, List<String> selectedIds, boolean opened) {
        List<TreeModel> children = ToTree(orgDepts, selectedIds, opened);
        List<TreeModel> treeModels = new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0", "根节点",
                new TreeStateModel(false, opened), children)));
        return JSONArray.fromObject(treeModels, new JsonConfig()).toString();
    }
}
