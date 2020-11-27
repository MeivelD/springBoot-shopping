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
@RequestMapping("/api/")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("orders")
	public ResponseEntity<List<Order>> getAllOrder() {
		try {
			List<Order> orders = orderService.findAll();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable int orderId) throws Exception {
		try {
			Order order = orderService.findById(orderId);
			if (order == null) {
				throw new RuntimeException("Order id not found - " + orderId);
			}
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			return new ResponseEntity<>().status(HttpStatus.FORBIDDEN)
//            .body("Error Message");
		}
	}

	@PutMapping()
	public Order updateOrder(@RequestBody Order order) throws Exception {
		orderService.save(order);
		return order;
	}

	@DeleteMapping("{orderId}")
	public String deleteOrder(@PathVariable int orderId) throws Exception {

		Order order = orderService.findById(orderId);

		if (order == null) {
			throw new RuntimeException("UserInfo id not found - " + orderId);
		}

		orderService.deleteById(orderId);

		return "Deleted UserInfo id - " + orderId;
	}

}
