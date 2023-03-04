package com.user.userops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userops.model.ReqRes;
import com.user.userops.service.ReqResService;

@RestController
@RequestMapping("/api/reqres")


public class ReqResController {
	
	@Autowired
	ReqResService reqresService;
	MessageSource messageSource;
	
	@PostMapping(path = "/users" , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<ReqRes> createUser(@RequestBody ReqRes reqres) {
		ReqRes newReqres = reqresService.addUser(reqres);
		if(newReqres == null) {
			ResponseEntity.internalServerError().body(newReqres);
		}

	return new ResponseEntity<ReqRes>(newReqres,HttpStatusCode.valueOf(200));

	}

}
