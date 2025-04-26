package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminController {

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
}
