package in.ashokit.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.ProductCategoryDTO;
import in.ashokit.dto.ProductDTO;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.ProdServImpl;

@RestController
@CrossOrigin
public class ProductController {
	
private final ProdServImpl productServ;
	
	@Autowired
	public ProductController(ProdServImpl productServ) {
		this.productServ=productServ;
	}
	
	@GetMapping("/category")
	public ResponseEntity<ApiResponse<List<ProductCategoryDTO>>> productCategories(){
		List<ProductCategoryDTO> allCategories = productServ.getAllCategories();
		ApiResponse<List<ProductCategoryDTO>> response = new ApiResponse<>();
		if(!allCategories.isEmpty()) {
		
		response.setStatus(200);
		response.setMessage("Fetched All categories");// to display all categories on SideBar
		response.setData(allCategories);
		return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed in Fetching All categories");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*public ResponseEntity<List<ProductCategoryDTO>> getCategories(){
		List<ProductCategoryDTO> products = productServ.getAllCategories();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}*/
	
	@GetMapping("/products/{categoryId}")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> productsById(@PathVariable Integer categoryId){
		List<ProductDTO> products = productServ.getProductsByCatgId(categoryId);
		ApiResponse<List<ProductDTO>> response= new ApiResponse<>();
		if(!products.isEmpty()) {
			response.setStatus(200);
			response.setMessage("All Products");
			response.setData(products);
		return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.setStatus(200);
			response.setMessage("All Products");
			response.setData(Collections.emptyList());
		return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity <ApiResponse<ProductDTO>> productByProductId(@PathVariable Integer productId){
		ProductDTO product = productServ.getProductsByProdId(productId);
		ApiResponse<ProductDTO> response= new ApiResponse<>();
		if(product!=null) {
		response.setStatus(200);
		response.setMessage("Products by Id");
		response.setData(product);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}else {
			response.setStatus(500);
			response.setMessage("Failed To Fetch Products by Id");
			response.setData(product);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/product-name/{productName}")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> productsByProdName(@PathVariable String productName){
		List<ProductDTO> products = productServ.getProductsByProdName(productName);
		ApiResponse<List<ProductDTO>> response= new ApiResponse<>();
		if(!products.isEmpty()) {
		response.setStatus(200);
		response.setMessage("Products By Name");
		response.setData(products);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}else {
		response.setStatus(500);
		response.setMessage("Failed To Fetch Products By Name");
		response.setData(null);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

}
