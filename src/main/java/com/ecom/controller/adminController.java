package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Category;
import com.ecom.service.categoryService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	private categoryService catserv;

	 @GetMapping("/")
	public String homePage() {
		 
		return"/admin/index";
	}
	 
	 @GetMapping("/loadAddProducts")
	 public String addProduct() {
		 
		 return "admin/add_product";
	 }
	 
	 @GetMapping("/category")
	 public String category() {
		 
		 return "admin/category";
	 }
	 
	  @PostMapping("/saveCat")
	 public String saveCategory(@ModelAttribute Category cat,@RequestParam("imagePath") String file,HttpSession session) {
	  String imageName= file!=null?file:"default.jpg";
		  cat.setImagePath(imageName);
		  
		  Boolean existCat= catserv.existCategory(cat.getName());
		  
		 if(existCat) {
			 session.setAttribute("errorMsg", "Category Already Exist");
		 }else {
			  Category categ= catserv.saveCategory(cat);
			    if(ObjectUtils.isEmpty(categ)){
			    	session.setAttribute("errorMsg", "Not Saved! Internal Server Error");
			    }else {
			    	session.setAttribute("succMsg", "Saved SuccessFully");
			    }
		 }
		 
		 return "redirect:/admin/category";
	 }
	 
}
