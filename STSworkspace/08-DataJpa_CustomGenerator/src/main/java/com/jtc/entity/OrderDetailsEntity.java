package com.jtc.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name="Order_Dtls")
@Data
public class OrderDetailsEntity {
	
	
	@Id
 @GenericGenerator(name = "order_id_gen", strategy = "com.jtc.generator.OrderIdGenerator")
	@GeneratedValue(generator = "order_id_gen")
	@Column(name="Order_Id")
	private String orderId;
	
	@Column(name="Order_By")
	private String orderBy;
	
	@Column(name="Order_Placed_Date")
	@Temporal(TemporalType.DATE)
	private Date orderPlacedDate;

}
