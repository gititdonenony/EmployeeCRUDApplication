package com.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.DTO.EmployeeDTO;
import com.employee.entity.EmployeeEntity;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setEmail(employeeDTO.getEmail());
		employeeDTO.setId(employee.getId());
		employeeRepository.save(employee);
		return employeeDTO;
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent()) {
			EmployeeEntity employee = employeeOptional.get();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setId(employee.getId());
			employeeDTO.setFirstName(employee.getFirstName());
			employeeDTO.setLastName(employee.getLastName());
			employeeDTO.setEmail(employee.getEmail());
			return employeeDTO;
		}
		return null;
	}


	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return employeeRepository.findAll().stream().map(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setId(employee.getId());
			employeeDTO.setFirstName(employee.getFirstName());
			employeeDTO.setLastName(employee.getLastName());
			employeeDTO.setEmail(employee.getEmail());
			return employeeDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
		Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent()) {
			EmployeeEntity employee = employeeOptional.get();
			employee.setFirstName(employeeDTO.getFirstName());
			employee.setLastName(employeeDTO.getLastName());
			employee.setEmail(employeeDTO.getEmail());
			employeeRepository.save(employee);
			return employeeDTO;
		}
		return null;
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);

	}

}
