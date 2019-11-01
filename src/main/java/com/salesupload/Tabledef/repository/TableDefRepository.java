package com.salesupload.Tabledef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesupload.Tabledef.enitity.Tabledefinition;

@Repository
public interface TableDefRepository extends JpaRepository<Tabledefinition, Integer> {
	
}
