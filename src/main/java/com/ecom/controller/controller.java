package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
	
	@GetMapping("/")
	public String indexController() {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String loginController() {
		
		return "login";
		
	}
	@GetMapping("/register")
	public String registerController() {
		
		return "register";
	}
	
	@GetMapping("/products")
	public String productPage() {
		return "product";
	}
	
	@GetMapping("/viewProduct")
	public String viewProduct() {
		return "view_product";
	}
}
