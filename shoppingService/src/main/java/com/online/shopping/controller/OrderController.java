package com.online.shopping.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.entity.Order;
import com.online.shopping.service.OrderService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders/")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * Fetch all the orders placed.
	 * 
	 * @return list of order placed.
	 * @throws Exception
	 */
	@GetMapping()
	public ResponseEntity<List<Order>> getAllOrder() throws Exception{
		try {
			List<Order> orders = orderService.findAll();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Fetch specific order placed.
	 * 
	 * @param orderId id of the order
	 * @return specific order
	 * @throws Exception
	 */
	@GetMapping("{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable int orderId) throws Exception {
		Order order = orderService.findById(orderId);
		if (order == null) {
			throw new RuntimeException("Order id not found - " + orderId);
		}
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	/**
	 * Update specific order placed.
	 * 
	 * @param order order details needs to be updated
	 * @return status of the update.
	 * @throws Exception
	 */
	@PutMapping()
	public ResponseEntity<String> updateOrder(@RequestBody Order order) throws Exception {
		orderService.save(order);
		return new ResponseEntity<>("Updated UserInfo id - " + order.getOrderId(), HttpStatus.OK);
	}
	
	/**
	 * Delete the order placed.
	 * 
	 * @param orderId
	 * @return status of the deleted.
	 * @throws Exception
	 */
	@DeleteMapping("{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable int orderId) throws Exception {
		Order order = orderService.findById(orderId);
		if (order == null) {
			throw new RuntimeException("UserInfo id not found - " + orderId);
		}
		orderService.deleteById(orderId);
		return new ResponseEntity<>("Deleted UserInfo id - " + orderId, HttpStatus.OK);
	}

}
