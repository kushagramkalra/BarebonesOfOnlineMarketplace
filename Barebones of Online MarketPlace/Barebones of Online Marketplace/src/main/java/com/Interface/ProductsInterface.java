package com.Interface;

import com.model.Cart;
import com.model.Product;

import java.util.List;

public interface ProductsInterface {

    List<Product> fetchProducts(String title);
    Product purchaseProduct(String title);
    List<Product> purchaseCart(Cart cart);
}
