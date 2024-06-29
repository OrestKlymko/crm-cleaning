package org.cleaning.crm.building.model;

import lombok.Builder;
import org.cleaning.crm.building.entity.Building;
import org.cleaning.crm.client.enums.TypeBuilding;

@Builder
public record BuildingRequest(
		String street,
		String state,
		int stage,
		int numberHouse,
		int cleaningRoom,
		TypeBuilding typeBuilding
) {

	public static BuildingRequest toRequest(Building building) {
		return BuildingRequest.builder()
				.street(building.getStreet())
				.stage(building.getStage())
				.state(building.getState())
				.numberHouse(building.getNumberHouse())
				.cleaningRoom(building.getCleaningRoom())
				.typeBuilding(building.getTypeBuilding())
				.build();
	}
}
