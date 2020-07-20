package com.dao;

import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Product> fetchAllProducts(){
        List<Product> products = new ArrayList<>();
        try{
            products = mongoTemplate.findAll(Product.class);
        }catch (Exception e){
            System.out.print("Unexpected exception: "+ e.getMessage());
        }
        return products;
    }

    public List<Product> fetchOne(String title){
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        Query query = new Query(Criteria.where("title").is(title));
        try{
            product = mongoTemplate.findOne(query, Product.class);
        }catch (Exception e){
            System.out.println("Unexpected Exception: "+ e.getMessage());
        }
        productList.add(product);
        return productList;
    }

    public boolean save(Product product){
        boolean saveFlag = false;
        try{
            mongoTemplate.save(product);
            saveFlag = true;
        }catch (Exception e){
            System.out.println("Unexpected exception: "+ e.getMessage());
        }
        return saveFlag;
    }
}
