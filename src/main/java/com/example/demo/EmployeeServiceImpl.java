package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());
        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryGreaterThan(Double minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.searchByName(name);
    }

    @Override
    public List<String> getAllEmployeeEmails() {
        return employeeRepository.getAllEmails();
    }

    @Override
    public Double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }
}

