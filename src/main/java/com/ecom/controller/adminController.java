package com.ecom.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Category;
import com.ecom.model.addProducts;
import com.ecom.service.addProductService;
import com.ecom.service.categoryService;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	private categoryService catserv;
	@Autowired
	private addProductService prodService;

	 @GetMapping("/")
	public String homePage() {
		 
		return"/admin/index";
	}
	 
	 @GetMapping("/loadAddProducts")
	 public String addProduct(Model m) {
		 m.addAttribute("categories",catserv.getAllCategory());
		 
		 return "admin/add_product";
	 }
	 
	 @GetMapping("/category")
	 public String category(Model m) {
		 m.addAttribute("categories",catserv.getAllCategory());
		 
		 return "admin/category";
	 }
	 @PostMapping("/saveCat")
	 public String saveCategory(@ModelAttribute Category cat, @RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
	  String imageName= file!=null?file.getOriginalFilename():"default.jpg";
		  cat.setImagePaths(imageName);
		  
		  Boolean existCat= catserv.existCategory(cat.getName());
		  
		 if(existCat) {
			 session.setAttribute("errorMsg", "Category Already Exist");
		 }else {
			  Category categ= catserv.saveCategory(cat);
			    if(ObjectUtils.isEmpty(categ)){
			    	session.setAttribute("errorMsg", "Not Saved! Internal Server Error");
			    }else {
			    	File savefile =new ClassPathResource("static/img").getFile();
			    	  Path path= Paths.get(savefile.getAbsolutePath()+File.separator+"/category_img");
			    	 if(!Files.exists(path)) {
			    		 Files.createDirectories(path);
			    	 }
			    	  Path filePath= path.resolve(file.getOriginalFilename());
			    	  Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
			    	 System.out.println(path);
			    	session.setAttribute("succMsg", "Saved SuccessFully");
			    }
		 }
		 
		 return "redirect:/admin/category";
	 }
	  
	  @GetMapping("/deleteCate/{id}")
	  public String deleteCategory(@PathVariable int id,HttpSession session) {
		  
		  Boolean result = catserv.deleteCategory(id);
		  if(result) {
			  session.setAttribute("succMsg", "Category Deleted SuccessFully");
		  }else {
			  
			  session.setAttribute("errorMsg", "Unable Delete Category!!Please Check Once");
		  }
		  
		  return "redirect:/admin/category";
	  }
	  
	  @GetMapping("/editCate/{id}")
	  public String editCat(@PathVariable int id,Model m) {
		Category cat=  catserv.getCategoryById(id);
		
		  m.addAttribute("category",cat);
		  
		  return "admin/editCategory";
	  }
	  
	  @PostMapping("/updateCategory")
	  public String updateCategory(@ModelAttribute Category cat,@RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
		  Category categ =  catserv.getCategoryById(cat.getId());
		            String path=  file.isEmpty()?categ.getImagePaths():file.getOriginalFilename();
		            System.out.println(path);
		  if(!ObjectUtils.isEmpty(categ)) {
			  
			 categ.setName(cat.getName());
			 categ.setIsActive(cat.getIsActive());
			 categ.setImagePaths(path);
			  
		  }
		 Category updateCat = catserv.saveCategory(categ);
		 if(!ObjectUtils.isEmpty(updateCat)) {
			 if(!file.isEmpty()) {
				 File savefile =new ClassPathResource("static/img").getFile();
		    	  Path paths= Paths.get(savefile.getAbsolutePath()+File.separator+"/category_img");
		    	 if(!Files.exists(paths)) {
		    		 Files.createDirectories(paths);
		    	 }
		    	  Path filePath= paths.resolve(file.getOriginalFilename());
		    	  Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
		    	 System.out.println(path);
		    	 session.setAttribute("succMsg","Updated SuccessFully");
			 }	 
			 
		 }else {
			 session.setAttribute("errorMsg","Not Updated!!Internal Server Error");
		 }
		  
		  
		  return "redirect:/admin/editCate/"+cat.getId();
		  
	  }
	  
	  @PostMapping("/addProd")
	  public String addProducts(@ModelAttribute addProducts prod,@RequestParam("img") MultipartFile  file,HttpSession session) throws IOException {
		  
		  String imageName= file!=null?file.getOriginalFilename():"default.jpg";
		  prod.setProdImage(imageName);
		  
		  //Boolean existCat= catserv.existCategory(cat.getName());
		  
//		 if(existCat) {
//			 session.setAttribute("errorMsg", "Category Already Exist");
//		 }else {
			  addProducts prd= prodService.saveProducts(prod);
			    if(ObjectUtils.isEmpty(prd)){
			    	session.setAttribute("errorMsg", "Not Saved! Internal Server Error");
			    }else {
			    	File savefile =new ClassPathResource("static/img").getFile();
			    	  Path path= Paths.get(savefile.getAbsolutePath()+File.separator+"/product_img");
			    	 if(!Files.exists(path)) {
			    		 Files.createDirectories(path);
			    	 }
			    	  Path filePath= path.resolve(file.getOriginalFilename());
			    	  Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
			    	 System.out.println(path);
			    	session.setAttribute("succMsg", "Saved SuccessFully");
			    }
		  return "redirect:/admin/";
	  }
	  
	  @GetMapping("/deleteProduct/{id}")
	  public String deleteProd(@PathVariable int id,HttpSession session) {
		  
		Boolean deleteStatus=  prodService.deleteProdById(id);
		  
		if(deleteStatus) {
			
			session.setAttribute("succMsg", "Product Deleted SuccessFully");
		}else {
			session.setAttribute("errorMsg", "Internal Server Error !!! Not able to delete");
		}
		  return "admin/viewProduct";
	  }
	  @GetMapping("/viewProd")
	  public String loadviewProdcts(Model m) {
		  m.addAttribute("products",prodService.getAllProducts());
		  
		  return "admin/viewProduct";
	  }
	  @GetMapping("/editProd/{id}")
	  public String editProduct(@PathVariable int id,Model m) {
		 m.addAttribute("product",prodService.getProductById(id));
		 m.addAttribute("categories",catserv.getAllCategory());
		  return "admin/editProduct";
	  }
}	  
