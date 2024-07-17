package med.forohub.api.domain.topic.validaciones;

import jakarta.validation.ValidationException;
import med.forohub.api.domain.topic.ActualizarTopico;
import med.forohub.api.domain.topic.DatosActualizarTopico;
import med.forohub.api.domain.topic.TopicoRepository;
import med.forohub.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidacionAutor implements UpdateValidation{
    @Autowired
    private TopicoRepository topicRepository;

    @Override
    public void validate(DatosActualizarTopico data, Usuario user) {
        var topic = topicRepository.findById(data.id()).orElse(null);

        assert topic != null;
        if (!Objects.equals(topic.getAuthor().getId(), user.getId())) {
            throw new ValidationException("Este topico no le pertenece a este usuario");
        }
    }

    @Override
    public void validate(ActualizarTopico data, Usuario user) {
        var topic = topicRepository.findById(user.getId()).orElse(null);
        assert topic != null;
        if (!Objects.equals(topic.getAuthor().getId(), user.getId())) {
            throw new ValidationException("Este topico no le pertenece a este usuario");
        }
    }
}
