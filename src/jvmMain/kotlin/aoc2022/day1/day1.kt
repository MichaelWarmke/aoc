package aoc2022

import java.io.File

fun calc(items: List<String>): Int {
    val totals = mutableListOf<Int>()
    var curTotal = 0

    for (item in items) {
        if (item.isEmpty()) {
            totals.add(curTotal)
            curTotal = 0
        } else
            curTotal += item.toInt()
    }

    return totals.sorted().takeLast(3).sum()
}

var list = listOf("100", "" , "100", "100")
var actualList = File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\day1.txt").bufferedReader().readLines()

fun main(args: Array<String>) {
    print(calc(actualList))
}