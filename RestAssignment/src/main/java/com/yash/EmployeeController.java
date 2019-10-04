/**
 * 
 */
package com.yash;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mitali
 *
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController extends ResourceSupport {
	Employee emp1 = new Employee();
	@Autowired
	private EmployeeService employeeService;

	

	@PostMapping
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeService.save(emp);
	}

	@GetMapping
	public List<Employee> getAllEmployees() {

		return employeeService.findAll();
	}

	@GetMapping(path = "/{id}", produces = {"application/xml", "application/json"})
	public Resource<Employee> getEmployeeById(@PathVariable(value = "id") Long empId) {

		Optional<Employee> emp = employeeService.findOne(empId);

		if (!emp.isPresent() ) {
			throw new EmployeeNotFoundException("no employee found");
		}
		Resource<Employee> resource = new Resource<Employee>(emp.get());
		Link selfLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).getAllEmployees()).withSelfRel();
		resource.add(selfLink);

		return resource;

	}

	@PutMapping(path="/{id}",produces={"application/xml","application/json"},consumes={"application/xml","application/json"})
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empId,
			@Valid @RequestBody Employee empDetails) {

		Optional<Employee> emp = employeeService.findOne(empId);
		if (!emp.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		emp.get().setName(empDetails.getName());
		emp.get().setDesigntion(empDetails.getDesigntion());

		Employee updateEmployee = employeeService.save(emp.get());
		return ResponseEntity.ok().body(updateEmployee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empid) {
		Optional<Employee> emp = employeeService.findOne(empid);
		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		employeeService.delete(emp.get());
		return ResponseEntity.ok().build();

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void DeleteAll() {
		employeeService.deleteAll();
	}

}
