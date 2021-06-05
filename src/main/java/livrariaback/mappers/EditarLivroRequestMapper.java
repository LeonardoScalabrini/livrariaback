package livrariaback.mappers;

import adapter.dto.EditarLivroRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditarLivroRequestMapper extends EditarLivroRequest {

    @JsonCreator
    public EditarLivroRequestMapper(@JsonProperty("id") String id, @JsonProperty("autor") String autor, @JsonProperty("titulo") String titulo) {
        super(id, autor, titulo);
    }
}
