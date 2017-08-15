package com.sup.product.busin;

import java.util.List;

import com.sup.product.bean.Product;
import com.sup.product.bean.ProductType;


public interface ProductBusin {

    /**
     * SpringBean的名称
     */
    public static String PRODUCTBUSIN = "productBusinImpl";

    /**
     * 获得单产品
     * 
     * @param id ID
     * @return Product
     */
    Product getProduct(String id);

    /**
     * 产品列表
     * 
     * @return List<Product>
     */
    List<Product> getProductList();

    /**
     * 根据部门查看目录
     * 
     * @param id
     * @return ProductType
     */
    ProductType getProductType(String id);

    /**
     * 查询产品列表
     * 
     * @return List<ProductType>
     */
    List<ProductType> getProductTypeList(String companyInfo);
    
    /**
     * 获得单产品
     * 
     * @param Code
     * @return Product
     */
    Product getProductByCode(String productCode,String companyinfo);

}
