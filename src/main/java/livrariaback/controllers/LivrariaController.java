package livrariaback.controllers;

import adapter.LivrariaAdapter;
import adapter.dto.LivroResponse;
import livrariaback.mappers.CriarLivroRequestMapper;
import livrariaback.mappers.EditarLivroRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/livraria")
public class LivrariaController {

    private final LivrariaAdapter livrariaAdapter;

    @Autowired
    public LivrariaController(LivrariaAdapter livrariaAdapter) {
        this.livrariaAdapter = livrariaAdapter;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public LivroResponse criar(@RequestBody CriarLivroRequestMapper request) {
        return livrariaAdapter.criarLivro(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void editar(@RequestBody EditarLivroRequestMapper request) {
        livrariaAdapter.editarLivro(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void excluir(@PathVariable("id") String id) {
        livrariaAdapter.excluirLivro(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{filter}")
    public List<LivroResponse> listar(@PathVariable("filter") String filter) {
        return livrariaAdapter.buscarLivro(filter);
    }
}
