package com.cts.RestImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Constants.CafeConstants;
import com.cts.Rest.CategoryRest;
import com.cts.Service.CategoryService;
import com.cts.Utils.CafeUtils;
import com.cts.POJO.Category;


@RestController
public class CategoryRestImpl implements CategoryRest{

	
	@Autowired
	CategoryService categoryService;
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try{
			return categoryService.addNewCategory(requestMap);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 
	 //getAllCategory
		public List<Category> getAllCategory(){
		 return categoryService.getAllCategory();
	 }
	 
	 
	 //updateCategory
	 @Override
	    public ResponseEntity<String> update(Map<String, String> requestMap) {
	        try {
	            return categoryService.updateCategory(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
}
