# InMemory 데이터베이스에서 외부 데이터베이스로 변경하기

Spring Authorization Server가 제공하는 기본 Configuration 설정으로 기본구성이 가능하지만 Default는 InMemory 데이터베이스를 기반으로 한 Repository가 생성된다. 개발환경이 아닌 이상 InMemory 환경으로 서비스를 하기에는 한계가 있기 때문에 Spring Authorization Server에서는 Jdbc 기반의 Repository 구현체를 제공해준다. 이를 활용하여 외부 데이터베이스를 연동하여 Configuration을 구성할 수 있다.

## RegisteredClientRepository 변경하기
먼저 InMemoryRepository로 구성되어 있던 RegistredClientRepository를 스프링에서 제공하는 구현체인 JdbcRegisteredClientRepository로 변경한다. JdbcRegisteredClientRepository를 생성자 매개변수로 JdbcOperation이 필요한데 Spring Framework에서 제공하는 JdbcOperation의 구현체인 JdbcTemplate을 SecurityConfig에서 주입받아 활용한다.

```java
@Bean
public RegisteredClientRepository registeredClientRepository() {
    String clientId = "oidc-client";
    JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
    if (registeredClientRepository.findByClientId(clientId) == null) {
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
        registeredClientRepository.save(oidcClient);
    }
    return registeredClientRepository;
}
```
RegisteredClientRepository 타입의 JdbcRegisteredClientRepository를 생성하여 필요시 RegisteredClient를 생성하여 저장하고, 이 RegisteredClientRepository를 Bean으로 등록한다.

## 기존 InMemoryOAuth2AuthorizationService 빈을 JdbcOAuth2AuthorizationService 타입의 빈으로 생성하여 변경하기
기존의 InMemory 타입의 OAuth2AuthorizationService를 Spring AuthrozationServer 에서 제공하는 JdbcOAuth2AuthorizationService 객체를 생성하여 변경한다. JdbcOAuth2AuthorizationService 또한 JdbcRegisteredClientRepository와 마찬가지로 생성자 매개변수로 JdbcOperations 타입의 객체와 추가로 RegisteredClientRepository 타입의 객체가 필요하여 주입받은 JdbcTemplate와 앞서 생성된 RegisteredClientRepository를 생성자 매개변수로 사용하여 빈을 생성한다.

```java
@Bean
public OAuth2AuthorizationService oAuth2AuthorizationService() {
    return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository());
}
```

## 기존 InMemoryOAuth2AuthorizationConsentService 빈을 JdbcOAuth2AuthorizationConsentService 타입의 빈으로 생성하여 변경하기
기존의 InMemory 타입의 OAuth2AuthorizationConsentService를 이 역시 Spring Authorization Server에서 제공하는 JdbcOAuth2AuthorizationConsentService 객체를 생성하여 변경한다. JdbcOAuth2AuthorizationService 또한 OAuth2AuthorizationService와 마찬가지로 생성자 매개변수에 JdbcOperations 타입의 객체와 RegisteredClientRepository 타입의 객체가 필요하다.

```java
@Bean
public OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService() {
    return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository());
}
```
