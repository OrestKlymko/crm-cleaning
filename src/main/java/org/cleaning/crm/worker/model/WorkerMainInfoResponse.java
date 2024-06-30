package org.cleaning.crm.worker.model;

import lombok.Builder;

@Builder
public record WorkerMainInfoResponse(
		Long workerId,
		String firstName,
		String lastName,
		int hourRate
) {
}
