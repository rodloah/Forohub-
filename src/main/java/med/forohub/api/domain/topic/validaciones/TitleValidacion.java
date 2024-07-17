package med.forohub.api.domain.topic.validaciones;

import jakarta.validation.ValidationException;
import med.forohub.api.domain.topic.ActualizarTopico;
import med.forohub.api.domain.topic.DatosTopico;
import med.forohub.api.domain.topic.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleValidacion implements ValidacionTopico{
    @Autowired
    TopicoRepository topicRepository;

    @Override
    public void validate(DatosTopico data) {
        var title = topicRepository.existsByTitle(data.title());
        if (title) {
            throw new ValidationException("There is already a topic with that title.");
        }
    }

    @Override
    public void validate(ActualizarTopico data) {
        var title = topicRepository.existsByTitle(data.title());
        if (title) {
            throw new ValidationException("There is already a topic with that title.");
        }
    }
}
