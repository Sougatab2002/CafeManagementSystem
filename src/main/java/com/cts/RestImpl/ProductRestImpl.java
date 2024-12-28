package com.cts.RestImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Rest.ProductRest;
import com.cts.Service.ProductService;
import com.cts.Constants.CafeConstants;
import com.cts.POJO.Product;
import com.cts.Utils.CafeUtils;

@RestController
public class ProductRestImpl implements ProductRest{

	@Autowired
	ProductService productService;
	
	 @Override
	    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
	        try {
	            //System.out.println("inside userRestImpl");
	            return productService.addNewProduct(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @Override
	 public List<Product> getAllProducts(){
		 return productService.getAllProducts();
	 }

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		try {
			
			return productService.updateProduct(requestMap);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
	@Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            return productService.deleteProduct(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
	 
}
