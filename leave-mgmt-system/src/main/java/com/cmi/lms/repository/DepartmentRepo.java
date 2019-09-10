package com.cmi.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmi.lms.beans.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, String> {

}
