## Mocking 이란
Mocking은 유니셑스트시에 외부 의존성을 가지는 유닛테스트에 대하여 유닛 테스트 안에서 사용하게 되는 프로세스이다.
Mock 테스트의 목적은 외부 의존성에 의한 상태나 행위로부터 독립적으로 Mocking을 수행하기 위함이다

## Mockito 애노테이션을 사용하기 위한 활성화 방법
Mockito 테스트를 위한 애노테이션을 활성화 하는 방법은 아래와 같다.

1. 클래스내 @ExtendWith 애노테이션에 정의하는 방법
```java
@ExtendWith(MockitoExtension.class)
class Sample {
}
```

2. 프로그래밍적으로 MockitoAnnotation.openMocks를 호출하는 방법
```java
public class Sample {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
```

3. MockitoJUnit.rule() 메서드를 정의하는 방법
```java
public class Sample {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
}
```

### @Mock을 정의하는 방법
가장 많이 사용하는 바업ㅂ은 Mockito내의 @Mock 애노테이션을 활용하는 방법이다. @Mock 애노테이션을 통하여 Mockito.mock 과 같은 메뉴얼적인 호출 없이 Mocked 된 인스턴스를 생성하고 주입할 수 있다.

1. @Mock 애노테이션을 사용하지 않는 메뉴얼적인 방법
```java
public class Sample {
    @Test
    public void test(){
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
    }
}
```
2. @Mock 애노테이션을 이용하는 방법

```java
import com.tamsil.mocktest.repository.jpa.LessonRepository;

public class Sample {
    @Mock
    private LessonRepository lessonRepository;
}
```
## @Mock
```java
@ExtendWith(MockitoExtension.class)
class MemberServiceV2MockTest {

    @InjectMocks
    private MemberServiceV2 memberServiceV2;

    @Mock
    private LessonJpaRepository lessonRepository;

    @Mock
    private ReservationJpaRepository reservationJpaRepository;

    @Mock
    private NotificationSender notificationSender;

    @Test
    void reserveLessonTest() throws Exception {
        // given
        given(lessonRepository.findByDateAndStartTimeAndEndTime(date, startTime, endTime)).willReturn(Optional.of(Collections.singletonList(lesson)));
        given(reservationJpaRepository.save(any())).willReturn(reservation);
        given(notificationSender.sendMessage(anyString(), any())).willReturn("SUCCESS");

        // when
        Reservation savedReservation = memberServiceV2.reserveLesson(member, date, startTime, endTime);

        // then
        then(lessonRepository).should(times(1)).findByDateAndStartTimeAndEndTime(date, startTime, endTime);
        then(reservationJpaRepository).should(times(1)).save(any());
        then(notificationSender).should(times(1)).sendMessage(anyString(), any());
    }
}
```
위와 같이 @Mock 애노테이션을 활용하여 Mocking Stub 객체를 선언하고 @InjectMock 애노테이션을 사용하여 해당 클래스가 필요한 의존성과 맞는 Mock 객체들을 감지하여 클래스 객체 생성시 주입될 수 있도록 사용한다

## @Spy
클래스 내부의 메서드중이 일부만 Stub하고 일부는 실제 기능을 그대로 사용하고 싶은 경우에는 @Spy 애노테이션을 활용한다
예를들면 아래와 같이 MemberRepository 내의 existMember 메서드는 그대로 사용하고 createMemeber 메서드는 Stub을 하고 싶을 경우에는 아래와 같이 @Spy 애노테이션을 활용하여 Stub 하고 싶은 메서드에 대해서만 given() 메서드를 활용하면 된다
```java
@Repository
public class MemberRepository {
    private Map<String, Member> memberMap = new HashMap<>();

    public boolean existMember() {
        return false;
    }

    public Member createMember() {
        Member member = Member.builder()
                .name("john")
                .age(30)
                .build();
        return member;
    }
}
```
아래와 같이 @Spy 애노테이션을 활용하여 Stub 대상 메서드에 대해서만 given() 처리
```java
@ExtendWith(MockitoExtension.class)
class MemberSpyTest {
    @Spy
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceV1 memberServiceV1;

    @Test
    void findByLessonTest() {
        // given
        given(memberRepository.createMember()).willReturn(member);

        // when
        List<Member> resultList = memberServiceV1.createMember();

        // then
        then(memberRepository).should(times(1)).createMember();
    }
}
```

## @Mock 과 @MockBean
Mockito에서 제공하는 Mock 객체는 클래스 또는 인터페이스의 mock 객체를 생성할 수 있도록 지원해 준다
정의한 Mock 객체가 호출되면 해당 객체의 메서드의 리턴값을 정의할 수 있는 Stub을 생성할 수 있다
Mocking 내에서 의존성은 진짜 객체의 행위를 모방하는 대체 객체에 의해 컨트롤 되면서 대체된다

### @SpringBoot 의 @MockBean 애노테이션
스프링부트에서 지원하는 @MockBean 애노테이션을 활용하여 Mock 객체를 스프링의 ApplicationContext에 추가할 수 있다. 이렇게 되면 이 Mock 객체는 Application Context에 존재하는 같은 타입의 빈을 대체하게 된다
반약에 같은 타입의 정의된 빈이 없을 경우에는 새롭게 빈을 추가해 준다
```java
@SpringBootTest
@Slf4j
class MemberServiceMockBeanTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private LessonRepository lessonRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private NotificationSender notificationSender;

    @Test
    void reserveLessonTest() throws Exception {
        given(lessonRepository.findByDateAndStartTimeAndEndTime(date, startTime, endTime)).willReturn(Optional.of(Collections.singletonList(lesson)));
        given(reservationRepository.save(any())).willReturn(reservation);
        given(notificationSender.sendMessage(anyString(), any())).willReturn("SUCCESS");

        Reservation savedReservation = memberService.reserveLesson(member, date, startTime, endTime);
    }
}
```
위와 같이 @MockBean 애노테이션을 활용하여 필드를 정의하게 되면 Mock 객체는 필드 주입되는 뿐만 아니라 Application Context에 빈으로 등록 된다
이를 통하여 Mockito에서 제공하는 given 메서드를 활용하여 결과값을 정의하고자 하는 메서드에 대한 정의를 구현할 수 있다

## BDDMockito 에 대하여
BDD는 테스트 코드 작성시 자연스러운 흐름에 맞춰, 코드 가독성을 향상시키는 행위기반의 어플리케이션에 초점을 맞추고 있다
이는 테스트 코드를 작성함에 있어서 아래 3가의 방법에 대하여 정의하고 있다
- given : 테스트를 위하여 미리 정의해야 되는 상황(Arrange)
- when : 액션이 발생하는 상황(Act)
- then : 결과물에 대한 증명(Assert)

이와 같은 BDD를 구현하기 위하여 Mockito 라이브러리에서 지원하는 BDDMockito 클래스를 통하여 BDD 친화적인 API를 사용할 수 있다.
이 API를 사용하여 테스트 코드 작성시 given(), then() 메서드를 활용하여 결과를 검증할 수 있는 BDD 친화적인 테스트 코드를 작성할 수 있다.

### 사용 방법
아래와 같이 mockito-core 라이브러리에 대한 의존성을 추가해주면 되는데, spring-boot-starter-test를 의존성에 추가할 경우 별도로 Mockito-core에 대한 의존성을 추가할 필요는 없다
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>2.21.0</version>
</dependency>
```

BDDMockito는 아래와 같이 기존 Mockito와 import되는 패키지 구조 또한 다르다
```java
import static org.mockito.BDDMockito.*;
```

### Mockito와 BDDMockito의 차이
보통 테스트 코드 작성시 Mockito 라이브러리를 사용하게 되면 when().then()과 같은 스텝에 따라서 테스트 코드를 작성하게 되고, 이후 결과물에 대하여 verify()를 통하여 검증하게 된다.
BDDMockito는 Mockito 함수를 통하여 BDD의 다양한 alias를 제공한다. 이를 통하여 우리는 기존에 BDD에서의 when 대신에 given, 이후 검증시 verify 대신에 then을 사용하게 된다

## 테스트 더블이란
테스트 더블이란 테스트 목적을 위하여 운영 환경의 각종 객체들에 대하여 대체 케이스를 사용하여 테스트를 수행하게 되는 것을 의미한다. 이는 다양한 종류의 더블을 정의한다
- Dummy : 실제 사용되지 않고 호출시 통과되는 객체
- Fake : 실제 구현체의 사용이 필요하긴 하지만 보통 운영환경에서 테스트를 하기에 적합하지 않은 경우 사용되는 숏컷
- Stubs : 테스트중에 발생하는 호출에 대한 응답을 제공하고, 테스트를 위한 프로그래밍 외의 것에는 응답하지 않는다
- Spies : 호출 방법에 따라서 특정 정보를 기록하는 스텁
- Mocks : 기대하는 결과값을 미리 정의하게 되는 객체. 만약에 기대하는 결과값을 받지 못하거나 기대되는 목표를 보장하지 못할 경우에 대하여 체크하고 이와 같은 케이스에 대하여 Exception을 발생시킨다

## Static Class 를 Mocking 하는 방법 (mockStatic)
테스트시에 Static 클래스에 대하여 Mocking이 필요한 경우가 발생한다. Mockito 3.4.0 이전 버전까지는 직접적으로 static 클래스, 또는 메서드에 대하여 직접적으로 Mocking이 불가능 하였고, PowerMockito 와 같은 추가적인 라이브러리의 사용이 필요 했다.
3.4.0 이후 버전부터 사용 가능한 Mockito를 활용한 mock static method에 대하여 살펴 보겠다
### mockStatic 사용 방법
아래와 같이 static 유틸 메서드가 있다고 가정해보자
```java
public class ApplicationUtil {
    public static String getReservationId(String studentName, String teacherName) {
        return String.join("_", studentName, teacherName);
    }

    public static String getProfile() {
        String activeProfile = System.getProperty("spring.profiles.active");
        return Optional.ofNullable(activeProfile).orElse("default");
    }
}
```
우선 테스트를 하기 이전에 MockMaker inline의 활성화 설정이 필요하다.
프로젝트 내 src/test/resources/mockito-extensions 디렉토리를 생성하고 org.mockito.plugins.MockMaker 이름의 파일을 해당 위치에 생성하고 아래와 같은 내용을 작성한다
```properties
mock-maker-inline
```
이전에 언급한 바와 같이 Mockito 3.4.0 이후 버전부터는 Mockito.mockStatic(Class<T> classToMock)과 같이 static 메서드를 호출하기 위한 mock 메서드를 사용할 수 있다.
이 메서드는 MockedStatic 객체를 선언한 타입에 맞게 리턴해주게 되고, 이는 mock 객체 영역이 된다
따라서 유닛테스트시에 이 mock 객체는 쓰레드로컬 영역이 되고 이는 try-with-resources 등을 활용하여 해당 scope 실행이 종료되었을때 close 작업이 필요하다
- 매서드 매개변수가 없는 경우에 대한 mockStatic 활용 방법
```java
public class Sample {
    @DisplayName("mockStatic 테스트 - 매서드 매개변수가 없는 경우")
    @Test
    void mockStaticTest_NoArgs() {
        assertThat(ApplicationUtil.getName()).isEqualTo("tamsil");

        try (MockedStatic<ApplicationUtil> applicationUtilMockedStatic = Mockito.mockStatic(ApplicationUtil.class)) {
            applicationUtilMockedStatic.when(ApplicationUtil::getProfile).thenReturn("default");
            assertThat(ApplicationUtil.getProfile()).isEqualTo("default");
        }

        assertThat(ApplicationUtil.getProfile()).isEqualTo("default");
    }    
}
```

- 메서드 배개변수가 있는 경우에 대한 mockStatic 활용 방법
```java
public class Sample {
    @Test
    void mockStaticTest_WithArgs() {
        String studentName = "john";
        String teacherName = "david";
        assertThat(ApplicationUtil.getReservationId(studentName, teacherName)).isEqualTo(String.join("_", studentName, teacherName));

        try (MockedStatic<ApplicationUtil> utilMockedStatic = Mockito.mockStatic(ApplicationUtil.class)) {
            utilMockedStatic.when(() -> ApplicationUtil.getReservationId(studentName, teacherName))
                    .thenReturn(String.join("_", studentName, teacherName));

            assertThat(ApplicationUtil.getReservationId(studentName, teacherName)).isEqualTo(String.join("_", studentName, teacherName));
        }

        assertThat(ApplicationUtil.getReservationId(studentName, teacherName)).isEqualTo(String.join("_", studentName, teacherName));
    }    
}
```

### 참고자료
- https://martinfowler.com/bliki/TestDouble.html
- https://www.baeldung.com/java-spring-mockito-mock-mockbean
- https://www.baeldung.com/mockito-annotations
- https://www.baeldung.com/bdd-mockito
- https://www.geeksforgeeks.org/difference-between-mock-and-injectmocks-in-mockito/