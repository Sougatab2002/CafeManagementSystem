package com.cts.Dao;
import com.cts.POJO.User;
//import com.cts.Wrapper.UserWrapper;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

//here we pass the pojo or the model(which is user) and the type of the Id(which is integer) present in User class
public interface UserDao extends JpaRepository<User,Integer>{

	User findByEmailId(@Param("email") String email);
	
	//getAllUser
	//List<UserWrapper> getAllUser();
	}
