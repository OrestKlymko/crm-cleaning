package org.cleaning.crm.worker.controller;


import lombok.AllArgsConstructor;
import org.cleaning.crm.exception.NotFoundException;
import org.cleaning.crm.worker.model.CreateWorkerRequest;
import org.cleaning.crm.worker.model.UpdateWorkerRequest;
import org.cleaning.crm.worker.model.WorkerMainInfoResponse;
import org.cleaning.crm.worker.model.WorkerResponse;
import org.cleaning.crm.worker.service.WorkerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

	@GetMapping("/{start}/{end}")
	public List<WorkerMainInfoResponse> getAvailableWorkers(
			@PathVariable LocalDateTime start,
			@PathVariable LocalDateTime end) {
		return workerService.getAvailableWorkers(start, end);
	}

	@GetMapping("/{id}")
	public ResponseEntity<WorkerResponse> getWorker(@PathVariable long id) {
		try {
			return ResponseEntity.ok(workerService.getWorker(id));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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


	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
