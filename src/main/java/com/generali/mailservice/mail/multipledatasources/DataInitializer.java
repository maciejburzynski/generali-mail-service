package com.generali.mailservice.mail.multipledatasources;

import com.generali.mailservice.mail.multipledatasources.order.Order;
import com.generali.mailservice.mail.multipledatasources.order.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

  private final OrderRepository orderRepository;
  @PostConstruct
  void initData(){
    Order order = new Order("My Order");
    orderRepository.save(order);
  }


}
