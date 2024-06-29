package org.cleaning.crm.worker.controller;


import lombok.AllArgsConstructor;
import org.cleaning.crm.exception.NotFoundException;
import org.cleaning.crm.worker.model.CreateWorkerRequest;
import org.cleaning.crm.worker.model.UpdateWorkerRequest;
import org.cleaning.crm.worker.model.WorkerResponse;
import org.cleaning.crm.worker.service.WorkerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/worker")
public class WorkerController {
	private final WorkerService workerService;

	@GetMapping
	public List<WorkerResponse> getAllWorkers(Pageable pageable) {
		return workerService.getAllWorkers(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getWorker(@PathVariable long id) {
		try {
			return ResponseEntity.ok(workerService.getWorker(id));
		} catch (NotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public void createWorker(@RequestBody CreateWorkerRequest request) {
		workerService.createWorker(request);
	}

	@DeleteMapping("/{id}")
	public void deleteWorker(@PathVariable long id) {
		workerService.deleteWorker(id);
	}

	@PutMapping("/{id}")
	public void updateWorker(@PathVariable long id, @RequestBody UpdateWorkerRequest request) {
		workerService.updateWorker(id, request);
	}
}
