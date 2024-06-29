package org.cleaning.crm.building.service;

import lombok.AllArgsConstructor;
import org.cleaning.crm.building.entity.Building;
import org.cleaning.crm.building.model.BuildingRequest;
import org.cleaning.crm.building.repository.BuildingRepository;
import org.cleaning.crm.client.entity.Client;
import org.cleaning.crm.client.repository.ClientRepository;
import org.cleaning.crm.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BuildingService {
	private final BuildingRepository buildingRepository;
	private final ClientRepository clientRepository;

	public void createBuilding(BuildingRequest request, long clientId) {
		clientRepository.findById(clientId).ifPresent(client -> {
			Building building = Building.builder()
					.client(client)
					.street(request.street())
					.state(request.state())
					.street(request.street())
					.stage(request.stage())
					.numberHouse(request.numberHouse())
					.cleaningRoom(request.cleaningRoom())
					.typeBuilding(request.typeBuilding())
					.build();
			buildingRepository.save(building);
		});
	}

	public void updateBuilding(BuildingRequest request, long buildId) {
		buildingRepository.findById(buildId).ifPresent(building -> {
			Building buildingToUpdate = Building.builder()
					.street(request.street())
					.state(request.state())
					.street(request.street())
					.stage(request.stage())
					.numberHouse(request.numberHouse())
					.cleaningRoom(request.cleaningRoom())
					.typeBuilding(request.typeBuilding())
					.build();
			buildingRepository.save(buildingToUpdate);
		});
	}

	public void deleteBuilding(Long id) {
		buildingRepository.deleteById(id);
	}

	public BuildingRequest getBuilding(Long id) throws NotFoundException {
		return buildingRepository.findById(id)
				.map(BuildingRequest::toRequest)
				.orElseThrow(() -> new NotFoundException(String.format("Building %s not found", id)));
	}

	public Page<BuildingRequest> getAllBuildings(Long clientId) throws NotFoundException {
		Optional<Client> client = clientRepository.findById(clientId);
		if (client.isPresent()) {
			return buildingRepository.findBuildingsByClient(client.get())
					.map(BuildingRequest::toRequest);
		}
		throw new NotFoundException(String.format("Client %s not found", clientId));
	}
}
