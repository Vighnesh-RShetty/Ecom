package com.ecom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecom.Repository.addProductRepo;
import com.ecom.model.addProducts;
import com.ecom.service.addProductService;

@Service
public class addProductsServImpl implements addProductService {

@Autowired
private addProductRepo prodRepo;
	
	@Override
	public addProducts saveProducts(addProducts addProd) {
		
		if(!ObjectUtils.isEmpty(addProd)) {
			return prodRepo.save(addProd);
		}
		
		return null;
	}

	@Override
	public List<addProducts> getAllProducts() {
		
		return prodRepo.findAll();
	}

	@Override
	public addProducts getProductsById(int id) {
	addProducts prod = prodRepo.findById(id).orElse(null);
		return prod;
	}

	@Override
	public Boolean deleteProdById(int id) {
		  
		addProducts prod = prodRepo.findById(id).orElse(null);
		if(!ObjectUtils.isEmpty(prod)) {
			prodRepo.delete(prod);
		}
		return false;
	}

	@Override
	public addProducts getProductById(int id) {
		addProducts products =  prodRepo.findById(id).orElse(null);
		if(!ObjectUtils.isEmpty(products)) {
			return products;
		}
		return null;
	}
	}

