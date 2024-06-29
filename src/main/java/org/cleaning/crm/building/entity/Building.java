package org.cleaning.crm.building.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cleaning.crm.client.entity.Client;
import org.cleaning.crm.client.enums.TypeBuilding;

@Entity
@Table(name = "building")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
