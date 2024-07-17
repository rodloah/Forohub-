package med.forohub.api.domain.topic.validaciones;

import med.forohub.api.domain.topic.ActualizarTopico;
import med.forohub.api.domain.topic.DatosTopico;

public interface ValidacionTopico {
    void validate(DatosTopico data);

    void validate(ActualizarTopico data);
}
