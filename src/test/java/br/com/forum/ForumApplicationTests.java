package br.com.forum;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.jsonwebtoken.lang.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class ForumApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
