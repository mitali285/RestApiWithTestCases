/**
 * 
 */
package com.yash;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mitali
 *
 */

@RunWith(SpringRunner.class)
public class TestEmployeeService {
	
	@Mock
	EmployeeRepository emprepo;
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Test
	public void TestShouldReturnEmployeeList()
	{
		List<Employee>empList=new ArrayList<Employee>();
		empList.add(new Employee(10L,"vishal","software Engineer"));
		when( emprepo.findAll()).thenReturn(empList);
		employeeService.findAll();
		verify(emprepo).findAll();
	}
	
	@Test
	public void TestEmployeeGetById()
	{	
		Optional<Employee>emp=Optional.of(new Employee(10L,"vishal","software Engineer"));
		when(emprepo.findById((long)10)).thenReturn(emp);
		employeeService.findOne(10L);
		verify(emprepo).findById((long)10);
	}
	
	@Test
	public void TestSaveEmployee()
	{
		Employee emp=new Employee(10L,"Vishal","SE");
		when(emprepo.save(emp)).thenReturn(emp);
		employeeService.save(emp);
		verify(emprepo).save(emp);
	}
	
	@Test
	public void TestEmployeeUpdation()
	{	
		Employee emp=new Employee(10L,"kisan","software Engineer");
		when(emprepo.save(emp)).thenReturn(emp);
		employeeService.save(emp);
		verify(emprepo).save(emp);
	}
	
	@Test
	public void TestDeleteEmployeeByID()
	{	
		Employee emp=new Employee(10L,"kisan","software Engineer");
		doNothing().when(emprepo).delete(emp);
		employeeService.delete(emp);
		verify(emprepo).delete(emp);
	}
	
	@Test
	public void TestDeleteEmployeeAll()
	{	
		Employee emp=new Employee(10L,"kisan","software Engineer");
		doNothing().when(emprepo).deleteAll();
		employeeService.deleteAll();
		verify(emprepo).deleteAll();
	}
	
	
}
