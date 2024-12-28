package com.cts.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.POJO.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
