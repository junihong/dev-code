### Collection 에서 최대 값 찾기 예시
#### As-IS for문으로 탐색하는 방법
```kotlin
fun findTheOldest(people: List<Person>): Person? {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    return theOldest
}
```

#### To-BE maxBy값으로 검색
```kotlin
fun findTheOldestNew(people: List<Person>) : Person = people.maxBy { it.age }
```

### Filter와 Map
- 기본적인 사용법은 Java의 Stream과 동일
- Filter
```kotlin
println(people.filter { it.age > 30 })
```
- Map
```kotlin
people.map(Person::name)
people.filter { it.age > 30 }.map(Person::name)
```
### all, any
```kotlin
// 모두 만족하는지 확인하는 경우
people.all(canBeInClub27)
// 만족하는게 한개라도 있는지 확인하는 경우
people.any(canBeInClub27)
```

### groupBy
```kotlin
people.groupBy { it.age }
```

### 시퀀스
- 중간 연산에 대한 리스트 생성 없이 Lazy로 수행
```kotlin
people.asSequence()
        .map(Person::name)
        .filter { it.startsWith("a") }
        .toList()
```