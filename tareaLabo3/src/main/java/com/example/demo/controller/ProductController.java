package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Product;



@Controller
public class ProductController {
	
	private List<Product> products = new ArrayList<Product>();
	
	
	@GetMapping(path ="/ejemplo1", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String ejemplo1() {	
		return "Bienvenidos\n" + "Programacion N-Capas";
	}
	
	
	
	@GetMapping("/ejemplo2")
	public @ResponseBody List<Product> ejemplo2(){
		return Arrays.asList(
				new Product(1, "pollo", "4"),
				new Product(2,"carne","6")
				);
	}
	
	@GetMapping ("/inicio")
	public String inicio(Product product) {
		return "productos";
	}
	
	
	
	@PostMapping("/formData")
	public ModelAndView procesar(Product product) {
		products.add(product);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productos");
		mav.addObject("product", new Product());
		return mav;
	}
	
	@GetMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resultado");
		mav.addObject("productList", this.products);
		return mav;
	}
	
	
	@GetMapping("/validar")
	public ModelAndView validar(Product product, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(product.getCantidad().equals(this.products)) {
			mav.setViewName("comprar");
			return mav;
		}
		else {
			mav.setViewName("error");
			return mav;
		}
	}
		
}