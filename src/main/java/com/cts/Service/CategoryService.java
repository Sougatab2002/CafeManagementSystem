package com.cts.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cts.POJO.Category;

public interface CategoryService {

	ResponseEntity<String> addNewCategory(Map<String,String>requestMap);
	
	//ResponseEntity<List<Category>> getAllCategory(String Value);
	List<Category> getAllCategory();
	
	ResponseEntity<String> updateCategory(Map<String,String> requestMap);
}

