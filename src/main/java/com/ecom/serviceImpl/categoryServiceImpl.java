package com.ecom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.Repository.CategoryRepo;
import com.ecom.model.Category;
import com.ecom.service.categoryService;

@Service
public class categoryServiceImpl implements categoryService {
   
	@Autowired
	private CategoryRepo catRepo;
	
	@Override
	public Category saveCategory(Category cate) {
		
		return catRepo.save(cate);
	}

	@Override
	public List<Category> getAllCategory() {
		
		return catRepo.findAll();
	}

	@Override
	public Boolean existCategory(String name) {
		
		return catRepo.existsByName(name);
	}

}
