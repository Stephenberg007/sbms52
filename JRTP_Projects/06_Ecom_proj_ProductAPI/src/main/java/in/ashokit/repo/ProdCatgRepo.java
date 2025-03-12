package main.java.in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.in.ashokit.entity.ProductCategory;

public interface ProdCatgRepo extends JpaRepository<ProductCategory,Integer> {

}
