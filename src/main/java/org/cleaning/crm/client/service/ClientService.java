package org.cleaning.crm.client.service;


import lombok.AllArgsConstructor;
import org.cleaning.crm.client.entity.Client;
import org.cleaning.crm.client.model.ClientRequest;
import org.cleaning.crm.client.repository.ClientRepository;
import org.cleaning.crm.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
	private final ClientRepository clientRepository;


	public void createClient(ClientRequest clientRequest) {
		Client client = Client.builder()
				.firstName(clientRequest.firstName())
				.lastName(clientRequest.lastName())
				.sex(clientRequest.sex())
				.email(clientRequest.email())
				.numberPhone(clientRequest.numberPhone())
				.buildings(new ArrayList<>())
				.build();
		clientRepository.save(client);
	}

	public void deleteClient(long id) {
		clientRepository.deleteById(id);
	}

	public ClientRequest updateClient(long clientId, ClientRequest clientRequest) throws NotFoundException {
		Optional<Client> client = clientRepository.findById(clientId);
		if (client.isEmpty()) {
			throw new NotFoundException(String.format("Client %s not found", clientId));
		}

		client.get().setEmail(clientRequest.email());
		client.get().setSex(clientRequest.sex());
		client.get().setNumberPhone(clientRequest.numberPhone());
		client.get().setLastName(clientRequest.lastName());
		client.get().setFirstName(clientRequest.firstName());

		return ClientRequest
				.toClientRequest(clientRepository.save(client.get()));
	}

	public ClientRequest getClient(long clientId) throws NotFoundException {
		return clientRepository.findById(clientId)
				.map(ClientRequest::toClientRequest)
				.orElseThrow(() -> new NotFoundException(String.format("Client %s not found", clientId)));
	}


	public Page<ClientRequest> getAllClients(Pageable pageable) {
		return clientRepository.findAll(pageable)
				.map(ClientRequest::toClientRequest);
	}


}
