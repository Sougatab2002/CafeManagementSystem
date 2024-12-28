package com.cts.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;


import com.cts.POJO.Product;

public interface ProductService {

	ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
	
	List<Product> getAllProducts();
	
	ResponseEntity<String> updateProduct(Map<String,String> requestMap);
	
	ResponseEntity<String> deleteProduct(Integer id);
}
