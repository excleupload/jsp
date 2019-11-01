package com.salesupload.tablecreationdetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.salesupload.tablecreationdetail.entity.Tablecreationdetail;

@Repository
public interface TablecreationdetailRepository extends JpaRepository<Tablecreationdetail, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update tabledefinition set tablecreated='Yes' where tabledefinitionid = ?1", nativeQuery = true)
	public void updateTablecreated(Integer tabledefinitionid);
}
