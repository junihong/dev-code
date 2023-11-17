# 권한 부여 유형
- 권한부여란 클라이언트가 사용자를 대신해서 사용자의 승인하에 인가서버로부터 권한을 부여받는 것을 의미한다
- OAuth 2.0 메커니즘은 아래와 같은 권한 부여 유형들을 지원하고 있다

1. Authorization Code Grant Type
- 권한 코드 부여 타입, 서버 사이드 애플리케이션(웹 애플리케이션), 보안에 가장 안전한 유형
2. Implicit Grant Type(Deprecated)
- 암시적 부여 타입, 공개 클라이언트 애플리케이션(SPA 기반 자바스크립트 앱, 모바일 앱), 보안에 취약
3. Resource Owner Password Credentials Grant Type(Deprecated)
- 리소스 사용자 비밀번호 자격증명 부여 타입, 서버 애플리케이션, 보안에 취약
- Resource Owner의 ID/PW를 전달하여 인증받는 방식
4. Client Credentials Grant Type
- 클라이언트 자격 증명 권한 부여 타입, UI or 화면이 없는 서버 애플리케이션
- Client Id, Client Secret만으로 인증받는 방식
5. Refresh Token Grant Type
- 새로고침 토큰 부여 타입, Authorization Code, Resource Owner Password Type 에서 지원
6. PKCE-enhanced Authorization Code Grant Type
- PKCE권한 코드 부여 타입, 서버사이드 애플리케이션, 공개 클라이언트 애플리케이션

## 매개 변수 용어
1. client_id
- 인가서버에 등록된 클라이언트에 대해 생성된 고유키
2. client_secret
- 인가서버에 등록된 특정 클라이언트의 client_id에 대해 생성된 비밀값
3. response_type
- 애플리케이션이 권한 부여 코드 흐름을 시작하고 있음을 인증 서버에 알려준다
- code, token, id_token이 있으며 token, id_token은 implicit 권한부여 유형에서 지원해야 한다
- 서버가 쿼리 문자열에 인증 코드(code), 토큰(token, id_token) 등을 반환
4. grant_type
- 권한 부여 타입 지정 : authorization_code, password, client_credentials, refresh_token
5. redirect_uri
- 사용자가 응용 프로그램을 성공적으로 승인하면 권한 부여 서버가 사용자를 다시 응용 프로그램으로 리다이렉션한다
- redirect_uri가 초기 권한 부여 요청에 포함된 경우 서비스는 토큰 요청에서도 이를 요구해야 한다
- 토큰 요청의 redirect_uri는 인증 코드를 생성할 때 사용된 redirect_uri와 정확히 일치해야 한다. 그렇지 않으면 서비스는 요청을 거부해야 한다
6. scope
- 애플리션이 사용자 데이터에 접근하는 것을 제한하기 위해 사용된다 - email profile read write
- 사용자에 의해 특정 스코프로 제한된 권한 인가권을 발행함으로써 데이터 접근을 제한단다
7. state
- 응용 프로그램은 임의의 문자열을 생성 및 요청에 포함하고 사용자가 앱을 승인한 후 서버로부터 동일한 값이 반환되는지 확인해야 한다
- CSRF 공격을 방지하는데 사용

# Authorization Code Grant
## 흐름 및 특징
- 사용자가 애플리케이션을 승인하면 인가서버는 Redirect URI로 임시 코드 담아서 애플리케이션으로 다시 리다이렉션 한다
- 애플리케이션은 해당 임시 코드를 인가서버로 전달하고 액세스 토큰으로 교환한다
- 애플리케이션이 액세스 토큰을 요청할 때 해당 요청을 클라이언트 암호로 인증할 수 있으므로 공격자가 인증 코드를 가로채서 스스로 사용할 위험이 줄어듬
- 액세스 토큰이 사용자 또는 브라우저에 표시되지 않고 애플리케이션에 다시 전달하는 가장 안전한 방법이므로 토큰이 다른 사람에게 누출될 위험이 줄어듬

## 권한부여코드 요청시 매개변수
- response_type=code(필수)
- client_id(필수)
- redirect_uri(선택사항)
- scope(선택사항)
- state(선택사항)

## 액세스토큰 교환 요청시 매개변수
- grant_type=authorization_code(필수)
- code(필수)
- redirect_uri(리다이렉션 URL가 초기 승인 요청에 포함된 경우에는 필수)
- client_id(필수)
- client_secret(필수)

# Implicit Grant
## 흐름 및 특징
- 클라이언트에서 Javascript 및 HTML 소스 코드를 다운로드한 후 브라우저는 서비스에 직접 API 요청을 한다
- 코드 교환 단계를 건너뛰고 대신 액세스 토큰이 쿼리 문자열 조각으로 클라이언트에 즉시 반환된다
- 이 유형은 back channel이 없으므로 refresh token을 사용하지 못한다
- 토큰 만료시 애플리케이션이 새로운 Access Token을 얻으려면 다시 OAuth 승인 과정을 거쳐야 한다

## 권한 부여 승인 요청시 매개변수
- response_type=token(필수), id_token
- client_id(필수)
- redirect_uri(필수)
- scope(선택사항)
- state(선택사항)

# Resource Owner Password Credentials Grant
## 흐름 및 특징
- 애플리케이션이 사용자 이름과 암호를 Access Token으로 교환할 때 사용된다
- 타사 애플리케이션이 이 권한을 사용하도록 허용해서는 안되고 고도의 신뢰할 자사 애플리케이션에서만 사용해야 한다

## 권한 부여 승인 요청 시 매개변수
- grant_type=password(필수)
- username(필수)
- password(필수)
- client_id(필수)
- client_secret(필수)
- scope(선택사항)

# Client Credentials Grant
## 흐름 및 특징
- 애플리케이션이 리소스 소유자인 동시에 클라이언트의 역할을 한다
- 리소스 소유자에게 권한 위임 받아 리소스에 접근하는 것이 아니라 자기 자신의 애플리케이션을 사용할 목적으로 사용하는 것
- 서버 대 서버간의 통신에서 사용할 수 있으며 IOT 같은 장비 애플리케이션과의 통신을 위한 인증으로도 사용할 수 있다
- Client Id와 Client Secret을 통해 액세스 토큰을 바로 발급 받을 수 있기 때문에 Refresh Token을 제공하지 않는다
- Client 정보를 기반으로 하기 때문에 사용자 정보를 제공하지 않는다

## 권한 부여 승인 요청 시 매개변수
- grant_type=client_credentials(필수)
- client_id(필수)
- client_secret(필수)
- scope(선택사항)

# Refresh Token Grant
## 흐름 및 특징
- 액세스 토큰이 발급될 때 함께 제공되는 토큰으로써 액세스 토큰이 만료되더라도 함께 발급받았던 리프레쉬 토큰이 유효하다면, 인증 과정을 처음부터 반복하지 않아도 액세스 토큰을 재발급 받을 수 있음
- 한 번 사용된 리프레쉬 토큰은 폐기되거나 재사용 가능

## 권한 부여 승인 요청시 매개변수
- grant_type=refresh_token(필수)
- refresh_token
- client_id(필수)
- client_secret(필수)

# PKCE-enhanced Authorization Code Flow
## PKCE(Proof Key for Code Exchange, RFC-6749) 개요
- 코드 교환을 위한 증명 키로서 CSRF 및 권한부여코드 삽입 공격을 방지하기 위한 Authorization Code Grant Flow의 확장버전이다.
- 권한부여코드 요청시 Code Verifier와 Code Challenge를 추가하여 만약 Aughorization Code Grant Flow에서 Authorization Code가 탈취당했을 때 Access Token을 발급받지 못하도록 차단한다
- PKCE는 원래 모바일 앱에서 Authorization Code Grant Flow를 보호하도록 설계되었으며 나중에 단일 페이지 앱에서도 사용하도록 권장되었으며 모든 유형의 OAuth2 클라이언트, 심지어 클라이언트 암호를 사용하는 웹서버에서 실행되는 앱에도 유용하다

## 코드 생성
1. Code Verifier
- 권한부여코드 요청 전에 앱에 원래 생성한 PKCE 요청에 대한 코드 검증기
- 48 ~ 128 글자수를 가진 무작위 문자열
- A-Z a-z 0-9 -.~~의 ASCII문자들로만 구성
2. Code Challenge
- 선택한 Hash 알고리즘으로 Code Verifier를 Hashing 한 후 Base64 인코딩을 한 값
- ex) Base64Encode(Sha256(ASCII(Code Verifier)))
3. Code Challenge Method
- plain : Code Verifier가 특정한 알고리즘을 사용하지 않도록 설정
- S256 : Code Verifier 해시 알고리즘 사용하도록 설정

## 처리 흐름
1. 클라이언트는 code_verifier를 생성하고, code_challenge_method를 사용하여 code_challenge를 계산한다
2. 클라이언트가 /authorize에 대한 요청을 작성한다
3. 권한 서버가 /authorize에 대한 표준 OAuth2 요청 유효성 검증을 수행한다
4. 권한 서버가 code_challenge 및 code_challenge_method의 존재를 확인한다
5. 권한 서버가 권한 코드에 대해 code_challenge 및 code_challenge_method를 저장한다
6. 권한 서버가 권한 코드 응답을 리턴한다
7. 클라이언트가 추가 code_verifier를 포함해 권한 코드를 /token에 제공한다
8. 권한 서버가 /token에 대한 표준 OAuth2 요청 유효성 검증을 수행한다
9. 권한 서버가 제공된 code_verifier 및 저장된 code_challenge_method를 사용하여 고유 code_challenge를 생성한다
10. 권한 서버가 생성된 code_challenge를 /authorize에 대한 초기 요청에 제공된 값과 비교한다
11. 두 값이 일치하면 액세스 토큰이 발행되고 일치하지 않으면 요청이 거부된다

## code_challenge_method 검증
1. 권한 부여 코드 흐름에 있어 인가서버는 code_verifier를 검증하기 위해 code_challenge_method를 이미 알고 있어야 한다
2. 토큰 교환시 code_challenge_method가 plain이면 인가서버는 전달된 code_verifier와 보관하고 있는 code_challenge 문자열과 단순히 일치하는지 확인만 하면 된다
3. code_challenge_method가 S256이면 인가서버는 전달된 code_verifier를 가져와서 동일한 S256 해시 메소드를 사용하여 변환한 다음 보관된 code_challenge 문자열과 비교해서 일치 여부를 판단한다