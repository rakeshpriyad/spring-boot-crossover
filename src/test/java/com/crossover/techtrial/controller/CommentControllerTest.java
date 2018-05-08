package com.crossover.techtrial.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.ArticleService;
import com.crossover.techtrial.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CommentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ArticleService service;

	@MockBean
	CommentService commentService;

	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testCreateComment() throws Exception {
		String articleJson = "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }";
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		
		Comment comment = new Comment();
		comment.setArticle(article);
		comment.setEmail("test@publisher.com");
		Mockito.when(commentService.save(comment)).thenReturn(comment);
		Mockito.when(service.findById(article.getId())).thenReturn(article);
		mvc.perform(post("/articles/1/comments").accept(MediaType.APPLICATION_JSON).content(articleJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));

	}

	@Test
	public void testGetComments() throws Exception {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		
		Comment comment = new Comment();
		comment.setArticle(article);
		comment.setEmail("test@publisher.com");
		List<Comment> comments = new ArrayList<Comment>();
		Mockito.when(commentService.findAll(1L)).thenReturn(comments);
		mvc.perform(get("/articles/1/comments").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
}
