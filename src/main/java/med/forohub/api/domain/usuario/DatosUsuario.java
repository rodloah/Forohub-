package med.forohub.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosUsuario(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {

}
