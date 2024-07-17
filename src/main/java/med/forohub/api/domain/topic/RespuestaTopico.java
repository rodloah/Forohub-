package med.forohub.api.domain.topic;

import java.time.LocalDateTime;

public record RespuestaTopico (Long id,
                               String title,
                               String message,
                               LocalDateTime creationDate,
                               String status,
                               Long author,
                               String course
) {
    public RespuestaTopico(Topico topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreation(), topic.getEstado().toString(), topic.getAuthor().getId(), topic.getCourse());
    }
}
