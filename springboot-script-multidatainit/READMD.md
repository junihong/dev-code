# springboot sql init 실행 방법
스프링 부트는 JDBC 또는 R2DBC에 대한 초기화 DDL, DML SQL 스크립트 실행을 지원한다

## sql 스크립트 설정 방법
ClassPath 위치에 아래 이름의 sql 파일을 위치시키면 스프링 부트가 자동으로 스크립트 실행
- schema.sql, data.sql
- 보통 DDL의 경우 schema.sql, DML의 경우 data.sql의 이름으로 활용

특정 platform 값에 따라 sql 파일을 다르게 하고 싶을 경우 아래 현식의 platform값을 변수화 하여 스크립트 파일을 생성할 수 있다
- schema-${platform}.sql, data-${platform}.sql
- 파일명에 해당하는 platform 변수값은 application.properties내에 spring.sql.init.platform 값으로 지정 가능

스프링부트는 디폴트값으로 SQL 데이터베이스 초기화 기능은 embedded 인메모리 데이터베이스일 경우에만 실행한다. 따라서 그 외의 데이터베이스에 대하여 실행하고 싶을 경우에는 아래 옵션을 always로 설정해 준다
- spring.sql.init.mode=always

스크립트 기반의 데이터베이스 초기화는 기본적으로 JPA의 EntityManagerFactory Bean이 생성되기 전에 실행된다. 보통은 Hibernate 기반의 Schema 생성 기능과 스크립트 기반의 초기화 기능을 같이 사용하는 것은 권장되지 않는다

만약 함께 사용하고 싶을 경우는 아래 설정값을 true로 추가해야 한다
- spring.jpa.defer-datasource-initialization=true
이 경우에는 EntityManagerFactory를 통해 Schema가 초기화되기 전에 스크립트 초기화는 실행되지 않고, Hibernate의 Schema 초기화가 실행된 이후에 스크립트 기반의 초기화가 실행되게 된다.

## Multi DataSource일 경우
Multi DataSource일 경우에는 @Primary로 설정된 데이터소스로 스크립트 초기화가 실행되게 된다