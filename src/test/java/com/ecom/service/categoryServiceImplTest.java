package com.ecom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecom.Repository.CategoryRepo;
import com.ecom.model.Category;
import com.ecom.serviceImpl.categoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class categoryServiceImplTest {
	
	@Mock
	private CategoryRepo catrepo;
	
	@InjectMocks
	private categoryServiceImpl catService;
	
	//This For True
	@Test
	void testExistCategory_whenExist_returnTrue() {
		
		String catName="Books";
		
		when(catrepo.existsByName(catName)).thenReturn(true);
		
		boolean exist=catService.existCategory(catName);
		assertTrue(exist);
	}
	
	//This for False
	
	@Test
	void testExistCat_whenNotExist_returnFalse() {
		
		String categoryName="Electronics";
		when(catrepo.existsByName(categoryName)).thenReturn(false);
		boolean exist=catService.existCategory(categoryName);
		assertFalse(exist);
	}
	
	//For Save Category
	@Test
	void testSaveCategory_success() {
		
		Category cate1=new Category();
		cate1.setName("Fashion");
		
		when(catrepo.save(cate1)).thenReturn(cate1);
		
		Category savedCat=catService.saveCategory(cate1);
		
		assertNotNull(savedCat);
		assertEquals("Fashion", savedCat.getName());
		
	}
	
	//Get All Category
	@Test
	void testAllCategory_success() {
		
		List<Category> category=new ArrayList<>();
		category.add(new Category());
		category.add(new Category());
		
		when(catrepo.findAll()).thenReturn(category);
		
		List<Category> result=catService.getAllCategory();
		
		assertEquals(2, result.size());
	}

}
