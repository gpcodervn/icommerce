package com.icommerce.order.service.impl;

import com.icommerce.core.dto.UserCredential;
import com.icommerce.order.entity.Order;
import com.icommerce.order.entity.OrderDetail;
import com.icommerce.order.model.request.OrderRequest;
import com.icommerce.order.model.response.OrderResponse;
import com.icommerce.order.repository.OrderRepository;
import com.icommerce.order.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse save(OrderRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        Set<OrderDetail> orderDetails = request.getOrderDetails().stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetail.class))
                .collect(Collectors.toSet());
        Double total = orderDetails.stream()
                .filter(Objects::nonNull)
                .map(orderDetail -> orderDetail.getPrice() * orderDetail.getAmount())
                .reduce(0D, Double::sum);

        Order order = new Order();
        order.setNo(RandomStringUtils.randomAlphanumeric(8));
        order.setCustomerId(getUserId());
        order.setOrderedAt(LocalDateTime.now());
        order.setPrice(total);
        order.setOrderDetails(orderDetails);

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    private Long getUserId () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserCredential) authentication.getPrincipal()).getId();
    }
}
