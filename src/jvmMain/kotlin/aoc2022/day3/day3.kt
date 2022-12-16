package aoc2022.day3

import java.io.File

val actualList = File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\day3.txt").bufferedReader().readLines()

fun main(args: Array<String>) {

    println(actualList.asSequence().map {
        val dup = findDup(it)
        val priority = if (dup.isLowerCase()) {
            dup - 'a' + 1
        } else {
            dup - 'A' + 27
        }
        println(priority)
        priority
    }.sum())

    var acc = mutableListOf<String>()
    var count = 0
    var i = 0
    for (it in actualList) {
        if (count >= 3) {
            i += 1
            count = 0
        }

        count += 1
        acc[i] = acc[i] + it
    }
}

fun findDup(s: String): Char {
    val halfPoint = s.length / 2

    val partOne = mutableSetOf<Char>()
    for (i in 0 until halfPoint) {
        partOne.add(s[i])
    }

    for (i in halfPoint until s.length) {
        if (partOne.contains(s[i])) return s[i]
    }
    throw IllegalArgumentException("given sack does not have a duplicate")
}

