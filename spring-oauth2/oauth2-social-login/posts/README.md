## OAuth2Client 코드 과정

### OAuth2ClientConfig
- OAuth2Client에 대한 초기화가 진행
- OAuth2LoginConfigure

### Sign In 클릭
- 제일 처음 요청을 받는 필터 : OAuth2AuthorizationReqquestRedirectFilter
    - 인가서버로부터 임시코드를 발급받는 요청을 하는 필터
    - sendRedirectForAuthorization 메서드에서 인가서버에 임시코드 발급 요청 API를 호출(saveAuthorizationRequest)

### 로그인 하면 인가서버를 통해 다시 /login/oauth2/code/google로 리다이렉트 되어 넘어옴
- 넘어온 요청은 받는 필터 : AbstractAuthenticationProcessingFilter -> OAuth2LoginAuthenticationFilter
- OAuth2LoginAUthenticationFilter로 인가서버에서 전달한 파라미터가 넘어온다
- 이 필터에서 인가서버로 토큰 요청 API를 호출시 필요한 정보들을 세팅한다
- OpenId가 포함되면 OidcAutorizationCodeAuthenticationProvider가 실제 토큰 API를 호출하여 토큰을 요청한다
    - 토큰 요청시 필요한 클라이언트 자격증명 정보를 세팅하는데 ClientSecretBasic, ClientSecretPost, None 3가지 방식이 있다(보통 디폴트는 ClientSecretBasic)
    - OidcUser를 만들기 위하여 userService.loadUser 메서드를 호출하는데 이때 커스텀으로 정의한 userService에서 오버라이딩한 loadUser메서드가 호출되고, 이때 스프링에서 제공하는 OidcUserService를 호출하여 OidcUser 객체가 생성된다
- 다시 AbstaractAuthenticationProcessingFilter로 넘어와서 인증받은 인증객체를 SecurityContext에 저장한다
- AbstractSecurityInterceptor에서 인증을 받고나서 /로 전달된다

### 이후 사용자 정보 조회 API를 호출하는데 이에 대한 ROLE Permit 정책을 확인한다
- AbstractSecurityInterceptor에 attempAuthorization에서 accessDecisionManager.decide에서 권한에 대한 확인을 진행한다
- accessDecisionManager에서 허용할 것인지 판단한다

### Naver같은 경우 Oidc를 지원하지 않기 때문에 위에 경우에서 처리되는 Provider가 변경된다
- OidcAuthorizationCodeAuthenticationProvider -> OAuth2AuthorizationCodeAuthenticationProvider