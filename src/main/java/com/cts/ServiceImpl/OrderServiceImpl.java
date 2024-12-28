package com.cts.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Dao.OrderDao;
import com.cts.Dao.ProductDao;
import com.cts.POJO.Order;
import com.cts.POJO.Product;
import com.cts.Service.OrderService;
import com.cts.Utils.ProductNotFoundExcepion;

@Service
public class OrderServiceImpl implements OrderService  {
	
	
		
		@Autowired
		private OrderDao orderDao;
		@Autowired
		private ProductDao productDao;
		
		public List<Order> getAllOrders(){
			return orderDao.findAll();
		}
		
		
		//addingOrder
		public Order addOrder(Integer productId) {
			
			Product productTemp = productDao.findById(productId).orElseThrow(()-> new ProductNotFoundExcepion("Product Not found"));
			
			
			Order temp = new Order();

			temp.setProductId(productTemp.getId());
			temp.setProductName(productTemp.getName());
			temp.setProductPrice(productTemp.getPrice());
			
			return orderDao.save(temp);
			
		}
		
		public void deleteOrder(Integer orderId) {
			Order orderTemp = orderDao.findById(orderId).orElseThrow(()-> new ProductNotFoundExcepion("Order Not found"));
			orderDao.delete(orderTemp);
		}

}
