package com.livraria.services;

import com.livraria.models.Livro;
import com.livraria.repositorys.LivroRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    private Livro livro;

    private Livro livroDB;

    @Before
    public void init() {
        livro = new Livro();
        livro.setTitulo("Titulo");
        livro.setAutor("Autor");

        livroDB = new Livro();
        livroDB.setId("Id");
        livroDB.setTitulo("Titulo");
        livroDB.setAutor("Autor");

        when(livroRepository.save(livro)).thenReturn(livroDB);
        when(livroRepository.findByTituloOrAutor("filtro")).thenReturn(Collections.singletonList(livroDB));
    }

    @Test
    public void deveCriar() {
        assertEquals(livroDB, livroService.criar(livro));
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    public void deveEditar() {
        livroService.editar(livro);
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    public void deveExcluir() {
        livroService.excluir("id");
        verify(livroRepository, times(1)).deleteById("id");
    }

    @Test
    public void deveBuscar() {
        assertEquals(Collections.singletonList(livroDB), livroService.buscar("filtro"));
        verify(livroRepository, times(1)).findByTituloOrAutor("filtro");
    }
}