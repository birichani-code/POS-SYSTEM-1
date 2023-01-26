package com.devIT.repository;

import com.devIT.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
