/**
 * 
 */
package com.yash;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mitali
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findOne(Long empid) {

		/*Optional<Employee> emp = employeeRepository.findById(empid);
		if (!emp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not found.");
		} else {
			return emp.get();
		}*/

		// return employeeRepository.findById(empid).orElseThrow(()-> new EmployeeNotFoundException("Employee not found."));

		// return employeeRepository.findById(empid).get();
		return  employeeRepository.findById(empid);
	}

	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

	public void deleteAll() {
		employeeRepository.deleteAll();
	}
}
