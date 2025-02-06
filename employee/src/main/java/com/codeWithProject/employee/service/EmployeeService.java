package com.codeWithProject.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeWithProject.employee.entity.Employee;
import com.codeWithProject.employee.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service

public class EmployeeService {
	@Autowired
    private   EmployeeRepository employeeRepository;
	 @Transactional
    public Employee postEmployee(Employee employee) {
      
Employee savedEmployee = employeeRepository.save(employee);
       
        employeeRepository.flush();  // Optional, depending on your use case

        return savedEmployee;
    }
	 
	 public List<Employee> getAllEmployee()
	 {
		 return employeeRepository.findAll();
	 }
	 public void deleteEmployees(Long id)
	 {
		 if(!employeeRepository.existsById(id))
		 {
			 throw new EntityNotFoundException("Employee with ID " + id + "not found");
		 }
		 employeeRepository.deleteById(id);
	 }
	 public Employee getEmployeeById(Long id)
	 {
		 return employeeRepository.findById(id).orElse(null);
	 }
	
	 public Employee updateEmployee(Long id ,Employee employee)
	 {
		 Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		 if(optionalEmployee.isPresent())
		 {
			 Employee existingEmployee = optionalEmployee.get();
			 existingEmployee.setEmail(employee.getEmail());
			 existingEmployee.setName(employee.getName());
			 existingEmployee.setPhone(employee.getPhone());
			 existingEmployee.setDepartment(employee.getDepartment());
			 return employeeRepository.save(existingEmployee);
		 }
		 return null;
	 }
	 
}
