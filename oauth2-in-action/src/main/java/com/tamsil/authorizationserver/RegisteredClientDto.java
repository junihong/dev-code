package com.tamsil.authorizationserver;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Instant;
import java.util.Set;

public record RegisteredClientDto(
        String id,
        String clientId,
        Instant clientIdIssuedAt,
        String clientSecret,
        Instant clientSecretExpiresAt,
        String clientName,
        Set<ClientAuthenticationMethod> clientAuthenticationMethods,
        Set<AuthorizationGrantType> authorizationGrantTypes,
        Set<String> redirectUris,
        Set<String> postLogoutRedirectUris,
        Set<String> scopes,
        ClientSettings clientSettings,
        TokenSettings tokenSettings
) {
}
