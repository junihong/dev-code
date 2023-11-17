package com.tamsil.basic

import java.util.*

data class Person(val name: String, val age: Int)

class Book(val title: String, val authors: List<String>)

// As-IS Collection 에서 특정 값 찾기
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

// To-BE maxBy 함수 이용
fun findTheOldest2(people: List<Person>) : Person = people.maxBy { it.age }

fun findTheOldest3() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy { it.age })
}

fun findTheOldest4() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy { p: Person -> p.age })
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if(it.startsWith("4")) {
            clientErrors++
        }else if (it.startsWith("5")) {
            serverErrors++
        }
    }
}

fun main() {
    val getAge = {person: Person -> person.age}

    val list = listOf(1, 2, 3, 4)
    val people = listOf(Person("Alice", 31), Person("Bob", 29), Person("Carol", 31))
    println(people.filter { it.age > 30 })
    println(list.filter { it % 2 == 0 })
    println(list.map { it * it })

    people.map(Person::name)
    people.filter { it.age > 30 }.map(Person::name)
    people.filter { it.age == people.maxBy(Person::age).age }

    val numbers = mapOf(0 to "zero", 1 to "one")
    numbers.mapValues { it.value.uppercase(Locale.getDefault()) }

    val canBeInClub27 = {p: Person -> p.age <= 27}
    // 모두 만족하는지 확인하는 경우
    people.all(canBeInClub27)
    // 만족하는게 한개라도 있는지 확인하는 경우
    people.any(canBeInClub27)

    // 리스트에서 특정 조건으로 그룹핑
    people.groupBy { it.age }

    people.map(Person::name).filter { it.startsWith("A") }

    people.asSequence()
        .map(Person::name)
        .filter { it.startsWith("A") }
        .toList()
}