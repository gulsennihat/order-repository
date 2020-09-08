package com.java.migros;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.java.migros.controller.OrderController;
import com.java.migros.dto.LineItemDto;
import com.java.migros.dto.OrderDto;
import com.java.migros.entity.OrderStatus;
import com.java.migros.request.OrderRequest;
import com.java.migros.service.OrderServiceImpl;
import com.java.migros.utils.ApiResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
@WithMockUser
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderServiceImpl orderService;

	LineItemDto li = new LineItemDto(1L, 1L, 3, 30);

	OrderDto mockOrderDto = new OrderDto(1L, 1L, "Ruzgar Sokak. Kadikoy/Istanbul", "2019-01-01T10:15:30+01:00",
			new HashSet<LineItemDto>(Arrays.asList(li)), Double.valueOf(70).doubleValue(), OrderStatus.INITIAL);

	String orderRequestJson = "{ \"customerId\":\"1\", \"basketId\":\"1\", \"deliveryAddress\":\"Ruzgar Sokak. Kadikoy/Istanbul\", \"deliveryDate\":\"2022-01-01T10:15:30+01:00\" }";

	@Test
	public void createOrder() throws Exception {

		Mockito.when(orderService.createOrder(Mockito.any(OrderRequest.class)))
				.thenReturn(new ApiResponse<OrderDto>(HttpStatus.OK, mockOrderDto));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order/create-order")
				.accept(MediaType.APPLICATION_JSON).content(orderRequestJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"status\":\"OK\",\"data\":{\"id\":1,\"customerId\":1,\"deliveryAddress\":\"Ruzgar Sokak. Kadikoy/Istanbul\",\"deliveryDate\":\"2019-01-01T10:15:30+01:00\",\"lineItems\":[{\"id\":1,\"productId\":1,\"amount\":3,\"price\":30.0}],\"orderTotal\":70.0,\"orderStatus\":\"INITIAL\",\"errMsg\":null},\"errMsg\":null}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
