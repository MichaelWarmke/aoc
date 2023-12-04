package aoc2023.day1

import java.io.File
import java.lang.Math.max
import java.lang.Math.min

fun main(args: Array<String>) {
    val actualList =
        File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day1.txt").bufferedReader()
//        File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\tmp.txt").bufferedReader()
            .readLines()

    val replaced = actualList.map { replaceNumberLiterals(it) }
    val replacedOther = actualList.map { other(it) }
    val total = replaced
        .map {
            val nums = it.filter { it.isDigit() }
            val one = nums[0]
            val two = nums[nums.length - 1]
            "$one$two".toInt()
        }

    replaced.forEachIndexed { i, v ->
        print(v)
        print(" - ")
        print(replacedOther[i])
        print(" - ")
        println(total[i])
    }

    println("foureightmppchbgz8lqbzqbjz27cksqxns".indexOf("four") in 0 until 10)
    println(total.sum());
    println(replacedOther.map { it.toInt() }.sum());
}

val nums = mapOf(
    "nine" to "9",
    "eight" to "8",
    "seven" to "7",
    "six" to "6",
    "five" to "5",
    "four" to "4",
    "three" to "3",
    "two" to "2",
    "one" to "1",
)

fun replaceNumberLiterals(s: String): String {
    var tmp = s
    var i = tmp.length
    var replacer = ""

    nums.forEach {
        if (tmp.indexOf(it.key) in 0 until i) {
            i = tmp.indexOf(it.key)
            replacer = it.key
        }
    }

    if (replacer.isNotBlank()) tmp = nums[replacer]?.let { tmp.replaceFirst(replacer, it) }.toString()

    i = -1
    replacer = ""
    nums.forEach {
        if (tmp.lastIndexOf(it.key) in i until tmp.length) {
            i = tmp.indexOf(it.key)
            replacer = it.key
        }
    }

    if (replacer.isNotBlank()) tmp = nums[replacer]?.let { tmp.replace(replacer, it) }.toString()

    return tmp
}

val otherNums = listOf(
    "nine", "9",
    "eight", "8",
    "seven", "7",
    "six", "6",
    "five", "5",
    "four", "4",
    "three", "3",
    "two", "2",
    "one", "1",
)

fun other(s: String): String {
    var i = s.length
    var iVal = ""
    var j: Int = -1
    var jVal = ""

    otherNums.forEach {
        val firstIndex = s.indexOf(it)
        if (firstIndex != -1 && min(i, firstIndex) < i) {
            i = firstIndex
            iVal = it
        }
        if (max(j, s.lastIndexOf(it)) > j) {
            j = s.lastIndexOf(it)
            jVal = it
        }
    }

    if (nums.containsKey(iVal)) iVal = nums[iVal].toString()
    if (nums.containsKey(jVal)) jVal = nums[jVal].toString()

    return iVal + jVal
}