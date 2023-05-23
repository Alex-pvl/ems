package ru.nstu.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nstu.ems.model.Employee;
import ru.nstu.ems.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String index(Model model) {
		findPaginated(1, model);
		return "index";
	}

	@GetMapping("/saveForm")
	public String saveForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable("id") long id, Model model) {
		Employee employee = employeeService.getById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id") long id) {
		employeeService.deleteById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable int pageNo, Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		List<Employee> list = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", list);
		return "index";
	}
}
