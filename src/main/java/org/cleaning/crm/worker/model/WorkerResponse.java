package org.cleaning.crm.worker.model;

import lombok.Builder;
import org.cleaning.crm.client.enums.Sex;
import org.cleaning.crm.order.entity.Order;
import org.cleaning.crm.time.RangeTime;
import org.cleaning.crm.worker.entity.Worker;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
public record WorkerResponse(
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
	public static WorkerResponse mapToWorkerResponse(Worker worker) {
		return WorkerResponse.builder()
				.firstName(worker.getFirstName())
				.lastName(worker.getLastName())
				.email(worker.getEmail())
				.numberPhone(worker.getNumberPhone())
				.birthday(worker.getBirthday())
				.sex(worker.getSex())
				.hourRate(worker.getHourRate())
				.nonAvailableTime(worker.getNonAvailableTime())
				.orders(worker.getOrders())
				.build();

	}
}
