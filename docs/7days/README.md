# 시나리오

spring security를 적용해보자

## build.gradle 수정
`spring-security` 추가

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
}
```

- 적용 확인

1. application 실행 시 로그 확인
```
Using generated security password: c40c8568-e806-470b-8b91-7feec0cd196e

This generated password is for development use only. Your security configuration must be updated before running your application in production.
```

2. 기존 API 호출 시 401 or 403 오류 

- 호출

```
GET http://localhost:8080/api/v1/users
Content-Type: application/json
```

- 응답

```
HTTP/1.1 401 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
WWW-Authenticate: Basic realm="Realm"
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Thu, 26 Sep 2024 07:23:37 GMT

<Response body is empty>
```

## SecurityConfig 설정

[SecurityConfig.java](src/main/java/la/gov/security/SecurityConfig.java)

```java
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // h2-console / swagger 접속 설정
        http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(withDefaults())
            .formLogin(withDefaults())
            .authorizeHttpRequests(authorizeRequest ->
                authorizeRequest
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                    .anyRequest().authenticated()
            )
            .headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
            )
        ;
        
        return http.build();
    }
}
```
### 적용 결과

- h2-console

<img src="./image/h2-console.png" />

- swagger-ui

<img src="./image/swagger.png" />

- api 호출 

```
GET http://localhost:8080/api/v1/users
```

```
HTTP/1.1 403
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Thu, 26 Sep 2024 08:10:16 GMT

<Response body is empty>

Response code: 403; Time: 58ms (58 ms); Content length: 0 bytes (0 B)
```

## 로그인 붙혀보기

### 순서
1. User 도메인에  id(username), password 추가하기
2. Security로 로그인 / 로그아웃 페이지 설정해보기
3. 로그인 서비스, 인증 객체 만들기 
4. 메인 이동 화면 만들기
5. 실행 확인  


### 1. User Entity에 [Account.java](src/main/java/la/gov/user/entity/Account.java) (username, password) 추가

```java
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
    
    @Column(name = "nickName")
    private String nickName;
    
    // 신규 추가 
    @Embedded
    private Account account;
  
    // User 객체 생성 방식 변경
    private User(final String name,
                 final Integer age,
                 final String nickName) {
      this.name = name;
      this.age = age;
      this.nickName = nickName;
    }

    private User(final String name,
                 final Integer age,
                 final String nickName,
                 final Account account) {
      this(name, age, nickName);
    
      this.account = account;
    }

    public static User createUser(final String name,
                                  final Integer age,
                                  final String nickName) {
        return new User(name, age, nickName);
    }
    
    public static User createUserWithAccount(final String name,
                                             final Integer age,
                                             final String nickName,
                                             final String username,
                                             final String password) {
    
        return new User(name, age, nickName, Account.create(username, password));
    }
}
```

[import.sql](src/main/resources/import.sql)도 추가 변경 

- 계정

username : jarvis_master, password : shield_tony

username : mjolnir_master, password : shield_thor

username : vibranium_master, password : shield_steve

username : gammaray_master, password : shield_bruce

username : ant_master, password : shield_hank

username : spider_master, pasword : shield_peter

### 2. Security 설정 추가 
```java
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(AbstractHttpConfigurer::disable)
          .httpBasic(withDefaults())
          .formLogin(e -> e.successForwardUrl("/main"))
          .authorizeHttpRequests(authorizeRequest ->
              authorizeRequest
                  .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                  .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
                  .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                  .anyRequest().authenticated()
          )
          .logout((logout) -> logout
              .logoutSuccessUrl("/logout")
              .deleteCookies("JSESSIONID", "remember-me")
              .logoutSuccessUrl("/login")
          )
          .headers(headers ->
              headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
          )
      ;
    
      return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
}
```

### 3. 로그인 서비스, 인증 객체 만들기

UserDetailsService를 구현한 UserLoginService 만들기 
```java
@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByAccount_username(username)
            .orElseThrow(UserNotFoundException::new);

        return new LoginUser(user);
    }
}
```

UserDetails를 구현한 LoginUser 만들기
```java
@Getter
public class LoginUser implements UserDetails {
    final User user;
   
    public LoginUser(final User user) {
      this.user = user;
    }
}
```

Security 추가 
```java
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    final UserLoginService userLoginService;
  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(AbstractHttpConfigurer::disable)
          .httpBasic(withDefaults())
          .formLogin(e -> e.successForwardUrl("/main"))
          // 로그인 인증 객체 서비스 연결 
          .userDetailsService(userLoginService)
          .authorizeHttpRequests(authorizeRequest ->
              authorizeRequest
                  .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                  .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
                  .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                  .anyRequest().authenticated()
          )
          .logout((logout) -> logout
              .logoutSuccessUrl("/logout")
              .deleteCookies("JSESSIONID", "remember-me")
              .logoutSuccessUrl("/login")
          )
          .headers(headers ->
              headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
          )
      ;
    
      return http.build();
    }
}
```

### 4. 메인 이동 화면 만들기

1. view 서비스 하도록 thymeleaf 추가

build.gradle 수정  

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
}
```

2. [MainController.java](src/main/java/la/gov/main/MainController.java) 작성

```java
@Controller
@RequestMapping("/main")
public class MainController {
    
    @PostMapping
    public ModelAndView index(@AuthenticationPrincipal LoginUser loginUser,
                              ModelAndView mv) {
      // loginUser 로그인한 인증 정보
      mv.addObject("data", String.format("Welcome! %s, S.H.I.E.L.D.", loginUser.getNickName()));
      mv.setViewName("index");
      return mv;
    }
}
```

3. [index.html](src/main/resources/templates/index.html) 작성


### 5. 실행 확인 

localhost:8080/login 접속

<img src="./image/login.png" />

- 로그인 실패 

<img src="./image/login-fail.png" />

- 로그인 성공

<img src="./image/login-success.png" />

- 로그아웃

<img src="./image/logout.png" />