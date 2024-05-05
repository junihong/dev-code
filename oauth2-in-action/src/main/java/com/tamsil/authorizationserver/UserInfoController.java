package com.tamsil.authorizationserver;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class UserInfoController {

    private final JwtDecoder jwtDecoder;

    public UserInfoController(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/getToken")
    public ResponseEntity<?> getToken(@RequestParam String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return ResponseEntity.ok(new JwtAuthenticationToken(jwt));
    }
}
