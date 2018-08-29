package com.demo.rest;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.rest.pojo.Customer;
import com.demo.rest.service.CustomerServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class CustomerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private MockMvc mockMvc;
	
/*	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(demo).build();
	}*/

	@Mock
	CustomerServiceImpl customerService;

	@Test
	public void testViewCustomer() throws Exception {
		//mockMvc.perform(get("/customers")/*.accept((MediaType.APPLICATION_JSON))*/).andExpect(status().isOk());
			/*	.andExpect(jsonPath("$.customerId").value(101))
				.andExpect(jsonPath("$.customerName").value("Drishti Rao"));*/
		List<Customer> customers = testRestTemplate.getForObject("/customers", ArrayList.class);
		System.out.println("\n in test");
		 System.out.println(customers);
	}

	@Test
	public void testViewCustomerBetween() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		/*TestRestTemplate testRest = new TestRestTemplate();*/
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Resources> customersBetween = testRestTemplate.exchange("/customers/1/2", HttpMethod.GET, entity, Resources.class);
		
		/*Resources customersBetween = testRestTemplate.getForObject("/customers/1/2", Customer.class);*/
		
		/*JSONAssert.assertEquals(expected, response.getBody(), false);*/
		assertThat(customersBetween.getStatusCode(), equalTo(HttpStatus.OK));
		
		System.out.println("\n");
		 System.out.println("*********** " + customersBetween);
		//mockMvc.perform(get("/customers/1/2")).andExpect(status().isOk());
		/* .andExpect(content().contentType(MediaType.APPLICATION_JSON)) */
		/*
		 * .andExpect(jsonPath("$.customerId").value(101))
		 * .andExpect(jsonPath("$.customerName").value("Drishti Rao"));
		 */

	}

	private void assertThat(HttpStatus statusCode, Matcher<HttpStatus> equalTo) {
		// TODO Auto-generated method stub
		
	}
}

