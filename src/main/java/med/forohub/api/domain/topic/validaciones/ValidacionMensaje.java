package med.forohub.api.domain.topic.validaciones;

import jakarta.validation.ValidationException;
import med.forohub.api.domain.topic.ActualizarTopico;
import med.forohub.api.domain.topic.DatosTopico;
import med.forohub.api.domain.topic.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionMensaje implements ValidacionTopico{
    @Autowired
    TopicoRepository topicRepository;
    @Override
    public void validate(DatosTopico data) {
        var message = topicRepository.existsByMessage(data.message());
        if (message) {
            throw new ValidationException("There is already a topic with that message.");
        }
    }

    @Override
    public void validate(ActualizarTopico data) {
        var message = topicRepository.existsByMessage(data.message());
        if (message) {
            throw new ValidationException("There is already a topic with that message.");
        }
    }
}
