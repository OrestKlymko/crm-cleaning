package org.cleaning.crm.worker.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.cleaning.crm.client.enums.Sex;
import org.cleaning.crm.order.entity.Order;
import org.cleaning.crm.time.RangeTime;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "workers")
@Data
public class Worker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String numberPhone;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
			name = "worker_cleaning_time_schedule",
			joinColumns = @JoinColumn(name = "worker_id")
	)
	private List<RangeTime> cleaningTimeSchedule;
	@ManyToMany(mappedBy = "workers")
	private Set<Order> orders;
	private int hourRate;
}
