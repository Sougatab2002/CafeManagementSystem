package com.cts.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.Service.ProductService;
import com.cts.Constants.CafeConstants;
import com.cts.Utils.CafeUtils;
import com.cts.POJO.Category;
import com.cts.POJO.Product;

import lombok.extern.slf4j.Slf4j;

import com.cts.JWT.JwtFilter;
import com.cts.Dao.ProductDao;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	   ProductDao productDao;
	
	@Autowired
    JwtFilter jwtFilter;
	
	@Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        log.info("Inside addNewProduct{}", requestMap);
        try {
            if (jwtFilter.isAdmin()) {
            	//requestMap=ProductName
                if (validateProductMap(requestMap, false)) {
                    productDao.save(getProductFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(CafeConstants.SOMETHING_WENT_WRONG);
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
	
	
	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Product product = new Product();
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));
        //whenever we will add a product this is false and the setStatus becomes true
        //this line is for update
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        //product.setStatus(String.valueOf(isAdd));

        return product;
    }
    
    
    @Override
	 public List<Product> getAllProducts(){
		 return productDao.findAll();
		 
	 }


    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
            	//means it contain the id and the name of the product or not
                if (validateProductMap(requestMap, true)) {
                	//to check if that id actually exist or not
                    Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
                    //if product id does not exist then it will return "Product id does not exist" but if it does the it will get product from map and the product is updated
                    if (!optional.isEmpty()) {
                        Product product= getProductFromMap(requestMap, true);
                        product.setStatus(optional.get().getStatus());
                        productDao.save(product);
                        return CafeUtils.getResponseEntity("Product is updated successfully", HttpStatus.OK);

                    } else {
                        return CafeUtils.getResponseEntity("Product id doesn't exist", HttpStatus.OK);
                    }

                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = productDao.findById(id);
                if (!optional.isEmpty()) {
                    productDao.deleteById(id);
                    //System.out.println("Product is deleted successfully");
                    return CafeUtils.getResponseEntity("Product is deleted successfully", HttpStatus.OK);
                }
                //System.out.println("Product id doesn't exist");
                return CafeUtils.getResponseEntity("Product id doesn't exist", HttpStatus.OK);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(CafeConstants.SOMETHING_WENT_WRONG);
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
	

}
