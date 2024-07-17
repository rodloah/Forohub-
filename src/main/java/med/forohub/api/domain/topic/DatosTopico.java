package med.forohub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record DatosTopico(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String course
) {
}
