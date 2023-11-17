## Authorization Server에서 Cusmtom Authentication 구현 하기
Authorization Server에서도 formLogin 방식을 사용하게 되면 UsernamePasswordAuthenticationFilter가 동작하여 사용자에 대한 로그인 인증을 받게 된다. 인증받지 않은 사용자가 Token이나 Authorization Code를 요청할 경우 로그인 화면이 제공되고, 해당 로그인 화면에서 사용자가 ID/PW를 입력하게 되면 

## Authorization Server에서 userDetailsService Custom하기
UserDetailsService 인터페이스를 구현한 커스텀 userDetailsService를 생성하고, loadUserByUsername를 구현하여 파라미터로 넘어오는 username에 맞는 유저정보를 가져와 UserDetails 객체 타입으로 리턴해주면 유저 인증시 사용자가 입력한 username/password와 리턴된 userDetails 객체의 유저정보로 username/password를 검증하게 된다
```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUserId(username);
        return User.builder()
                .username(username)
                .password("{noop}" + employee.getPassword())
                .authorities("ROLE_USER")
                .build();
    }
}
```

이렇게 커스텀한 userDetailsService를 DaoAuthenticationProvider 객체내 userDetailsService 세팅시에 해당 객체로 세팅해주면 로그인 화면에서 사용자가 입력한 id/pw에 대한 검증이 해당 custom한 userDetailsService에서 리턴된 userDetails 객체의 유저정보를 기준으로 사용자가 체크된다
```java
@EnableWebSecurity
public class DefaultSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/getUser").access("hasAuthority('SCOPE_read')")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        http.authenticationProvider(daoAuthenticationProvider);
        return http.build();
    }
```

위 코드에서 daoAuthenticationProvider.setUserDetailsService(customUserDetailsService); 이 부분이 커스텀 생성한 userDetailsService를 DaoAuthenticationProvider에 userDetailsService 객체로 설정해주는 코드이다.