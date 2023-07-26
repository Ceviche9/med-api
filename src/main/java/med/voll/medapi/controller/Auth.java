package med.voll.medapi.controller;

import jakarta.validation.Valid;
import med.voll.medapi.domains.user.AuthenticateRequest;
import med.voll.medapi.domains.user.User;
import med.voll.medapi.infra.security.TokenService;
import med.voll.medapi.infra.security.jwtTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Auth {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid AuthenticateRequest data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new jwtTokenDTO(jwtToken));
    }
}
