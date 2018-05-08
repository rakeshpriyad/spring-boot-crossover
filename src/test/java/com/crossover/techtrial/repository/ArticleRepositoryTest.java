package com.crossover.techtrial.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.techtrial.model.Article;


@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class ArticleRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ArticleRepository articleRepository;
	
	@Test
	public void testFindTop10ByTitleContainingIgnoreCase() {
		String title = "How to boil an egg";
		List<Article> articles = articleRepository.findTop10ByTitleContainingIgnoreCase(title);
		Assert.assertEquals(1, articles.size());
	}
	@Test
	public void testFindById() {
		articleRepository.findById(1L);
	}
	
	@Test
	public void testDeleteById() {
		articleRepository.deleteById(1L);
	}

	public TestEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(TestEntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
