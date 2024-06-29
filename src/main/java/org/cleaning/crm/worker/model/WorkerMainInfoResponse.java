package org.cleaning.crm.worker.model;

import lombok.Builder;

@Builder
public record WorkerMainInfoResponse(
		String firstName,
		String lastName,
		int hourRate
) {
}
