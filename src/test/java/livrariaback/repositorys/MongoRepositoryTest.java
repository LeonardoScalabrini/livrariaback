package livrariaback.repositorys;

import entity.Livro;
import livrariaback.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoRepositoryTest {

    @Autowired
    private MongoRepository mongoRepository;

    private Livro livro;

    @Before
    public void init() {
        livro = Utils.livro("id", "Titulo", "Autor");
        mongoRepository.save(livro);
    }

    @After
    public void after() {
        mongoRepository.deleteAll();
    }

    @Test
    public void deveBuscarLivroPorTitulo() {
        Livro livroDB = mongoRepository.findByTituloOrAutor("Titulo").get(0);
        assertLivro(livroDB, livro);
    }

    @Test
    public void deveBuscarLivroPorAutor() {
        Livro livroDB = mongoRepository.findByTituloOrAutor("Autor").get(0);
        assertLivro(livroDB, livro);
    }

    @Test
    public void deveBuscarLivroPorTituloContido() {
        Livro livroDB = mongoRepository.findByTituloOrAutor("Titu").get(0);
        assertLivro(livroDB, livro);
    }

    @Test
    public void deveBuscarLivroPorAutorContido() {
        Livro livroDB = mongoRepository.findByTituloOrAutor("Aut").get(0);
        assertLivro(livroDB, livro);
    }

    @Test
    public void deveBuscarOrdernadoPorTitulo() {
        Livro livroAAA = Utils.livro("idA", "TituloAAA", "Autor");
        mongoRepository.save(livroAAA);

        Livro livroBBB = Utils.livro("idB", "TituloBBB", "Autor");
        mongoRepository.save(livroBBB);

        List<Livro> livrosDB = mongoRepository.findByTituloOrAutor("Titulo");
        assertEquals("Titulo", livrosDB.get(0).getTitulo());
        assertEquals("TituloAAA", livrosDB.get(1).getTitulo());
        assertEquals("TituloBBB", livrosDB.get(2).getTitulo());
    }

    private void assertLivro(Livro expected, Livro actual) {
        assertEquals(expected.getTitulo(), actual.getTitulo());
        assertEquals(expected.getAutor(), actual.getAutor());
        assertEquals(expected.getId(), actual.getId());
    }
}