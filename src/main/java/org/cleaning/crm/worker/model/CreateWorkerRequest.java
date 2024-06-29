package org.cleaning.crm.worker.model;

import org.cleaning.crm.client.enums.Sex;

import java.time.LocalDate;

public record CreateWorkerRequest(
		String firstName,
		String lastName,
		String email,
		String numberPhone,
		LocalDate birthday,
		Sex sex,
		int hourRate
) {
}
