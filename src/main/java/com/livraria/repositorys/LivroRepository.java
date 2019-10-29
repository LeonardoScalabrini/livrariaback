package com.livraria.repositorys;

import com.livraria.models.Livro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {
    @Query("{'$or' : [{'titulo' : {'$regex' : ?0}} , {'autor' : {'$regex' : ?0}}]}, {'$sort' : {titulo : 1}}")
    List<Livro> findByTituloOrAutor(String filtro);
}
