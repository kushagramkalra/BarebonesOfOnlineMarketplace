package com.controller;

import com.Interface.ProductsInterface;
import com.model.Cart;
import com.model.Product;
import com.model.ResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    ProductsInterface productsInterface;

    /*Fetch either one or all the products.
        This microservice would fetch either one product if the title of that product is given as the request parameter of all of the products
        if no request parameter is given.*/
    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ResponseEntity<ResponsePayload> fetchProducts(@RequestParam(value = "title", required = false) String title){
        ResponsePayload responsePayload = null;
        ResponseEntity<ResponsePayload> responseEntity = null;
        List<Product> productList = productsInterface.fetchProducts(title);

        if(productList !=null && productList.size()>0){
            responsePayload = new ResponsePayload(ResponsePayload.RESPONSE_STATUS.SUCCESS, "Product(s) fetched successfully!");
            responsePayload.getResponseDetails().add(productList);
            responseEntity = new ResponseEntity<ResponsePayload>(responsePayload, HttpStatus.OK);
        }else{
            responsePayload = new ResponsePayload(ResponsePayload.RESPONSE_STATUS.FAILURE, "Failed to fetch the Product(s)!");
            responseEntity = new ResponseEntity<ResponsePayload>(responsePayload, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    /*Purchase a single product
    This microservice requires the title of the product as the request parameter which is being purchased.*/
    @RequestMapping(value="/purchase", method=RequestMethod.PUT)
    public ResponseEntity<ResponsePayload> purchaseProduct(@RequestParam(value="title") String title){
        ResponsePayload responsePayload = null;
        ResponseEntity<ResponsePayload> responseEntity = null;
        Product product = productsInterface.purchaseProduct(title);

        if(product !=null){
            responsePayload = new ResponsePayload(ResponsePayload.RESPONSE_STATUS.SUCCESS, "Successfully purchased the product '"+title+"'.");
            responsePayload.getResponseDetails().add(product);
            responseEntity = new ResponseEntity<ResponsePayload>(responsePayload, HttpStatus.OK);
        }else{
            responsePayload = new ResponsePayload(ResponsePayload.RESPONSE_STATUS.FAILURE, "Failed purchased the product '"+title+"'.");
            responseEntity = new ResponseEntity<ResponsePayload>(responsePayload, HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

     /*EXTRA CREDIT
     completing the cart
     This method would be accepting a list of products as the Request body,
     where title and inventory_count would denote the name and the respective amount of product bought.
     This microservice should be integrated with the button which would be hit at the time of completing the cart.*/
    @RequestMapping(value="/cart", method = RequestMethod.PUT)
    public ResponseEntity<ResponsePayload> productCart(@RequestBody Cart cart){
        ResponsePayload responsePayload = null;
        ResponseEntity<ResponsePayload> responseEntity = null;
        List<Product> product = productsInterface.purchaseCart(cart);

        if(product !=null && product.size()>0){
            responsePayload = new ResponsePayload(ResponsePayload.RESPONSE_STATUS.SUCCESS, "Successfully purchased the product(s).");
            responsePayload.getResponseDetails().add(product);
            responseEntity = new ResponseEntity<ResponsePayload>(responsePayload, HttpStatus.OK);
        }else{
            responsePayload = new ResponsePayload(ResponsePayload.RESPONSE_STATUS.FAILURE, "Failed purchased the product(s)");
            responseEntity = new ResponseEntity<ResponsePayload>(responsePayload, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
