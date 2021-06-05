package livrariaback.repositorys;

import entity.Livro;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<Livro, String> {
    @Query("{'$or' : [{'titulo' : {'$regex' : ?0}} , {'autor' : {'$regex' : ?0}}]}, {'$sort' : {titulo : 1}}")
    List<Livro> findByTituloOrAutor(String filter);
}
