package livrariaback.utils;

import adapter.dto.CriarLivroRequest;
import adapter.dto.EditarLivroRequest;
import adapter.dto.LivroResponse;
import entity.Livro;

public class Utils {

    public static Livro livro(String id, String titulo, String autor) {
        return new Livro(id, titulo, autor);
    }

    public static LivroResponse livroResponse(String id, String titulo, String autor) {
        return new LivroResponse(id, titulo, autor);
    }

    public static CriarLivroRequest criarLivroRequest(String titulo, String autor) {
        return new CriarLivroRequest(titulo, autor);
    }

    public static EditarLivroRequest editarLivroRequest(String id, String titulo, String autor) {
        return new EditarLivroRequest(id, titulo, autor);
    }
}
