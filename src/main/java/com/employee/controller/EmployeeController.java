package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.DTO.EmployeeDTO;
import com.employee.service.EmployeeService;

@Controller
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.createEmployee(employeeDTO);
		return ResponseEntity.ok("Employee created successfully!");
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<String> getEmployeeById(@PathVariable Long id) {
		EmployeeDTO employee = employeeService.getEmployeeById(id);

		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Employee with ID " + id + " was not found in our records.");
		}

		return ResponseEntity.ok(employee.toString());
	}

	@GetMapping("/all")
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO existingEmployee = employeeService.getEmployeeById(id);

		if (existingEmployee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Employee with ID " + id + " was not found in our records.");
		}
		employeeService.updateEmployee(id, employeeDTO);
		return ResponseEntity.ok("Record updated successfully");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		EmployeeDTO existingEmployee = employeeService.getEmployeeById(id);

		if (existingEmployee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Employee with ID " + id + " was not found in our records.");
		}

		employeeService.deleteEmployee(id);
		return ResponseEntity.ok("Record with ID " + id + " is deleted");
	}
}
