package br.com.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@Caching
@EnableSpringDataWebSupport
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
