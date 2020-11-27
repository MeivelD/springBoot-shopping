package com.online.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Order;
import com.online.shopping.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findById(int orderId) {
		Optional<Order> result = orderRepository.findById(orderId);
		if (result != null && result.isPresent())
			return result.get();

		return null;
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);

	}

	@Override
	public void deleteById(int orderId) {
		orderRepository.deleteById(orderId);

	}

}
