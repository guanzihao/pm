package com.sup.product.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pm.framework.models.TreeModel;
import com.pm.framework.models.TreeStateModel;
import com.sup.product.bean.ProductType;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 树状结构处理类
 * 
 * @author shaobo.song
 */

public class ProductTypeTreeModel {

    /**
     * 获得组织结构树
     */
    public static List<TreeModel> ToTree(List<ProductType> productTypes, List<String> selectedIds, boolean opened) {
        List<TreeModel> treeModels = new ArrayList<TreeModel>();
        if (productTypes != null && productTypes.size() > 0) {
            for (ProductType productType : productTypes) {
                String id = productType.getId();
                String name = "[" + productType.getCode() + "]" + productType.getTypeName();

                boolean selected = false;
                if (selectedIds != null && !selectedIds.isEmpty()) {
                    selected = selectedIds.contains(productType.getId());
                }

                List<TreeModel> children = null;
                if (productType.getChildren() != null && !productType.getChildren().isEmpty()) {
                    children = ToTree(productType.getChildren(), selectedIds, opened);
                }
                treeModels.add(new TreeModel(id, name, new TreeStateModel(selected, opened), children));
            }
        }
        return treeModels;
    }

    /**
     * 组织树JOSN
     */
    public static String ToJosn(List<ProductType> productTypes, List<String> selectedIds, boolean opened) {
        List<TreeModel> children = ToTree(productTypes, selectedIds, opened);
        List<TreeModel> treeModels = new ArrayList<TreeModel>(Arrays.asList(new TreeModel("0", "全部商品",
                new TreeStateModel(false, opened), children)));
        return JSONArray.fromObject(treeModels, new JsonConfig()).toString();
    }
}
