package med.forohub.api.domain.topic.validaciones;

import med.forohub.api.domain.topic.ActualizarTopico;
import med.forohub.api.domain.topic.DatosActualizarTopico;
import med.forohub.api.domain.usuario.Usuario;

public interface UpdateValidation {
    void validate(DatosActualizarTopico data, Usuario user);

    void validate(ActualizarTopico data, Usuario user);
}
