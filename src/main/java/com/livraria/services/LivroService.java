package com.livraria.services;

import com.livraria.repositorys.LivroRepository;
import entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro criar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void editar(Livro livro) {
        livroRepository.save(livro);
    }

    public void excluir(String id) {
        livroRepository.deleteById(id);
    }

    public List<Livro> buscar(String filtro) {
        return livroRepository.findByTituloOrAutor(filtro);
    }
}
