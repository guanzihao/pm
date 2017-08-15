package com.sup.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.CheckSubmit;
import com.sup.company.controller.SupBaseController;
import com.sup.product.bean.ProductType;
import com.sup.product.busin.ProductBusin;
import com.sup.product.models.ProductTypeTreeModel;

@Controller
@RequestMapping("/sup/product/productType")
public class ProductTypeController extends SupBaseController {

    @Autowired
    private ProductBusin productBusin;

    /**
     * AJAX检查部门是否冲突
     */
    @RequestMapping(value = "/ajaxCheckProductType")
    @ResponseBody
    public void ajaxCheckProductType(String productTypeId, String code) {
        int count = businApi.getQueryPageSize("select a.id from ProductType a where id <> ? and code = ? and companyInfoUser = ?", new Object[] { productTypeId, code,this.getCurrSup().getId() });
        writer(count > 0 ? "false" : "true");
    }

    /**
     * AJAX检查目录下是否有产品
     */
    @RequestMapping(value = "/ajaxCheckProductToType")
    @ResponseBody
    public void ajaxCheckProductToType(String productTypeId) {
        int count = businApi.getQueryPageSize("select a.id from Product a where a.productType.id=? and companyInfoUser = ? ", new Object[] { productTypeId,this.getCurrSup().getId() });
        writer(count > 0 ? "false" : "true");
    }

    @RequestMapping("/listProductByType")
    public String listProductByType(String productTypeId, SearchBean searchBean) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select p.id, p.productName, p.productCode, p.productSpec, p.productUnit, p.productPrice,p.productType from Product p where p.companyInfoUser = ?");
        pageBean.addParams(this.getCurrSup().getId());
        pageBean.addQueryStr("p.productName", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("p.productCode", searchBean.getSearchName2(), true);
        pageBean.addQueryStr("p.productType.id", productTypeId, true);
        pageBean.addOrderBy("p.productIndex", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("productTypeId", productTypeId);
        return "/product/productType/listProductByType";
    }

    /**
     * 添加产品目录
     * 
     * @return editProductType
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/addProductType")
    public String addProductType(String productTypeId) {
        model.addAttribute("parent", productBusin.getProductType(productTypeId));
        return "/product/productType/editProductType";
    }

    /**
     * 编辑产品目录
     * 
     * @return editProductType
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editProductType")
    public String editProductType(String productTypeId) {
        ProductType productType = productBusin.getProductType(productTypeId);
        if (productType != null && productType.getParent() != null) {
            ProductType parent = productBusin.getProductType(productType.getParent().getId());
            model.addAttribute("parent", parent);
        }
        model.addAttribute("productType", productType);
        return "/product/productType/editProductType";
    }

    /**
     * 保存产品目录
     * 
     * @param parentId
     * @param productType
     * @return listProductType
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveProductType")
    public String saveProductType(String parentId, ProductType productType) {
        ProductType parent = productBusin.getProductType(parentId);
        if (!StringUtil.isEmpty(productType.getId())) {
            ProductType productType1 = productBusin.getProductType(productType.getId());
            productType1.setCode(productType.getCode());
            productType1.setTypeName(productType.getTypeName());
            productType1.setParent(parent);
            productType1.setCompanyInfoUser(this.getCurrSup().getId());
            businApi.save(productType1);
        } else {
            productType.setCompanyInfoUser(this.getCurrSup().getId());
            productType.setParent(parent);
            businApi.save(productType);
        }
        return "redirect:/sup/product/productType/listProductType";
    }

    /**
     * 删除产品目录
     * 
     * @param productTypeId
     * @return listProductType
     */
    @RequestMapping("/removeProductType")
    public String removeProductType(String productTypeId) {
        ProductType productType = productBusin.getProductType(productTypeId);
        if (productType != null) {
            if (productType.getChildren() == null || productType.getChildren().size() == 0) {
                businApi.remove(productType);
            }
        }
        return "redirect:/sup/product/productType/listProductType";
    }

    /**
     * 查询产品目录List树状列表
     * 
     * @return
     */
    @RequestMapping("/listProductType")
    public String listProductType(String productTypeId) {
        model.addAttribute("treeData", ProductTypeTreeModel.ToJosn(productBusin.getProductTypeList(this.getCurrSup().getId()), null, true));
        return "/product/productType/listProductType";
    }

    @RequestMapping("/selectProductType")
    public String selectProductType() {
        model.addAttribute("treeData", ProductTypeTreeModel.ToJosn(productBusin.getProductTypeList(this.getCurrSup().getId()), null, true));
        return "/product/productType/selectProductType";
    }

}
