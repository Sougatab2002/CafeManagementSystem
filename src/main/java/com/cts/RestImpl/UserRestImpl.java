package com.cts.RestImpl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Constants.CafeConstants;
import com.cts.Rest.UserRest;
import com.cts.Service.UserService;
import com.cts.Utils.CafeUtils;

@RestController
public class UserRestImpl implements UserRest{

	@Autowired
	UserService userService;
	//[SignUp Implemented]
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			//This line calls the signUp method of the UserService class, passing the requestMap as an argument. It returns the result of this method call, which is expected to be a ResponseEntity<String>.
			return userService.signUp(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		//[CafeUtils and Something Went Wrong from constants]
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	  @Override
	    public ResponseEntity<String> login(Map<String, String> requestMap) {
	        try {
	            return userService.login(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	

}
