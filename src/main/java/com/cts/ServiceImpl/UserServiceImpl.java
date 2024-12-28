package com.cts.ServiceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Dao.UserDao;
import com.cts.JWT.CustomerUserDetailsService;
import com.cts.JWT.JwtFilter;
import com.cts.JWT.JwtUtil;
import com.cts.POJO.User;
import com.cts.Service.UserService;

import lombok.extern.slf4j.Slf4j;

import com.cts.Utils.CafeUtils;
import com.cts.Constants.CafeConstants;

//For Loggers
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	 @Autowired
	  private PasswordEncoder passwordEncoder;
	
	//used in login
	@Autowired
	AuthenticationManager authenticationManager;
	
	//used in login
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	JwtFilter jwtFilter;
	
	//ResponseEntity is the return type. it is a class in Spring that represents the complete HTTP response
	//requestMap is used to map request to controller
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		
		//Loggers
		//if the credentials are valid then only if statement will run otherwise it will be INVALID DATA and a bad request
		log.info("Inside signup{}", requestMap);
		try {
			
		if(validateSignUpMap(requestMap))
		{
			
			//if email exist in our database during signup then it will return Bad Request if not then the credentials will be saved in userDao and returns Successfully registered
			//getting the object from userDao
			User user = userDao.findByEmailId(requestMap.get("email"));
			if(Objects.isNull(user)) 
			{
				userDao.save(getUserFromMap(requestMap));
				return CafeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
			}
			else
			{
				return CafeUtils.getResponseEntity("email already exists.", HttpStatus.BAD_REQUEST);
			}
		} 
		else
		{
			return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA,HttpStatus.BAD_REQUEST);
		}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	//Validating that all the info are present or not
	    private boolean validateSignUpMap(Map<String, String> requestMap) {
	        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") 
	        		&& requestMap.containsKey("email") && requestMap.containsKey("password")) 
	        {
	            return true;
	        }
	        return false;
	    }
	    
	  //Extract the value from the map and set it in the user and return the user object. After returning the user object it is saved in
	    private User getUserFromMap(Map<String,String> requestMap) {
	    	 User user = new User();
	         user.setName(requestMap.get("name"));
	         user.setContactNumber(requestMap.get("contactNumber"));
	         user.setEmail(requestMap.get("email"));
	         //user.setPassword(requestMap.get("password"));
	         user.setPassword(passwordEncoder.encode(requestMap.get("password")));
	         //user.setStatus("false");
	         user.setStatus("true");
	         //user.setRole("user");
	         user.setRole(requestMap.get("role"));
	         return user;
	    }
	
	    
	    
	    //login
	    @Override
	    public ResponseEntity<String> login(Map<String, String> requestMap) {
	        log.info("Inside login"); //requestMap);
	        try {
	        	//we extract the email and password and check that it is authenticate or not
	            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
	            
	            if (auth.isAuthenticated()) {
	            	//if the user is authenticated by the admin then only token is generated and given otherwise it shows wait for admin approval
	                if (customerUserDetailsService.getUserDatails().getStatus().equalsIgnoreCase("true")) {
	                	log.info("Login is Succesfully done"); //**
	                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtil.generateToken(
	                            customerUserDetailsService.getUserDatails().getEmail(), customerUserDetailsService.getUserDatails().getRole()) + "\"}",
	                            HttpStatus.OK);
	                } else {
	                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for Admin Approval." + "\"}",
	                            HttpStatus.BAD_REQUEST);
	                }
	            }
	        } catch (Exception ex) {
	            log.error("{}", ex);
	        }
	        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}",
	                HttpStatus.BAD_REQUEST);
	    }

	    
	    

}
