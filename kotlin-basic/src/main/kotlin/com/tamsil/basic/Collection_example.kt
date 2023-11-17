package com.tamsil.basic

class Coffee(
    var name: String = "",
    var price: Int = 0,
){
    val brand: String
        get() = "스타벅스"
    var quantity: Int = 0
        set(value) {
            if (value > 0) {
                field = value
            }
        }
}

fun main() {
    val menuList: List<String> = listOf("피자", "햄버거", "라면")
    val mutableMenuList = mutableListOf<String>()
    mutableMenuList.add("피자")
    mutableMenuList.add("햄버거")
    mutableMenuList.add("라면")

    val mutableMenuList2 = mutableListOf<String>().apply {
        add("피자")
        add("햄버거")
        add("라면")
    }

    val numberSet = setOf(1, 2, 3, 4)
    val mutableSet = mutableSetOf<Int>()

    val numberMap = mapOf("one" to 1, "two" to 2)
    val mutableMap = mutableMapOf<String, Int>()
    mutableMap["one"] = 1
    mutableMap["two"] = 2
    mutableMap["three"] = 3

    var a: String? = null
    val b = a?.length ?: 0
}