package com.salesupload.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesupload.usermaster.enitity.Usermaster;

public interface UserRepository extends JpaRepository<Usermaster, Integer>{

	@Query(value=" SELECT userid, login,emailid, password from usermaster WHERE login = :login and password = :password and active=1",nativeQuery=true)
	public List<?> findByloginAndPassword(@Param("login") String login,@Param("password") String password);
	

	public Usermaster findBylogin(String login);
	
}
