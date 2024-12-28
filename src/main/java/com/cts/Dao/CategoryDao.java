package com.cts.Dao;
import com.cts.POJO.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//here we pass the pojo or the model(which is Category) and the type of the Id(which is integer) present in Category class
public interface CategoryDao extends JpaRepository<Category,Integer>{

	
	List<Category> getAllCategory();
}
