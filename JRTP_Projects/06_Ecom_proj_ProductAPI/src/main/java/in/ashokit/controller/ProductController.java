package main.java.in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import main.java.in.ashokit.dto.ProductDTO;
import main.java.in.ashokit.service.ProdServImpl;

@RestController
public class ProductController {
	
	private final ProdServImpl productServ;
	
	@Autowired
	public ProductController(ProdServImpl productServ) {
		this.productServ=productServ;
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDTO>> getProductsById(@PathVariable Integer categoryId){
		List<ProductDTO> products = productServ.getProductsByCatgId(categoryId);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String categoryName){
		List<ProductDTO> products = productServ.getProductsByCatgName(categoryName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity <ProductDTO> getProductsByProductId(@PathVariable Integer productId){
		ProductDTO product = productServ.getProductsByProdId(productId);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@GetMapping("/product/{productName}")
	public ResponseEntity<List<ProductDTO>> getProductsByProdName(@PathVariable String productName){
		List<ProductDTO> products = productServ.getProductsByCatgName(productName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

}
