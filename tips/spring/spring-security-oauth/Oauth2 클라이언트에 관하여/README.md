# OAuth 2.0 Client
## 개요
- OAuth 2.0 인가 프레임워크의 역할 중 인가서버 및 리소스 서버와의 통신을 담당하는 클라이언트의 기능을 필터 기반으로 구현한 모듈
- 간단한 설정만으로 OAuth 2.0 인증 및 리소스 접근 권한, 인가서버 엔드포인트 통신 등의 구현이 가능하며 커스터마이징의 확장이 용이하다

### OAuth 2.0 Login
- 애플리케이션 사용자를 외부 OAuth 2.0 Provider나 OpenID Connect 1.0 Provider 계정으로 로그인할 수 있는 기능을 제공한다
- 글로벌 서비스 프로바이더인 "구글 계정으로 로그인", "깃허브 계정으로 로그인" 기능을 OAuth 2.0 로그인을 구현해 사용할 수 있도록 지원한다
- OAuth 2.0 인가 프레임워크의 권한 부여 유형 중 Authorization Code 방식을 사용한다

### OAuth 2.0 Client
- OAuth 2.0 인가 프레임워크에 정의된 클라이언트 역할을 지원한다
- 인가 서버의 권한 부여 유형에 다른 엔드 포인트와 직접 통신할 수 있는 API를 제공한다
    - Client Credentials
    - REsource Owner Password Credentials
    - Refresh Token
- 리소스 서버의 보호자원 접근에 대한 연동 모듈을 구현할 수 있다

# application.ytml / OAuth2ClientProperties
## 클라이언트 권한 부여 요청 시작
1. 클라이언트가 인가서버로 권한 부여 요청을 하거나 토큰 요청을 할 경우 클라이언트 정보 및 엔드포인트 정보를 참조해서 전달한다
2. application.yml 환경설정 파일에 클라이언트 설정과 인가서버 엔드포인트 설정을 한다
3. 초기화가 진행되면 application.yml에 있는 클라이언트 및 엔드포인트 정보가 OAuth2ClientProperties의 각 속성에 바인딩 된다
4. OAuth2ClientProperties에 바인딩 되어 있는 속성의 값은 인가서버로 권한부여 요청을 하기 위한 ClientRegistration 클래스의 필드에 저장된다
5. OAuth2Client는 ClientRegistration을 참조해서 권한부여 요청을 위한 매개변수를 구성하고 인가서버와 통신한다

## OAuth2ClientProperties(prefix="spring.security.oauth2.client")
- Registration은 인가 서버에 등록된 클라이언트 및 요청 파라미터 정보를 나타낸다
- Provider는 공급자에서 제공하는 엔드포인트 등의 정보를 나타낸다
- 클라이언트 및 공급자의 정보를 registration / provider 맵에 저장하고 인가서버와의 통신 시 각 항목을 참조하여 사용한다

# ClientRegistration
## 개념
- OAuth 2.0 또는 OpenID Connect 1.0 Provider에서 클라이언트의 등록 정보를 나타낸다
- ClientRegistration은 OpenID Connect Provider의 설정 엔드포인트나 인가 서버의 메타데이터 엔드포인트를 찾아 초기화 할 수 있따
- ClientRegistration의 메소드를 사용하면 아래 예제처럼 편리하게 ClientRegistration을 설정할 수 있다
    - ClientRegistration clientRegistration = ClientRegistrations.fromIssuerLocation(“https://idp.example.com/issuer”).build();
    - 위 코드는 200 응답을 받을 때까지 https://idp.example.com/issuer/.well-known/openid-configuration, https://idp.example.com/.well-known/oauth-authorization-server 에 차례대로 질의해본다.

## 필드 정리
- registrationId : ClientRegistration을 식별할 수 있는 유니크한 ID.
- clientId : 클라이언트 식별자.
- clientSecret : 클라이언트 secret.
- clientAuthenticationMethod : provider에서 클라이언트를 인증할 때 사용할 메소드로서 basic, post, none (public 클라이언트) 을 지원한다.
- authorizationGrantType : OAuth 2.0 인가 프레임워크는 네 가지 권한 부여 타입을 정의하고 있으며 지원하는 값은 authorization_code, implicit, client_credentials, password다. 
- redirectUriTemplate : 클라이언트에 등록한 리다이렉트 URL로, 사용자의 인증으로 클라이언트에 접근 권한을 부여하고 나면, 인가 서버가 이 URL로 최종 사용자의 브라우저를 리다이렉트시킨다.
- Scopes : 인가 요청 플로우에서 클라이언트가 요청한 openid, 이메일, 프로필 등의 scope. 
- clientName : 클라이언트를 나타내는 이름으로 자동 생성되는 로그인 페이지에서 노출하는 등에 사용한다.
- authorizationUri : 인가 서버의 인가 엔드포인트 URI.
- tokenUri : 인가 서버의 토큰 엔드포인트 URI.
- jwkSetUri : 인가 서버에서 JSON 웹 키 (JWK) 셋을 가져올 때 사용할 URI. 이 키 셋엔 ID 토큰의 JSON Web Signature (JWS) 를 검증할 때 사용할 암호키가 있으며, UserInfo 응답을 검증할 때도 사용할 수 있다.
- configurationMetadata : OpenID Provider 설정 정보로서 application.properties 에 spring.security.oauth2.client.provider.[providerId].issuerUri를 설정했을 때만 사용할 수 있다.
- (userInfoEndpoint)uri : 인증된 최종 사용자의 클레임/속성에 접근할 때 사용하는 UserInfo 엔드포인트 URI.
- (userInfoEndpoint)authenticationMethod : UserInfo 엔드포인트로 액세스 토큰을 전송할 때 사용할 인증 메소드. header, form, query 를 지원한다.
- userNameAttributeName : UserInfo 응답에 있는 속성 이름으로, 최종 사용자의 이름이나 식별자에 접근할 때 사용한다
    - provider마다 다름 : google(sub), keycloak(preferred_username)