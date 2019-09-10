package com.cmi.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmi.lms.beans.Employee;

@Repository
public interface EmpoyleeRepo extends JpaRepository<Employee, String> {
	@Transactional
	@Modifying
	@Query("update Employee e set e.address=:address where e.employeeId=:employeeId")
	void editAddress(String address, String employeeId);

	@Transactional
	@Modifying
	@Query("update Employee e set e.email=:email where e.employeeId=:employeeId")
	void editEmail(String email, String employeeId);

	@Transactional
	@Modifying
	@Query("update Employee e set e.phonenumber=:contact where e.employeeId=:employeeId")
	void editContact(String contact, String employeeId);

}
