package com.cts.Rest;

import com.cts.POJO.Order;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
public interface OrderRest {

    @GetMapping("/get")
    ResponseEntity<List<Order>> getAllOrder();

    @PostMapping("/add/{productId}")
    ResponseEntity<Order> addOrder(@PathVariable("productId") Integer id);

    @DeleteMapping("/delete/{orderId}")
    ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer id);
}
