# 기본설정으로 Authorization Server 생성하기

## Authorization Server에 대한 Security Config 설정 SecurityFilterChain Bean 생성
최소한의 기본 설정으로 Authorization Server를 생성하고자 할 경우에는 SecurityConfig에 대한 설정을 해주면 된다. Spring에서 제공하는 Authorization Server는 편리하게 기존 Configuration을 설정할 수 있도록 제공한다
먼저 Authorization Server에 대한 Defsult Security Configuration을 설정해야 하는데, SecurityFilterChain 타입의 Bean을 생성한다.
OAuth2AuthorizationServerConfiguration 클래스에서 제공되는 Default Apply 설정 함수는 SpringSecurity 설정에 필요한 기본 authorizeRequest, EndpointMatcher, csrf 등의 기본 설정을 편하게 설정할 수 있도록 제공해준다

```java
@Bean
@Order(1)
public SecurityFilterChain authorizationServerFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
        .oidc(Customizer.withDefaults());
    http
        .exceptionHandling((exceptions) -> exceptions
        .defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"),
        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
        ))
        .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
    return http.build();
}
```

## Default Security Config 설정
다음으로는 SecurityConfig 기본 설정을 진행한다. 모든 Request에 대하여 인증이 필요하도록 설정하고, formLogin에 대한 기본 설정도 진행한다. @Order 애노테이션으로 Authorization Server에 대한 Security Config 설정보다 우선순위가 낮도록 설정한다. 별도의 Login 페이지를 사용하고자 할 경우에는 Custom Login 페이지도 설정 가능하다.
```java
@Bean
@Order(2)
public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> authorize
        .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults());
    return http.build();
}
```

## UserDetailsService Bean 생성
다음으로 사용자 정보 인증을 위하여 Spring Security에서 제공하는 UserDetails타입의 User 정보를 제공하는 UserDetailsService 타입의 Bean을 정의한다
```java
@Bean 
public UserDetailsService userDetailsService() {
    UserDetails userDetails = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(userDetails);
}
```

## RegisteredClientRepository Bean 생성
Authorization Server에서 사용하는 3개의 테이블(Client, Authorization, Consent)에 대한 Repository Bean을 설정하게 되는데 AuthorizationService와 AuthorizationConsentService는 따로 Custom Bean을 정의하지 않으면 InMemory의 Repository가 Bean으로 등록이 되는데 RegisteredClient에 대한 최초 등록 Client가 필요한 Client는 InMemoryRepository를 사용하게 되어도 별도의 Bean을 생성해준다
```java
@Bean 
public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("oidc-client")
        .clientSecret("{noop}secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
        .postLogoutRedirectUri("http://127.0.0.1:8080/")
        .scope(OidcScopes.OPENID)
        .scope(OidcScopes.PROFILE)
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build();

    return new InMemoryRegisteredClientRepository(oidcClient);
}
```

## JWKSource에 대한 Bean 생성
JWT Token에 대한 전자서명을 위하여 JWKSource에 대한 Bean을 정의한다
```java
@Bean 
public JWKSource<SecurityContext> jwkSource() {
    KeyPair keyPair = generateRsaKey();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    RSAKey rsaKey = new RSAKey.Builder(publicKey)
        .privateKey(privateKey)
        .keyID(UUID.randomUUID().toString())
        .build();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return new ImmutableJWKSet<>(jwkSet);
}

private static KeyPair generateRsaKey() { 
    KeyPair keyPair;
    try {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
    }
    catch (Exception ex) {
        throw new IllegalStateException(ex);
    }
    return keyPair;
}
```

## JwtDecoder Bean 생성
Resource Server에서 인증을 위하여 전달되는 JWT Token을 검증하기 위하여 jwtDecoder도 Bean으로 등록해준다.
```java
@Bean 
public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
    return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
}
```

## Authorization Server 세팅 정보 설정을 위한 AuthorizationServerSettings에 대한 Bean 설정
Authorization Server에 대한 설정인 AuthorizationServerSetting Bean도 생성하게 되는데 별다른 Custom 설정이 없으면 AuthorizationServerSettings 클래스에서 제공하는 빌더 함수를 통한 기본 객체를 Bean으로 설정한다.
```java
@Bean 
public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
}
```