package com.cts.RestImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.POJO.Order;
import com.cts.Rest.OrderRest;
import com.cts.Service.OrderService;
import com.cts.ServiceImpl.OrderServiceImpl;

@RestController
public class OrderRestImpl implements OrderRest {
	@Autowired
	private OrderService orderService;
	
	
	public  ResponseEntity<List<Order>> getAllOrder() {
		return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
	
	public ResponseEntity<Order> addOrder(@PathVariable("productId") Integer id ){
		return new ResponseEntity<>(orderService.addOrder(id),HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer id){
		orderService.deleteOrder(id);
		//return  ResponseEntity.ok("Order: "+ id +" has been deleted");
		return new ResponseEntity<>("Order: "+ id +" has been deleted",HttpStatus.OK);
	}
}
