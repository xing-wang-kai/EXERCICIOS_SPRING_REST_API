package br.com.forum.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.forum.model.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRepositoryTest {
	
	@Autowired
	public CursoRepository cursoRepository;

	@Test
	public void DeveriaRetornarUmCursoPeloNome() {
		
		String nome = "HTML 5";
		Curso curso = cursoRepository.findByNome(nome);
		System.out.println(curso);
		assertNotNull(curso);
		assertEquals(nome, curso.getNome());
	}
	
	@Test
	public void NaoDeveriaRetornarUmCursoPeloNome() {
		
		String nome = "JPA";
		Curso curso = cursoRepository.findByNome(nome);
		assertNull(curso);
	}
	
	@Test
	public void retornaTur() {
		assertTrue(true);
	}

}
