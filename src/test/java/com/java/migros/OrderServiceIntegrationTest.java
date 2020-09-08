package com.java.migros;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.java.migros.dto.OrderDto;
import com.java.migros.entity.Basket;
import com.java.migros.entity.Customer;
import com.java.migros.entity.LineItem;
import com.java.migros.request.OrderRequest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderServiceIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void  when_create_valid_order() {
	    // given
		Customer customer = new Customer();
		customer.setName("nihat");
		customer.setSurname("GULSEN");
		Customer savedCustomer = entityManager.persistAndFlush(customer);
		
		LineItem li1 = new LineItem(1L, 3, 30);
		LineItem li2 = new LineItem(2L, 4, 40);
		
		Basket basket = new Basket();
		basket.setCustomerId(savedCustomer.getId());
		basket.getLineItems().add(li1);
		basket.getLineItems().add(li2);
		
		Basket savedBasket = entityManager.persistAndFlush(basket);
		
		OrderRequest request = new OrderRequest();
		request.setBasketId(savedBasket.getId());
		request.setCustomerId(savedBasket.getCustomerId());
		request.setDeliveryAddress("Rüzgar Sokak. Kadıköy/Istanbul");
		request.setDeliveryDate("2021-01-01T10:15:30+01:00");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(request, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/order/create-order",
				HttpMethod.POST, entity, String.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

	}
	
	@Test
	public void  when_create_invalid_order() {
	    // given
		Customer customer = new Customer();
		customer.setName("nihat");
		customer.setSurname("GULSEN");
		Customer savedCustomer = entityManager.persistAndFlush(customer);
		
		LineItem li1 = new LineItem(1L, 3, 30);
		LineItem li2 = new LineItem(2L, 4, 40);
		
		Basket basket = new Basket();
		basket.setCustomerId(savedCustomer.getId());
		basket.getLineItems().add(li1);
		basket.getLineItems().add(li2);
		
		Basket savedBasket = entityManager.persistAndFlush(basket);
		
		OrderRequest request = new OrderRequest();
		request.setBasketId(savedBasket.getId());
		request.setCustomerId(savedBasket.getCustomerId());
		request.setDeliveryAddress("Rüzgar Sokak. Kadıköy/Istanbul");
		request.setDeliveryDate("2019-01-01T10:15:30+01:00");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(request, headers);
		ResponseEntity<OrderDto> response = restTemplate.exchange("http://localhost:8080/order/create-order",
				HttpMethod.POST, entity, OrderDto.class);
		OrderDto body = response.getBody() != null ? response.getBody() : null;
			MatcherAssert.assertThat(body.getId(), Matchers.nullValue());
		

	}
	
}
