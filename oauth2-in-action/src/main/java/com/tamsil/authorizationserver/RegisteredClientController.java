package com.tamsil.authorizationserver;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/registeredclient")
@RestController
public class RegisteredClientController {

    private final JwtDecoder jwtDecoder;

    public RegisteredClientController(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/jwt/validate")
    public Jwt validate(@RequestParam String jwtToken) {
        Jwt jwt = jwtDecoder.decode(jwtToken);
        return jwt;
    }
}
