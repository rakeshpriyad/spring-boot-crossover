package com.crossover.techtrial.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ArticleControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArticleService service;

	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testGetArticleById() throws Exception {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.when(service.findById(1L)).thenReturn(article);
		mvc.perform(get("/articles/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("How to boil an egg")));
	}
	
	@Test
	public void testExceptionHandler() throws Exception {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.when(service.findById(1L)).thenReturn(article);
		mvc.perform(get("badurl").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(404));
				
	}

	@Test
	public void testGetArticleById1() throws Exception {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.when(service.findById(1L)).thenReturn(null);
		mvc.perform(get("/articles/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testArticleShouldBeCreated() throws Exception {
		String articleJson = "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }";
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.when(service.save(article)).thenReturn(article);
		mvc.perform(post("/articles").accept(MediaType.APPLICATION_JSON).content(articleJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));

	}

	@Test
	public void testUpdateArticle() throws Exception {
		String articleJson = "{\"id\" : \"1\", \"email\": \"user1@gmail.com\", \"title\": \"hello\" }";
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.when(service.save(article)).thenReturn(article);

		mvc.perform(put("/articles/1").accept(MediaType.APPLICATION_JSON).content(articleJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteArticleById() throws Exception {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.doNothing().when(service).delete(1L);

		mvc.perform(delete("/articles/1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
