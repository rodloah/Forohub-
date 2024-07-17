package med.forohub.api.domain.topic;

import java.time.LocalDateTime;

public record DetallesTopico(
        String title,
        String message,
        LocalDateTime creationDate,
        TopicoEstado status,
        Long author
) {
    public DetallesTopico(Topico topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreation(), topic.getEstado(), topic.getAuthor().getId());
    }
}
