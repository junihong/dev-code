# 컬렉션 타입
코틀린도 자바와 동일하게 기본 컬렉션 타입 3가지(List, Set, Map)을 제공하며, 불변(Immutable), 가변(Mutable) 컬렉션으로 나뉜다.

# 컬렉션을 생성하는 방법
가장 일반적인 방법은 표준 라이브러리 함수를 사용하는 것이다

- Immutable 리스트 생성
```kotlin

```

## Kotlin의 리스트
### 3가지의 가변 타입의 Collections
1. Mutable: 자유롭게 요소들의 추가/삭제가 가능
2. Read-Only: 추가/삭제가 불가능, 그러나 데이타 객체의 경우 가능
3. Immutable: 콜렉션에 대한 어떠한 수정도 불가능
### 코틀린에서의 리스트 타입
1. ArrayList: 자바의 ArrayList와 동일하고 가변
2. MutableList: MutableListOf()로 쉽게 초기화 가능하고, ArrayList로 생성됨
3. List: Read-Only로써 add(), remote() 함수 사용 불가능