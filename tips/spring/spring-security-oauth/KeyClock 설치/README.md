## KeyClock 설정하기
1. 공홈에서 keyclock 다운받아서 압축 풀기
2. 압축풀고 인스턴스 실행
- ./bin/kc.sh start-dev

## 콘솔에서 설정하기
1. realm 신규 생성
2. Clients 생성
- Capability config -> Client authentication On으로 변경
- Authentication flow -> Implicit flow, Service accounts roles 체크
3. User 생성
- User 생성 후 Credentials 탭에서 패스워드 생성