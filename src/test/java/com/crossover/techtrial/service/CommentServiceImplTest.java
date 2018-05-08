package com.crossover.techtrial.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.CommentRepository;

@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

	@TestConfiguration
	static class CommentServiceImplTestContextConfiguration {
		@Bean
		public CommentService commentService() {
			return new CommentServiceImpl();
		}
	}

	@Autowired
	private CommentService commentService;

	@MockBean
	private CommentRepository commentRepository;

	@Before
	public void setUp() {

		
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");

		Comment comment = new Comment();
		comment.setId(1L);
		comment.setArticle(article);
		comment.setEmail("test@publisher.com");
		
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);
		Mockito.when(commentRepository.findAll()).thenReturn(comments);

		/*java.util.Optional<Article> articleOptional = java.util.Optional.of(article);
		Mockito.when(commentRepository.findById(article.getId())).thenReturn(articleOptional);

		Mockito.when(articleRepository.findById(article.getId())).thenReturn(articleOptional);*/
	}

	@Test
	public void testFindAll() {
		List<Comment> comments = commentService.findAll(1L);
		Assert.assertEquals(1, comments.size());
	}

	@Test
	public void testSave() {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");

		Comment comment = new Comment();
		comment.setId(1L);
		comment.setArticle(article);
		comment.setEmail("test@publisher.com");
		
		Mockito.when(commentRepository.save(comment)).thenReturn(comment);
		// Act
		Comment savedComment = commentService.save(comment);
		// Assert
		Assert.assertEquals(savedComment, comment);
	}
}
