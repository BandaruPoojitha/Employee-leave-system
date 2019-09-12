package com.cmi.lms.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmi.lms.beans.Login;

public interface LoginRepo extends CrudRepository<Login, String> {
	@Query("select s from Login s where s.username=:username")
	Login findType(String username);

	@Transactional
	@Modifying
	@Query("update Login l set l.employeeType=:type where l.employeeId.employeeId=:managerId")
	void updaterole(String managerId, String type);
@Query("select l from Login l ")
	ArrayList<Login> getEmployeeid();
@Query("select l from Login l where l.username=:username and l.password=:password")
Login login(String username, String password);

}
