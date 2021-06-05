package livrariaback.mappers;

import adapter.dto.CriarLivroRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CriarLivroRequestMapper extends CriarLivroRequest {

    @JsonCreator
    public CriarLivroRequestMapper(@JsonProperty("autor") String autor, @JsonProperty("titulo") String titulo) {
        super(autor, titulo);
    }
}
