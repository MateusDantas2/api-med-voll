package br.com.med.voll.controller.user;

import br.com.med.voll.config.security.TokenService;
import br.com.med.voll.domain.entity.User;
import br.com.med.voll.dto.user.LoginRequestDTO;
import br.com.med.voll.dto.user.TokenDTO;
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
    private final TokenService tokenService;

    public UserController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        UsernamePasswordAuthenticationToken tokenAuthentication = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var authentication = authenticationManager.authenticate(tokenAuthentication);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
