package com.service;

import com.Interface.ProductsInterface;
import com.dao.ProductDAO;
import com.model.Cart;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class fetchProducts implements ProductsInterface {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> fetchProducts(String title){
        List<Product> products = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        //checking if any product title was actually passed as the request parameter.
        if(title == null || title.isEmpty())
            // if no title passed - fetch all products
            products = productDAO.fetchAllProducts();
        else
            //title passed - fetch one product
            products = productDAO.fetchOne(title);

        //checking if the inventory count is greater than one
        for(Product p: products){
            if(p.getInventory_count()>0){
                //if true - adding the product to the final list which would be returned
                productList.add(p);
            }
        }
        return productList;
    }

    @Override
    public Product purchaseProduct(String title){
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        if(title!=null && !title.isEmpty()){
            products = productDAO.fetchOne(title);
            product = products.get(0);
            int count = product.getInventory_count();
            if(count>0){
                //reducing the product count by one
                count--;
                product.setInventory_count(count);
                //updating the updated value of inventory count of the respective product into the DB.
                boolean saveFlag = productDAO.save(product);
                if(saveFlag)
                    //if successfully updated
                    return product;
            }else{
                return product;
            }
        }
        return product;
    }

    @Override
    public List<Product> purchaseCart(Cart cart){
        List<Product> productsList = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        //checking if the request body was not null
        if(cart!=null){
            //getting the list of products which were passed via the request body
            List<Product> cartList = cart.getProductList();
            //iterating through each product and updating the respective inventory count
            for(Product p:cartList){
                String s = p.getTitle();
                products = productDAO.fetchOne(s);
                product = products.get(0);
                int count = product.getInventory_count();
                if(count>0) {
                    count-=p.getInventory_count();
                    product.setInventory_count(count);
                    boolean saveFlag = productDAO.save(product);
                    if(saveFlag)
                        productsList.add(product);
                }
            }
        }
        return productsList;
    }
}
