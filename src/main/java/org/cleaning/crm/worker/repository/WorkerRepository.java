package org.cleaning.crm.worker.repository;

import org.cleaning.crm.worker.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
	@Override
	@NonNull
	Page<Worker> findAll(Pageable pageable);


}
