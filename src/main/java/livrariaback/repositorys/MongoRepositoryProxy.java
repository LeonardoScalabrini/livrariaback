package livrariaback.repositorys;

import entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usecase.port.LivroRepository;

import java.util.List;

@Component
public class MongoRepositoryProxy implements LivroRepository {

    private final MongoRepository mongoRepository;

    @Autowired
    public MongoRepositoryProxy(MongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Livro save(Livro livro) {
        return mongoRepository.save(livro);
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public List<Livro> findByTituloOrAutor(String filter) {
        return mongoRepository.findByTituloOrAutor(filter);
    }
}
