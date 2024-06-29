package org.cleaning.crm.order.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sources")
public class Source {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String source;
	private String medium;
	private String campaign;
	@OneToOne(mappedBy = "source")
	private Order order;
}
