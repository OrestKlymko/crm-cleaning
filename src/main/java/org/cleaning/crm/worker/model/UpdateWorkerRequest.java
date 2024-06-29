package org.cleaning.crm.worker.model;

import org.cleaning.crm.client.enums.Sex;
import org.cleaning.crm.order.entity.Order;
import org.cleaning.crm.time.RangeTime;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UpdateWorkerRequest(
		String firstName,
		String lastName,
		String email,
		String numberPhone,
		LocalDate birthday,
		Sex sex,
		int hourRate,
		List<RangeTime> nonAvailableTime,
		Set<Order> orders
) {
}
