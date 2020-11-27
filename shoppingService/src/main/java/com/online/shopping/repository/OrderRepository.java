package com.online.shopping.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.online.shopping.entity.Order;

@Repository
public interface OrderRepository
		extends JpaRepository<Order, Integer>, QueryByExampleExecutor<Order>, JpaSpecificationExecutor<Order> {

	List<Order> findByInvoiceNumber(int invoiceNumber);

	List<Order> findByCompanyName(String companyName);

	Page<Order> findAll(Pageable page);
}
