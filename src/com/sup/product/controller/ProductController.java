package com.sup.product.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.core.model.Contexts;
import com.pm.core.model.PageBean;
import com.pm.core.model.SearchBean;
import com.pm.core.util.StringUtil;
import com.pm.framework.models.CheckSubmit;
import com.pm.right.models.FunAuth;
import com.sup.company.controller.SupBaseController;
import com.sup.product.bean.Product;
import com.sup.product.bean.ProductType;
import com.sup.product.busin.ProductBusin;

@Controller
@RequestMapping("/sup/product/product")
public class ProductController extends SupBaseController {

    @Autowired
    private ProductBusin productBusin;

    /**
     * AJAX检查产品编号是否冲突
     */
    @RequestMapping(value = "/ajaxCheckProduct")
    @ResponseBody
    public void ajaxCheckProduct(String productId, String productCode) {
        int count = businApi.getQueryPageSize("select a.id from Product a where id <> ? and productCode = ? and companyInfoUser = ?", new Object[] { productId, productCode,this.getCurrSup().getId() });
        writer(count > 0 ? "false" : "true");
    }
    
    /**
     * 编辑产品
     * 
     * @return editProduct
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editProduct/{id}/{productTypeId}")
    public String editProduct(@PathVariable String id, @PathVariable String productTypeId) {
        Product product = productBusin.getProduct(id);
        if (productTypeId != null && !productTypeId.equals("")) {
            ProductType productType = productBusin.getProductType(productTypeId);
            if (product == null) {
                product = new Product();
            }
            product.setProductType(productType);
        }
        model.addAttribute("product", product);
        return "/product/product/editProduct";
    }

    /**
     * 显示产品信息
     */
    @RequestMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable String id) {
        Product product = productBusin.getProduct(id);
        model.addAttribute("product", product);
        return "/product/product/viewProduct";
    }

    /**
     * 查看所有产品
     * 
     * @return listProduct
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/listProduct")
    public String listProduct(SearchBean searchBean, String productTypeId, String productTypeName) {
        PageBean pageBean = new PageBean(searchBean, businApi);
        pageBean.addQuerySQL("select p.id, p.productName, p.productCode, p.productSpec,  p.productUnit, p.productPrice,p.productType,p.productstate from Product p where p.companyInfoUser=? ");
        pageBean.addParams(this.getCurrSup().getId());
        pageBean.addQueryStr("p.productName", searchBean.getSearchName1(), true);
        pageBean.addQueryStr("p.productCode", searchBean.getSearchName2(), true);
        if (!StringUtil.isEmpty(searchBean.getSearchName1()) || !StringUtil.isEmpty(searchBean.getSearchName2())) {
            pageBean.addQuerySQL(" and 1=1");
        } else {
            pageBean.addQuerySQL(" and 1=1");
        }
       
        StringBuffer sf = new StringBuffer();
        if (!StringUtil.isEmpty(productTypeId)) {
            sf.append(" and p.productType.id ='"+productTypeId+" ' " );
        }
        
        pageBean.addQuerySQL(sf.toString());
        pageBean.addOrderBy("p.productIndex", true);
        pageBean.query();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("productTypeId", productTypeId);
        model.addAttribute("productTypeName", productTypeName);
        model.addAttribute("productTypeList", productBusin.getProductTypeList(this.getCurrSup().getId()));
        return "/product/product/listProduct";
    }

    /**
     * 保存产品
     * 
     * @param parentId
     * @param Product
     * @return listProduct
     */
    @CheckSubmit(removeToken = true)
    @RequestMapping("/saveProduct")
    public String saveProduct(Product product, String productTypeId) {
        Product product_1 = productBusin.getProduct(product.getId());
        ProductType productType = productBusin.getProductType(productTypeId);
        if (product_1 != null) {
            product_1.setProductName(product.getProductName());
            product_1.setProductCode(product.getProductCode());
            product_1.setProductSpec(product.getProductSpec());
            product_1.setProductUnit(product.getProductUnit());
            product_1.setProductPrice(product.getProductPrice());
            product_1.setProductType(productType);
            product_1.setCompanyInfoUser(this.getCurrSup().getId());
            businApi.save(product_1);
        } else {
            product.setCompanyInfoUser(this.getCurrSup().getId());
            product.setProductstate(Contexts.N);
            product.setProductIndex(productBusin.getProductList().size() + 1);
            product.setProductType(productType);
            businApi.save(product);
        }

        return "redirect:/sup/product/product/viewProduct/" + product.getId();
    }

    /**
     * 删除产品
     * 
     * @param model
     * @return addProduct
     */
    @RequestMapping("/removeProduct")
    @ResponseBody
    public void removeProduct(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Product product = productBusin.getProduct(id);
                    if (product != null) {
                        businApi.remove(product);
                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }

    /**
     * 根据产品目录添加产品
     * 
     * @return editProduct
     */
    @CheckSubmit(saveToken = true)
    @RequestMapping("/editProduct/{id}/${productTypeId}")
    public String editProductByType(@PathVariable String id, @PathVariable String productTypeId) {
        Product product = productBusin.getProduct(id);
        model.addAttribute("product", product);
        return "/product/product/editProduct";
    }
    
    
    /**
     * 产品上架
     */
    @FunAuth("info_enableproduct")
    @RequestMapping("/enableproduct")
    @ResponseBody
    public void enableproduct(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Product product = productBusin.getProduct(id);
                    if (product != null) {
                        product.setProductstate(Contexts.Y);
                        businApi.save(product);
                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
    
    /**
     * 产品下架
     */
    @FunAuth("info_enableproducts")
    @RequestMapping("/enableproducts")
    @ResponseBody
    public void enableproductInfo(String[] ids) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (ids != null && ids.length > 0) {
                for (String id : ids) {
                    Product product = productBusin.getProduct(id);
                    if (product != null) {
                        product.setProductstate(Contexts.N);
                        businApi.save(product);
                        JSONObject jo = new JSONObject();
                        jo.put("id", id);
                        jsonArray.put(jo);
                    }
                }
            }
        } catch (Exception e) {
            addLogger(e);
        }
        writer(jsonArray.toString());
    }
}
