package org.cleaning.crm.client.repository;


import org.springframework.lang.NonNull;
import org.cleaning.crm.client.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
	@Override
	@NonNull
	Page<Client> findAll(Pageable pageable);
}
