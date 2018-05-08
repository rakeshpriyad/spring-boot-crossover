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
import com.crossover.techtrial.model.Comment;


@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class CommentRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	public void testFindAll() {
		List<Comment> comments = commentRepository.findAll();
		Assert.assertEquals(0, comments.size());
	}
	@Test
	public void testFindById() {
		commentRepository.findById(1L);
	}

	public TestEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(TestEntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
