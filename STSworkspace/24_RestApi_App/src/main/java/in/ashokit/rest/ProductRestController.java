package in.ashokit.rest;

import java.util.Arrays;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.Product;

@RestController
public class ProductRestController {
	
	@GetMapping(value= "/product", produces="application/xml")//----To handle XML responses, you need to add 
	//@GetMapping(value= "/product", produces="application/json")//the Jackson Dataformat-XML dependency to your pom.xml file.
	public ResponseEntity<Product> getProduct(){
		Product p = new Product(1,"KeyBoard",2225.0);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	@GetMapping(value= "/producttt/{pid}", produces="application/json")
	public ResponseEntity<Product> getProducttt(@PathVariable Integer pid){
		Product p = new Product(pid,"Kd",2225.0);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		Product p1 = new Product(1,"KeyBoard",725.0);
		Product p2 = new Product(2,"Mouse",225.0);
		Product p3 = new Product(3,"Violin",11125.0);
		List<Product> pList = Arrays.asList(p1,p2,p3);
		
		return new ResponseEntity<List<Product>>(pList,HttpStatus.OK);
		
	}
	
	@PostMapping("/productp")
	public ResponseEntity<String> addProduct(@RequestBody Product p){
		System.out.println(p);
		//logic to save product
		
		String msg = "Product Saved";
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	

}
