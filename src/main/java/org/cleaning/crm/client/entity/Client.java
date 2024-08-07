package org.cleaning.crm.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cleaning.crm.building.entity.Building;
import org.cleaning.crm.client.enums.Sex;

import java.util.List;

@Entity
@Table(name = "clients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String numberPhone;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@OneToMany(mappedBy = "clients", cascade = CascadeType.ALL)
	private List<Building> buildings;
}
