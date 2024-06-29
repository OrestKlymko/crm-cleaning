package org.cleaning.crm.client.model;

import lombok.Builder;
import org.cleaning.crm.client.entity.Client;
import org.cleaning.crm.client.enums.Sex;

@Builder
public record ClientRequest(
		String firstName,
		String lastName,
		String email,
		String numberPhone,
		Sex sex
) {
	public static ClientRequest toClientRequest(Client client) {
		return ClientRequest.builder()
				.email(client.getEmail())
				.firstName(client.getFirstName())
				.lastName(client.getLastName())
				.numberPhone(client.getNumberPhone())
				.sex(client.getSex())
				.build();
	}
}
