# Safe Call Operator

코틀린의 Safe call operator인 ?.를 사용하면 널 체크화 메서드 호출의 작업을 간단하게 단일화 할 수 있다
예를 들면 아래와 같은 문구를 생각해보면

```kotlin
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)
}
```
만약 매서드 매개변수 s의 값이 null이면 toUpperCase 매서드가 실행되지 않고, null이 아닌 경우만 실행되게 된다.
즉 아래와 같은 코드와 동일하게 수행된다고 볼 수 있다
```kotlin
if (s != null) s.toUpperCase() else null
```

# Elvis Operator
코틀린은 null 대신에 기본값을 제공하기 위한 손쉬운 기능을 가지고 있다. 이를 Elvis Operator라고 부른다.
아래의 예를 통해서 어떻게 사용되는지 확인할 수 있다.
```kotlin
fun foo(s: String?) {
    val t: String = s ?: ""
}
```

t 변수에 대한 대입값으로 만약 s가 null이 아닐 경우에는 s가 t값으로 대입되고, s가 null일 경우에는 Empty String이 대입되게 된다.

# Safe-Call Operator와 Elvis Operator를 조합해서 사용
Safe-Call Operator 와 Elvis Operator가 조합해서 사용되는 경우도 있다. 이는 null값일 경우에 대한 다른 대체 값을 사용하고자 할 때 주로 이와 같은 방법을 사용한다
```kotlin
fun Person6.countryName2() = company?.address?.country ?: "Unknown"
```
이와 같이 company의 address의 country에 대한 null 체크를 위하여 Safe-Call Operator가 사용되었고 만일 null일 경우 다른 값(Unknown)으로 대체하고 Elvis Operator가 같이 사용되었다.

```kotlin
fun printShippingLabel(person: Person6) {
    val address = person.company?.address ?: throw IllegalArgumentException("No Address")
    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}
```
위의 경우도 Safe-Call Operator와 Elvis Operator가 같이 사용된 경우로 볼 수 있는데, 이 경우에는 person의 company내 address가 null 일경우 예외를 throw하여 처리하기 위하여 이와 같은 방법을 사용하였다.
즉 address 값이 null 일 경우에는 IllegalArgumentException 예외를 throw 하게 된다.