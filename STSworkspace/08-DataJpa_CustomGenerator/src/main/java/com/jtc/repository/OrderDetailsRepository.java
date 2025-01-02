package com.jtc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtc.entity.OrderDetailsEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity,Serializable> {
	

}
