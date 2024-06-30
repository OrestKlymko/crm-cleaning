package org.cleaning.crm.order.model;

import org.cleaning.crm.building.enums.TypeBuilding;

public record OrderCreateRequest(
		String source,
		String medium,
		String campaign,
		TypeBuilding typeBuilding,
		Integer planId,
		Long clientId,
		Long buildId,
		Integer numberOfWorkers

) {
}
