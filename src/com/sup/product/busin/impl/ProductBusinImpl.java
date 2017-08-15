package com.sup.product.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.product.bean.Product;
import com.sup.product.bean.ProductType;
import com.sup.product.busin.ProductBusin;

@Service
@SuppressWarnings("unchecked")
public class ProductBusinImpl implements ProductBusin {

    @Autowired
    private BusinApi businApi;

    public Product getProduct(String id) {
        return (Product) businApi.get(Product.class, id);
    }

    public List<Product> getProductList() {
        return businApi.getList(Product.class);
    }

    public ProductType getProductType(String id) {
        return (ProductType) businApi.get(ProductType.class, id);
    }

    public List<ProductType> getProductTypeList(String companyinfo) {
        return businApi.getQueryList("from ProductType where parent is NULL and companyInfoUser = ? ",  new Object[] {companyinfo});
    }

    @Override
    public Product getProductByCode(String productCode,String companyinfo) {
        return (Product) businApi.getQueryObject("from Product where productCode = ? and companyInfoUser = ?", new Object[] { productCode ,companyinfo});
    }

}
