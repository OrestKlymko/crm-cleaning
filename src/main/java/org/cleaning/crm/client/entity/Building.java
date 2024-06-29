package org.cleaning.crm.client.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.cleaning.crm.client.enums.TypeBuilding;

@Entity
@Table(name = "Building")
@Data
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String street;
	private String state;
	private int stage;
	private int numberHouse;
	private int cleaningRoom;
	@Enumerated(EnumType.STRING)
	private TypeBuilding typeBuilding;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
}
