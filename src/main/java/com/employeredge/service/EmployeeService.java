package com.employeredge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeredge.entity.Employee;
import com.employeredge.repository.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo repo;

	public void addJob(Employee j) {
		repo.save(j);
	}

	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	public Employee getEmployeeById(int id) {
		Optional<Employee> j = repo.findById(id);
		if (j.isPresent()) {
			return j.get();
		}
		return null;
	}
	public void deleteEMp(int id) {
		repo.deleteById(id);
	}

}
