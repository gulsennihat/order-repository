package com.java.migros.service;

import java.time.ZonedDateTime;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.migros.constant.OrderServiceConstant;
import com.java.migros.dto.OrderDto;
import com.java.migros.entity.Basket;
import com.java.migros.entity.LineItem;
import com.java.migros.entity.Order;
import com.java.migros.entity.OrderStatus;
import com.java.migros.exceptions.CreateOrderException;
import com.java.migros.repository.CaseProjectRepository;
import com.java.migros.repository.OrderRepository;
import com.java.migros.request.OrderRequest;
import com.java.migros.utils.ApiResponse;

@Service
public class OrderServiceImpl implements OrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CaseProjectRepository caseProjectRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public ApiResponse<OrderDto> createOrder(OrderRequest request) {
		
		Order savedOrder = null;
		ZonedDateTime zdtDeliverDate = ZonedDateTime.parse(request.getDeliveryDate());
			try {
				if(!zdtDeliverDate.isAfter(ZonedDateTime.now())) {
					throw new CreateOrderException(OrderServiceConstant.ORDER_DATE_ERROR_MSG);
				}
				
				//create new order
				Order order = new Order();
				order.setCustomerId(request.getCustomerId());
				order.setDeliveryAddress(request.getDeliveryAddress());
				order.setDeliveryDate(zdtDeliverDate);
				order.setOrderTotal(getBasketTotalByCustomerId(request.getCustomerId()));
				order.setOrderStatus(OrderStatus.INITIAL);
				
				final Basket customerBasket = getCustomerBasket(request.getBasketId());
				
				order.getLineItems().addAll(customerBasket.getLineItems());
				
				savedOrder = orderRepository.save(order);
				logger.info("created order : " + savedOrder );
			} catch (CreateOrderException e) {
				logger.error("order delivery date is past. Date: " + zdtDeliverDate.toString()); 
				return new ApiResponse<OrderDto>(HttpStatus.OK, OrderServiceConstant.ORDER_DATE_ERROR_MSG);
			}
		
		OrderDto orderDto = new OrderDto();
		modelMapper.map(savedOrder, orderDto);
		logger.info("orderDto : " + orderDto ); 
		return new ApiResponse<OrderDto>(HttpStatus.OK, orderDto);
		
	}


	private Basket getCustomerBasket(Long id) {
		return caseProjectRepository.getCustomerBasket(id);
	}

	private Double getBasketTotalByCustomerId(Long customerId) {
		return getCustomerBasket(customerId).getLineItems()
  				.stream()
  				.map(LineItem::getPrice)
  				.mapToDouble(Double::valueOf)
  				.sum();
	}
	
	
}
