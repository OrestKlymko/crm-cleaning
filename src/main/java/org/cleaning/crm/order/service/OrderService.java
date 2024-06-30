package org.cleaning.crm.order.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.cleaning.crm.building.entity.Building;
import org.cleaning.crm.client.entity.Client;
import org.cleaning.crm.client.repository.ClientRepository;
import org.cleaning.crm.exception.NotFoundException;
import org.cleaning.crm.order.entity.Order;
import org.cleaning.crm.order.entity.Plan;
import org.cleaning.crm.order.enums.Status;
import org.cleaning.crm.order.model.OrderCreateRequest;
import org.cleaning.crm.order.model.OrderResponse;
import org.cleaning.crm.order.repository.OrderRepository;
import org.cleaning.crm.order.repository.PlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final ClientRepository clientRepository;
	private final PlanRepository planRepository;

	@Transactional
	public void createOrder(OrderCreateRequest request) throws NotFoundException {
		Client client = clientRepository.findById(request.clientId())
				.orElseThrow(() -> new NotFoundException("Client not found"));
		Plan plan = planRepository.findById(request.planId())
				.orElseThrow(() -> new NotFoundException("Plans not found"));
		Building buildingToClean = client.getBuildings().stream().filter(building -> building.getId() == request.buildId()).findFirst()
				.orElseThrow(() -> new NotFoundException("Build not found"));


		int totalPrice = countTotalPrice(buildingToClean, plan, request);

		Order order = Order.builder()
				.client(client)
				.plan(plan)
				.status(Status.PENDING)
				.totalPrice(totalPrice)
				.build();

		orderRepository.save(order);
	}

	private int countTotalPrice(Building buildingToClean, Plan plan, OrderCreateRequest request) {
		return 1;
	}

	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}

	public OrderResponse getOrder(Long orderId) throws NotFoundException {
		return orderRepository.findById(orderId)
				.map(OrderResponse::toOrderResponse)
				.orElseThrow(() -> new NotFoundException(String.format("Order %s not found", orderId)));
	}

	public Page<OrderResponse> getAllOrders(Pageable pageable) {
		return orderRepository.findAll(pageable).map(OrderResponse::toOrderResponse);
	}


}
