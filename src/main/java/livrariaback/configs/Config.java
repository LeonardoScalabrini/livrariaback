package livrariaback.configs;

import adapter.LivrariaAdapter;
import livrariaback.idgenerators.UUIDGenerator;
import livrariaback.repositorys.MongoRepositoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import usecase.BuscarLivros;
import usecase.CriarLivro;
import usecase.EditarLivro;
import usecase.ExcluirLivro;

@Component
public class Config {

    private final MongoRepositoryProxy mongoRepository;

    @Autowired
    public Config(MongoRepositoryProxy mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Bean
    public LivrariaAdapter livrariaAdapter() {
        CriarLivro criarLivro = new CriarLivro(mongoRepository);
        EditarLivro editarLivro = new EditarLivro(mongoRepository);
        BuscarLivros buscarLivros = new BuscarLivros(mongoRepository);
        ExcluirLivro excluirLivro = new ExcluirLivro(mongoRepository);
        return new LivrariaAdapter(criarLivro, editarLivro, buscarLivros, excluirLivro, new UUIDGenerator());
    }
}
