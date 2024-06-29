package org.cleaning.crm.client.controller;


import lombok.AllArgsConstructor;
import org.cleaning.crm.client.model.ClientRequest;
import org.cleaning.crm.client.service.ClientService;
import org.cleaning.crm.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
	private final ClientService clientService;

	@PostMapping
	public void createClient(@RequestBody ClientRequest clientRequest) {
		clientService.createClient(clientRequest);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientRequest> updateClient(@PathVariable long id, @RequestBody ClientRequest request) {
		try {
			return ResponseEntity.ok(clientService.updateClient(id, request));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable long id) {
		clientService.deleteClient(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientRequest> getClient(@PathVariable long id) {
		try {
			return ResponseEntity.ok(clientService.getClient(id));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	public Page<ClientRequest> getAllClients(Pageable pageable) {
		return clientService.getAllClients(pageable);
	}


	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
