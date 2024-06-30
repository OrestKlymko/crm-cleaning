package org.cleaning.crm.order.controller;


import lombok.AllArgsConstructor;
import org.cleaning.crm.exception.NotFoundException;
import org.cleaning.crm.order.model.OrderCreateRequest;
import org.cleaning.crm.order.model.OrderResponse;
import org.cleaning.crm.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
	private final OrderService orderService;


	@PostMapping
	public void createOrder(@RequestBody OrderCreateRequest request) {
		try {
			orderService.createOrder(request);
		} catch (NotFoundException e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(orderService.getOrder(id));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	public Page<OrderResponse> getOrder(Pageable pageable) {
		return orderService.getAllOrders(pageable);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}


	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}


}
