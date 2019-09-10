package com.cmi.lms.repository;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmi.lms.beans.BalanceLeaves;
import com.cmi.lms.beans.Employee;
@EntityScan
@Repository
public interface BalanceRepo extends JpaRepository<BalanceLeaves,Integer>{
    @Query("select b from BalanceLeaves b where b.employeeId.employeeId=:employee")
	ArrayList<BalanceLeaves> getBalanceLeaves(String employee);
	@Transactional
	@Modifying
    @Query("update BalanceLeaves b set b.LOP=:days where b.employeeId=:employeeId")
	void updateLOPCount( int days, Employee employeeId);
    @Query("select b.LOP from BalanceLeaves b where b.employeeId=:employeeId")
	int getLOP(Employee employeeId);
    @Query("select b.Paid from BalanceLeaves b where b.employeeId=:employeeId")
	int getPaid(Employee employeeId);
	@Transactional
	@Modifying
    @Query("update BalanceLeaves b set b.Paid=:days where b.employeeId=:employeeId")
	void updatePaidCount( int days, Employee employeeId);
}
