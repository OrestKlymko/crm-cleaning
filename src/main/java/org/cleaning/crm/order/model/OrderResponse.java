package org.cleaning.crm.order.model;

import lombok.Builder;
import org.cleaning.crm.order.entity.Order;
import org.cleaning.crm.order.entity.Plan;
import org.cleaning.crm.order.entity.Source;
import org.cleaning.crm.order.enums.Status;
import org.cleaning.crm.time.RangeTime;
import org.cleaning.crm.worker.entity.Worker;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record OrderResponse(
		Set<Worker> workers,
		long totalPrice,
		String source,
		String planTitle,
		Long planPrice,
		LocalDateTime createdAt,
		RangeTime rangeTime,
		Status status

) {
	public static OrderResponse toOrderResponse(Order order){
		return OrderResponse.builder()
				.workers(order.getWorkers())
				.totalPrice(order.getTotalPrice())
				.source(order.getSource().getSource())
				.planTitle(order.getPlan().getTitle())
				.planPrice(order.getPlan().getPrice())
				.createdAt(order.getCreatedAt())
				.rangeTime(order.getRangeTime())
				.status(order.getStatus())
				.build();
	}
}
