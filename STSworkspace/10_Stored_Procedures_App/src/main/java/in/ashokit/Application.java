package in.ashokit;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.entity.Products;
import in.ashokit.repository.ProductsRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		ProductsRepository productsRepo = ctxt.getBean(ProductsRepository.class);
		List<Products> products= productsRepo.getAllProducts();
		products.forEach(System.out::println);
	}

}
