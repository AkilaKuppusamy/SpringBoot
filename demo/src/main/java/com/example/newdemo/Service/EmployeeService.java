package com.example.newdemo.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.newdemo.Employee;
@Service
public class EmployeeService {
	
	public static ArrayList<Employee> empList = new ArrayList<Employee>();
	
	static {
		Employee roger = Employee.builder().id(1).name("Roger").dob(LocalDate.now().minusYears(20)).build();
        Employee rafa = Employee.builder().id(2).name("Rafa").dob(LocalDate.now().minusYears(10)).build();
        Employee Sara = Employee.builder().id(3).name("Sara").dob(LocalDate.now().minusYears(9)).build();
        Employee Carl = Employee.builder().id(4).name("Carl").dob(LocalDate.now().minusYears(8)).build();
        Employee Chloe = Employee.builder().id(5).name("Chloe").dob(LocalDate.now().minusYears(17)).build();
        empList.add(roger);
        empList.add(rafa);
        empList.add(Sara);
        empList.add(Carl);
        empList.add(Chloe);
	}
			
	
	public List<Employee> getEmployees(){
        
        return empList;
    }

}
