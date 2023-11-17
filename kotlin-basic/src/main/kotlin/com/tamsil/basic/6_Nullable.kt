package com.tamsil.basic

import java.lang.IllegalArgumentException

fun strLenSafe(s: String?): Int = if(s != null) s.length else 0

fun main() {
    val managerEmployee = Employee("Hong", Employee("Woo", null))
    val nullManagerEmployee = Employee("Hong", null)
    println(managerName(managerEmployee))
    println(managerName(nullManagerEmployee))
}

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)
}

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person6(val name: String, val company: Company?)

fun Person6.countryName(): String {
    val country = this.company?.address?.country
    return if(country != null) country else "Unknown"
}

// With safe-call operator and Elvis operator
fun strLenSafe2(s: String?): Int = s?.length ?: 0
fun Person6.countryName2() = company?.address?.country ?: "Unknown"

fun printShippingLabel(person: Person6) {
    val address = person.company?.address ?: throw IllegalArgumentException("No Address")
    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}