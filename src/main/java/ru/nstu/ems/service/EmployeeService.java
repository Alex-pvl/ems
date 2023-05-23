package ru.nstu.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nstu.ems.model.Employee;
import ru.nstu.ems.repository.EmployeeRepository;

import java.util.List;
import java.util.function.Function;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Employee getById(long id) {
		return employeeRepository.findById(id).orElseThrow();
	}

	public void deleteById(long id) {
		Employee employee = getById(id);
		employeeRepository.delete(employee);
	}

	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return employeeRepository.findAll(pageable);
	}
}
