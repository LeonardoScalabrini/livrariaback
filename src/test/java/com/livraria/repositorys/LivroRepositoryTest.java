package com.livraria.repositorys;

import entity.Livro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    private Livro livro;

    @Before
    public void init() {
        livro = new Livro();
        livro.setTitulo("Titulo");
        livro.setAutor("Autor");
        livroRepository.save(livro);
    }

    @After
    public void after() {
        livroRepository.deleteAll();
    }

    @Test
    public void deveBuscarLivroPorTitulo() {
        Livro livroDB = livroRepository.findByTituloOrAutor("Titulo").get(0);
        assertEquals(livroDB, livro);
    }

    @Test
    public void deveBuscarLivroPorAutor() {
        Livro livroDB = livroRepository.findByTituloOrAutor("Autor").get(0);
        assertEquals(livroDB, livro);
    }

    @Test
    public void deveBuscarLivroPorTituloContido() {
        Livro livroDB = livroRepository.findByTituloOrAutor("Titu").get(0);
        assertEquals(livroDB, livro);
    }

    @Test
    public void deveBuscarLivroPorAutorContido() {
        Livro livroDB = livroRepository.findByTituloOrAutor("Aut").get(0);
        assertEquals(livroDB, livro);
    }

    @Test
    public void deveBuscarOrdernadoPorTitulo() {
        Livro livroAAA = new Livro();
        livroAAA.setTitulo("TituloAAA");
        livroAAA.setAutor("Autor");
        livroRepository.save(livroAAA);

        Livro livroBBB = new Livro();
        livroBBB.setTitulo("TituloBBB");
        livroBBB.setAutor("Autor");
        livroRepository.save(livroBBB);

        List<Livro> livrosDB = livroRepository.findByTituloOrAutor("Titulo");
        assertEquals("Titulo", livrosDB.get(0).getTitulo());
        assertEquals("TituloAAA", livrosDB.get(1).getTitulo());
        assertEquals("TituloBBB", livrosDB.get(2).getTitulo());
    }
}