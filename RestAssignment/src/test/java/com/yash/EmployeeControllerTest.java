/**
 * 
 */
package com.yash;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mitali
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)

public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService mockEmpSer;

	
	@Test
	public void shouldReturnEmployeeByIdJson() throws Exception {
		Optional<Employee> emp = Optional.of(new Employee(1L, "name", "designtion"));

		when(mockEmpSer.findOne(1L)).thenReturn(emp);

		RequestBuilder requestBuilder = get("/employees/1").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockEmpSer, times(1)).findOne(1L);
	}

	@Test
	public void shouldReturnEmployeeByIdXml() throws Exception {
		Optional<Employee> emp = Optional.of(new Employee(1L, "name", "designtion"));

		when(mockEmpSer.findOne(1L)).thenReturn(emp);

		RequestBuilder requestBuilder = get("/employees/1").accept(MediaType.APPLICATION_XML);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockEmpSer, times(1)).findOne(1L);
	}
	
	@Test
	public void TestDeleteEmployee() throws Exception {
		Employee firstEmp = new Employee(10L, "abcd", "software engineer");

		when(mockEmpSer.findOne(10L)).thenReturn(Optional.of(firstEmp));
		doNothing().when(mockEmpSer).delete(Mockito.any(Employee.class));

		ObjectMapper objectMapper = new ObjectMapper();
		String empString = objectMapper.writeValueAsString(firstEmp);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employees/10").content(empString)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

		verify(mockEmpSer, times(1)).delete(firstEmp);
	}


	
	
	@Test
	public void shouldThrowExceptionWhenEmployeeNotFound() throws Exception {

		when(mockEmpSer.findOne(10L)).thenThrow(new EmployeeNotFoundException("Employee Not Found"));

		this.mockMvc.perform(get("/employees/10")).andExpect(status().isNotFound());

		
	}
	
	
	@Test
	public void TestEmployeeUpdate() throws Exception {
		Employee emp = new Employee(1L, "mit", "SE");

		Employee firstEmpResp = new Employee(1L, "abcd", "software engineer");

		when(mockEmpSer.save(emp)).thenReturn(firstEmpResp);
		when(mockEmpSer.findOne(10L)).thenReturn(Optional.of(emp));

		ObjectMapper objectMapper = new ObjectMapper();
		String empString = objectMapper.writeValueAsString(emp);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employees/10").content(empString)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

		verify(mockEmpSer, times(1)).save(emp);
	}

	@Test
	public void TestSaveEmployee() throws Exception {
		Employee emp = new Employee(4L, "name", "designtion");
		ObjectMapper mapper = new ObjectMapper();
		String employeeRequestBody = mapper.writeValueAsString(emp);
		System.out.println("--------" + employeeRequestBody);
		when(mockEmpSer.save(emp)).thenReturn(emp);
		this.mockMvc.perform(post("/employees").content(employeeRequestBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		// .andExpect(status().isCreated());
		// .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

	}

	
	@Test
	public void shouldReturnListOfEmployeeInJsonFormat() throws Exception {
		List<Employee> mockEmployees = new ArrayList<Employee>();
		mockEmployees.add(new Employee(1L, "name", "designtion"));
		mockEmployees.add(new Employee(2L, "name", "designtion"));

		when(mockEmpSer.findAll()).thenReturn(mockEmployees);

		RequestBuilder requestBuilder = get("/employees").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockEmpSer, times(1)).findAll();
	}

	@Test
	public void shouldReturnListOfEmployeeInXmlFormat() throws Exception {
		List<Employee> mockEmployees = new ArrayList<Employee>();
		mockEmployees.add(new Employee(1L, "name", "designtion"));
		mockEmployees.add(new Employee(2L, "name", "designtion"));

		when(mockEmpSer.findAll()).thenReturn(mockEmployees);

		RequestBuilder requestBuilder = get("/employees").accept(MediaType.APPLICATION_XML);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		verify(mockEmpSer, times(1)).findAll();
	}
	
	@Test
	public void TestDeleteAllEmployee()
	{
		mockEmpSer.deleteAll();
	}

}
