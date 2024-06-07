package com.employeredge.controller;

import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employeredge.entity.Employee;
import com.employeredge.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/index")
	public String home(Model m) {
		List<Employee> employee = service.getAllEmployees();
		m.addAttribute("employee", employee);
		return "index";
	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}

	@PostMapping("/register")
	public String employeeRegister(@ModelAttribute Employee j, HttpSession session, Model m) {
		System.out.println(j);
		service.addJob(j);
		session.setAttribute("msg", "Employee Added Successfully");

		 //Update model with the latest list of employees
		List<Employee> employee = service.getAllEmployees();
		m.addAttribute("employee", employee);

		return "index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
		Employee j = service.getEmployeeById(id);
		m.addAttribute("employee", j);
		return "edit";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee j, HttpSession session, Model m) {
		service.addJob(j);
		session.setAttribute("msg", "Employee data updated Successfully");

		// Update model with the latest list of employees
		List<Employee> employee = service.getAllEmployees();
		m.addAttribute("employee", employee);

		return "index";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session, Model m) {
		service.deleteEMp(id);
		session.setAttribute("msg", "Employee Data Deleted Successfully");

		// Update model with the latest list of employees
		List<Employee> employee = service.getAllEmployees();
		m.addAttribute("employee", employee);

		return "index";
	}
}
