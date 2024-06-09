package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
