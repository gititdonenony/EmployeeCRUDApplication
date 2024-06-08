package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.DTO.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	EmployeeDTO getEmployeeById(Long id);

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

	void deleteEmployee(Long id);
}
