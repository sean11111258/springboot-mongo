package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserTest {
	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private UserRepo repo;

	private HttpHeaders httpHeaders;

	@BeforeEach
	public void init() {
		httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json"); 
	}

	@AfterEach
	public void clear() {
		repo.deleteAll();
	}
	
	//測試是否可add增加user
	@Test
	void testAddUser() throws Exception {
		JSONObject request = new JSONObject().put("id", 1000).put("name", "test_object");
		/*
		 * httpHeaders = new HttpHeaders(); httpHeaders.add("Content-Type",
		 * "application/json");
		 */
		MvcResult result = mockmvc.perform(post("/users").headers(httpHeaders).content(request.toString()))
				.andDo(print()).andReturn();

		MockHttpServletResponse response = result.getResponse();
		JSONObject resBody = new JSONObject(response.getContentAsString());
		int productId = resBody.getInt("id");

		assertNotNull(productId);
		assertEquals(request.getString("id"), resBody.getString("id"));
		assertEquals(request.getString("name"), resBody.getString("name"));

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("application/json", response.getContentType());
		assertEquals(result.getRequest().getRequestURL() + "/" + productId, response.getHeader("Location"));

		assertEquals(1, repo.findAll().size());
		assertTrue(repo.findById(productId).isPresent());
	}
	
	//測試put取代
	@Test
	public void testReplaceUser() throws Exception {
		User user = repo.insert(new User(5000,"test_object_1"));

		JSONObject request = new JSONObject();
		request.put("id", 5555);
		request.put("name", "test_object_2");
		

		mockmvc.perform(put("/users")
				.headers(httpHeaders)
				.content(request.toString()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(request.getString("id")))
				.andExpect(jsonPath("$.name").value(request.getString("name")));
	}

	//測試delete刪除
	@Test
	public void testDeleteUser() throws Exception {
		User user = repo.insert(new User(5000,"test_object_1"));

		mockmvc.perform(delete("/users/" + user.getId())
				.headers(httpHeaders))
				.andExpect(status().isNoContent());

		assertTrue(repo.findById(user.getId()).isEmpty());
	}

}
