package com.livraria.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livraria.models.Livro;
import com.livraria.services.LivroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LivrariaControllerTest {

    @MockBean
    private LivroService livroService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void init() {
        Livro livroDB = new Livro();
        livroDB.setId("id");
        livroDB.setTitulo("titulo");
        livroDB.setAutor("autor");
        Mockito.when(livroService.criar(Mockito.any())).thenReturn(livroDB);
        Mockito.when(livroService.buscar(Mockito.any())).thenReturn(Collections.singletonList(livroDB));
    }

    @Test
    public void deveCriar() throws Exception {

        Livro livro = new Livro();
        livro.setTitulo("Titulo");
        livro.setAutor("Autor");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/livraria")
                .content(mapper.writeValueAsString(livro))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.autor").exists());

        verify(livroService, times(1)).criar(any());
    }

    @Test
    public void deveEditar() throws Exception {

        Livro livro = new Livro();
        livro.setId("id");
        livro.setTitulo("Titulo");
        livro.setAutor("Autor");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/livraria")
                .content(mapper.writeValueAsString(livro))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());

        verify(livroService, times(1)).editar(any());
    }

    @Test
    public void deveExcluir() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/livraria/id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());

        verify(livroService, times(1)).excluir("id");
    }

    @Test
    public void deveListar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/livraria/filtro")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].titulo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].autor").exists());

        verify(livroService, times(1)).buscar("filtro");
    }
}