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
import com.crossover.techtrial.repository.ArticleRepository;

@RunWith(SpringRunner.class)
public class ArticleServiceImplTest {

	@TestConfiguration
	static class ArticleServiceImplTestContextConfiguration {
		@Bean
		public ArticleService articleService() {
			return new ArticleServiceImpl();
		}
	}

	@Autowired
	private ArticleService articleService;

	@MockBean
	private ArticleRepository articleRepository;

	@Before
	public void setUp() {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");

		List<Article> articles = new ArrayList<Article>();
		articles.add(article);
		Mockito.when(articleRepository.findTop10ByTitleContainingIgnoreCase(article.getTitle())).thenReturn(articles);
		
		java.util.Optional<Article> articleOptional = java.util.Optional.of(article);
		Mockito.when(articleRepository.findById(article.getId())).thenReturn(articleOptional);
		
		Mockito.when(articleRepository.findById(article.getId())).thenReturn(articleOptional);
	}

	@Test
	public void testSearch() {
		List<Article> articles = articleService.search("How to boil an egg");
		Assert.assertEquals(1,  articles.size());
	}

	@Test
	public void testFindById() {
		Article article = articleService.findById(1L);
		Assert.assertTrue("john@publisher.com".equals(article.getEmail()));
	}
	
	@Test
	public void testDelete() {
		Mockito.doNothing().when(articleRepository).deleteById(1L);
		ArticleService as =Mockito.mock(ArticleServiceImpl.class);
        // Act         
		as.delete(1L);
        // Assert         
        Mockito.verify(as, Mockito.times(1)).delete(1L);
	}
	
	@Test
	public void testSave() {
		Article article = new Article();
		article.setEmail("john@publisher.com");
		article.setId(1L);
		article.setTitle("How to boil an egg");
		Mockito.when(articleRepository.save(article)).thenReturn(article);
        // Act         
		Article savedArticle = articleService.save(article);
        // Assert         
		 Assert.assertEquals(savedArticle,article);
	}
}
