package com.cts.Rest;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//This annotation specifies the base path for all methods, /user is the base path
@RequestMapping(path="/user")
public interface UserRest {
	
	
	//SignUp
	@PostMapping(path="/signup")
	public ResponseEntity<String> signUp(@RequestBody(required=true) Map<String, String> requestMap);
	
	
	 @PostMapping(path = "/login")
	    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);
}
