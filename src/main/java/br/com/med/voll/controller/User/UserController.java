package br.com.med.voll.controller.User;

import br.com.med.voll.dto.user.LoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mateus Dantas
 */
@RestController
@RequestMapping("/login")
public class UserController {
    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
