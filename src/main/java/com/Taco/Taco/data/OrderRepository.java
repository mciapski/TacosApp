package com.Taco.Taco.data;

import com.Taco.Taco.Order;

public interface OrderRepository {
  Order save(Order order);
}
