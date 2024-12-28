package com.cts.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {
	
	//to prevent the creation of instances of that class directly and enforce the use of the class through its static methods
	private CafeUtils() {
		
	}

	public static ResponseEntity<String> getResponseEntity(String responseMessage , HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
}
