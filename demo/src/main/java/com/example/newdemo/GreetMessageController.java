package com.example.newdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.newdemo.Service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GreetMessageController {

	@Autowired
	EmployeeService empservice;
	
    @RequestMapping(path = "/api/message",method = RequestMethod.GET)
    public String greet(){
        return "welcome to microservices learning sessions..!";
    }

    @GetMapping(path = "/employees")
    public List<Employee> getEmployees(){
    	return empservice.getEmployees();
    }
}
