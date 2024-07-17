package med.forohub.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.forohub.api.domain.usuario.DatosUsuario;
import med.forohub.api.domain.usuario.Usuario;
import med.forohub.api.infra.security.DatosJWTToken;
import med.forohub.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class UsuarioControler {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> getLogin(@RequestBody @Valid DatosUsuario datosUsusario) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosUsusario.email(), datosUsusario.password());
        var userAuth = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.generarToken((Usuario) userAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(token));
    }
}
