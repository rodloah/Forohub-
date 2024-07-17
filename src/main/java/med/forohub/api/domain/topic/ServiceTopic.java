package med.forohub.api.domain.topic;

import jakarta.servlet.http.HttpServletRequest;
import med.forohub.api.domain.topic.validaciones.UpdateValidation;
import med.forohub.api.domain.topic.validaciones.ValidacionTopico;
import med.forohub.api.domain.usuario.Usuario;
import med.forohub.api.domain.usuario.UsuarioRepository;
import med.forohub.api.infra.errores.ValidacionDeIntegridad;
import med.forohub.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceTopic {
    @Autowired
    UsuarioRepository userRepository;
    @Autowired
    TopicoRepository topicRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private List<ValidacionTopico> validations;
    @Autowired
    private List<UpdateValidation> updateTopico;
    public Topico addTopico(DatosTopico topic, HttpServletRequest request) {
        var user = getAuthenticatedUsuario(request);
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new ValidacionDeIntegridad("Este id para este usuario no existe");
        }
        validations.forEach(v -> v.validate(topic));
        return topicRepository.save(new Topico(user, topic));
    }

    private Usuario getAuthenticatedUsuario(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        String subject = tokenService.getSubject(token);
        return (Usuario) userRepository.findByEmail(subject);
    }

    public DetallesTopico update(Long id, ActualizarTopico data, HttpServletRequest request) {
        if (data.title() == null && data.message() == null) {
            throw new ValidacionDeIntegridad("No hay nada para editar");
        }
        if (!topicRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("No hay topicos con ese id");
        }

        var user = getAuthenticatedUsuario(request);
        DatosTopico dataTopico = new DatosTopico(data.title(), data.message(), null);
        validations.forEach(v -> v.validate(dataTopico));
        updateTopico.forEach(v -> v.validate(data, user));

        var topic = topicRepository.getReferenceById(id);
        if (data.title() != null && data.message() != null) {
            topic.setMessage(data.message());
            topic.setTitle(data.title());
        } else if (data.title() == null) {
            topic.setMessage(data.message());
        } else {
            topic.setTitle(data.title());
        }
        topic.update();
        return new DetallesTopico(topic);
    }

    public void delete(Long id, HttpServletRequest request) {
        if (!topicRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("No existe el topico");
        }
        var user = getAuthenticatedUsuario(request);
        DatosActualizarTopico data = new DatosActualizarTopico(id, null, null);
        updateTopico.forEach(v -> v.validate(data, user));
        var topic = topicRepository.getReferenceById(id);
        topic.delete();
    }
}
