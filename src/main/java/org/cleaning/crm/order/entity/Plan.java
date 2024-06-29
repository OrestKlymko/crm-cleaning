package org.cleaning.crm.order.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "plans")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private Long price;
	@OneToOne(mappedBy = "plan")
	private Order order;
}
