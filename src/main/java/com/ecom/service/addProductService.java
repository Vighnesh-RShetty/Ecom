package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.model.addProducts;

@Service
public interface addProductService {

public addProducts saveProducts(addProducts addProd);
public List<addProducts> getAllProducts();

public addProducts getProductsById(int id);

public Boolean deleteProdById(int id);

public addProducts getProductById(int id);

}
