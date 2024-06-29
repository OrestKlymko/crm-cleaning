package org.cleaning.crm.building.repository;

import org.cleaning.crm.building.entity.Building;
import org.cleaning.crm.client.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {
	Page<Building> findBuildingsByClient(Client client);
}
