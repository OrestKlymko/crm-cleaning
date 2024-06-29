package org.cleaning.crm.worker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cleaning.crm.client.enums.Sex;
import org.cleaning.crm.order.entity.Order;
import org.cleaning.crm.time.RangeTime;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "workers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String numberPhone;
	private LocalDate birthday;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(
			name = "worker_cleaning_time_schedule",
			joinColumns = @JoinColumn(name = "worker_id")
	)
	private List<RangeTime> nonAvailableTime;
	@ManyToMany(mappedBy = "workers")
	private Set<Order> orders;
	private int hourRate;
}
