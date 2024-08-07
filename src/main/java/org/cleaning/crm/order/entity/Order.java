package org.cleaning.crm.order.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cleaning.crm.client.entity.Client;
import org.cleaning.crm.order.enums.Sequence;
import org.cleaning.crm.order.enums.Status;
import org.cleaning.crm.time.RangeTime;
import org.cleaning.crm.worker.entity.Worker;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Client client;

	@ManyToMany
	@JoinTable(
			name = "order_worker",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "worker_id")
	)
	private Set<Worker> workers;

	private long totalPrice;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Embedded
	private RangeTime rangeTime;

	@OneToOne
	@JoinColumn(name = "source_id", referencedColumnName = "id")
	private Source source;

	@OneToOne
	@JoinColumn(name = "plan_id", referencedColumnName = "id")
	private Plan plan;

	@Enumerated(EnumType.STRING)
	private Sequence sequence;

	private final LocalDateTime createdAt = LocalDateTime.now();
}
