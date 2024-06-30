package org.cleaning.crm.worker.repository;

import jakarta.persistence.Tuple;
import org.cleaning.crm.worker.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
	@Override
	@NonNull
	Page<Worker> findAll(Pageable pageable);


	@Query(value = """
			SELECT w.id,w.first_name, w.last_name,w.hour_rate
			FROM Worker w 
			LEFT JOIN worker_cleaning_time_schedule wcts ON w.id = wcts.worker_id 
			WHERE (wcts.worker_id IS NULL OR (wcts.end <= :start OR wcts.start >= :end))
			""", nativeQuery = true)
	List<Tuple> findAvailableWorkers(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
