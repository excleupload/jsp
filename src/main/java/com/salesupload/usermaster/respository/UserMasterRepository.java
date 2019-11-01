package com.salesupload.usermaster.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesupload.usermaster.enitity.Usermaster;

@Repository
public interface UserMasterRepository extends JpaRepository<Usermaster, Integer>{

}
