package com.jvr.booking.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jvr.booking.model.Response;
import com.jvr.booking.model.User;
import com.jvr.booking.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        String result = userService.registerUser(user);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
   
    @PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody User user) {
		Response response = new Response();
		ResponseEntity<Response> res = null;
		String userName = userService.getPhone(user);
		String password = userService.getPassword(user);
		if (userName.equals(user.getPhone()) && password.equals(user.getPassword())) {
			response.setStatus("Okay");
			response.setMessage("Success");
			res = new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setStatus("Fail");
			response.setMessage("Username and Password wrong");
			res = new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		return res;
	}
   
}
