package org.cleaning.crm.worker.service;


import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.cleaning.crm.exception.NotFoundException;
import org.cleaning.crm.worker.entity.Worker;
import org.cleaning.crm.worker.mapper.WorkerMapper;
import org.cleaning.crm.worker.model.CreateWorkerRequest;
import org.cleaning.crm.worker.model.UpdateWorkerRequest;
import org.cleaning.crm.worker.model.WorkerMainInfoResponse;
import org.cleaning.crm.worker.model.WorkerResponse;
import org.cleaning.crm.worker.repository.WorkerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkerService {
	private final WorkerRepository workerRepository;

	public void createWorker(CreateWorkerRequest workerRequest) {
		Worker worker = Worker.builder()
				.firstName(workerRequest.firstName())
				.lastName(workerRequest.lastName())
				.email(workerRequest.email())
				.numberPhone(workerRequest.numberPhone())
				.birthday(workerRequest.birthday())
				.hourRate(workerRequest.hourRate())
				.sex(workerRequest.sex())
				.nonAvailableTime(new ArrayList<>())
				.orders(new HashSet<>())
				.build();

		workerRepository.save(worker);
	}

	public void deleteWorker(Long workerId) {
		workerRepository.deleteById(workerId);
	}

	public void updateWorker(long workerId, UpdateWorkerRequest workerRequest) {
		workerRepository.findById(workerId).ifPresent(worker -> {
			worker.setFirstName(workerRequest.firstName());
			worker.setLastName(workerRequest.lastName());
			worker.setBirthday(workerRequest.birthday());
			worker.setNumberPhone(workerRequest.numberPhone());
			worker.setEmail(workerRequest.email());
			worker.setSex(workerRequest.sex());
			worker.setHourRate(workerRequest.hourRate());
			worker.setOrders(workerRequest.orders());
			worker.setNonAvailableTime(workerRequest.nonAvailableTime());

			workerRepository.save(worker);
		});
	}

	public WorkerResponse getWorker(long id) throws NotFoundException {
		return workerRepository.findById(id)
				.map(WorkerResponse::mapToWorkerResponse)
				.orElseThrow(() -> new NotFoundException(String.format("Worker %s not found", id)));
	}

	public List<WorkerResponse> getAllWorkers(Pageable pageable) {
		return workerRepository.findAll(pageable)
				.stream()
				.map(WorkerResponse::mapToWorkerResponse)
				.toList();
	}

	public List<WorkerMainInfoResponse> getAvailableWorkers(LocalDateTime start, LocalDateTime end) {
		List<Tuple> availableWorkers = workerRepository.findAvailableWorkers(start, end);
		return new WorkerMapper(availableWorkers).toMainInfoResponse();
	}
}
