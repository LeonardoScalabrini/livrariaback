package com.livraria.controllers;

import com.livraria.services.LivroService;
import entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/livraria")
public class LivrariaController {

    @Autowired
    private LivroService livroService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public Livro criar(@RequestBody Livro livro) {
        return livroService.criar(livro);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void editar(@RequestBody Livro livro) {
        livroService.editar(livro);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void excluir(@PathVariable("id") String id) {
        livroService.excluir(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{filtro}")
    public List<Livro> listar(@PathVariable("filtro") String filtro) {
        return livroService.buscar(filtro);
    }
}
