package org.cleaning.crm.building.controller;


import lombok.AllArgsConstructor;
import org.cleaning.crm.building.model.BuildingRequest;
import org.cleaning.crm.building.service.BuildingService;
import org.cleaning.crm.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/building")
public class BuildingController {
	private final BuildingService buildingService;

	@GetMapping("/all/{id}")
	public ResponseEntity<Page<BuildingRequest>> getAllBuildings(@PathVariable long id) {
		try {
			return ResponseEntity.ok(buildingService.getAllBuildings(id));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/{build-id}")
	public ResponseEntity<BuildingRequest> getBuilding(@PathVariable("build-id") Long buildId) {
		try {
			return ResponseEntity.ok(buildingService.getBuilding(buildId));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping("/{client-id}")
	public void createBuilding(@RequestBody BuildingRequest request, @PathVariable("client-id") Long clientId) {
		buildingService.createBuilding(request, clientId);
	}

	@PutMapping("/{build-id}")
	public void updateBuilding(@RequestBody BuildingRequest request, @PathVariable("build-id") Long buildId) {
		buildingService.updateBuilding(request, buildId);
	}

	@DeleteMapping("/{build-id}")
	public void updateBuilding(@PathVariable("build-id") Long buildId) {
		buildingService.deleteBuilding(buildId);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}


}
