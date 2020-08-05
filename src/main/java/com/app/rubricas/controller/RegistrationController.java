package com.app.rubricas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.rubricas.model.User;
import com.app.rubricas.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String temEmailId = user.getEmailId();
		if(temEmailId != null && "".equals(temEmailId)) {
			User userobj = service.fetchUserByEmailId(temEmailId);
			if(userobj != null) {
				throw new Exception("Usuario con" + temEmailId+" ya existe");
			}
		}
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;
	}
	
	
	@PostMapping("login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		String temPass = user.getPassword();
		User userObj = null;
		if(tempEmailId != null && temPass != null) {
			userObj = service.fetchUserByEmailIdAndPassword(tempEmailId, temPass);
		}
		if(userObj == null) {
			throw new Exception("Credencial incorrecto");
		}
		return userObj;
	}

}
