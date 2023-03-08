package net.javaguide;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDataJpaTestApplicationTests {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Order(1)
	public  void saveEmployee() {
		Employee employee = Employee.builder().
				firstName("shaik").lastName("abid").email("abidshaik").build();
		
		employeeRepository.save(employee);
		
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(2)
	public void getEmployee() {
		Employee  employee = employeeRepository.findById(1L).get();
		
		Assertions.assertThat(employee.getId()).isEqualTo(1L);
		
	}
	
	@Test
	@Order(3)
	public void ListOfEmployees() {
		List<Employee>employees = employeeRepository.findAll();
		
		Assertions.assertThat(employees.size()).isGreaterThan(0);
	}
	@Test
	@Order(4)
	public void UpdateEmployee() {
		
		Employee employee = employeeRepository.findById(1l).get();
		
		employee.setEmail("ram@123");
		
		Employee  Updated=employeeRepository.save(employee);
		
		Assertions.assertThat(Updated.getEmail()).isEqualTo("ram@123");
		
	}
	@Test
	@Order(5)
	public void DeleteEmpById() {
		
		Employee employee = employeeRepository.findById(1L).get();
		
		employeeRepository.delete(employee);
		
		Employee employee2 =null;
		
		Optional<Employee>optionalemp = employeeRepository.findByEmail("ram@123");
		
		if(optionalemp.isPresent()) {
			employee2=optionalemp.get();
		}
		Assertions.assertThat(employee2).isNull();
		
		
	}
	
	
	

}
