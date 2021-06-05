package livrariaback.controllers;

import adapter.LivrariaAdapter;
import adapter.dto.CriarLivroRequest;
import adapter.dto.EditarLivroRequest;
import adapter.dto.LivroResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import livrariaback.utils.Utils;
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
    private LivrariaAdapter livrariaAdapter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void init() {
        LivroResponse livroResponse = Utils.livroResponse("id", "titulo", "autor");
        Mockito.when(livrariaAdapter.criarLivro(Mockito.any())).thenReturn(livroResponse);
        Mockito.when(livrariaAdapter.buscarLivro(Mockito.any())).thenReturn(Collections.singletonList(livroResponse));
    }

    @Test
    public void deveCriar() throws Exception {
        CriarLivroRequest criarLivroRequest = Utils.criarLivroRequest("Titulo", "Autor");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/livraria")
                .content(mapper.writeValueAsString(criarLivroRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.autor").exists());

        verify(livrariaAdapter, times(1)).criarLivro(any());
    }

    @Test
    public void deveEditar() throws Exception {
        EditarLivroRequest editarLivroRequest = Utils.editarLivroRequest("id", "Titulo", "Autor");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/livraria")
                .content(mapper.writeValueAsString(editarLivroRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());

        verify(livrariaAdapter, times(1)).editarLivro(any());
    }

    @Test
    public void deveExcluir() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/livraria/id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());

        verify(livrariaAdapter, times(1)).excluirLivro("id");
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

        verify(livrariaAdapter, times(1)).buscarLivro("filtro");
    }
}