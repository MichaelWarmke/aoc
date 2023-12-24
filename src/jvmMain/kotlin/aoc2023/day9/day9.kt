package aoc2023.day9

import java.io.File

fun extrapolate(input: MutableList<Long>): Long {
    var seq = input
    var totalLast = 0L
    val firstValues = mutableListOf<Long>()
    while (seq.any { it != 0L }) {
        println(seq)
        seq = generateSequence(seq)
        totalLast += seq.lastOrNull() ?: 0
        firstValues.add(seq.first())
    }

    var totalFirst = 0L
    firstValues.reversed().forEach {
        totalFirst = it - totalFirst
        println(totalFirst)
        totalFirst
    }
    return input.first() - totalFirst

//    return totalLast + input.last();

}

fun generateSequence(input: List<Long>): MutableList<Long> {
    val result = mutableListOf<Long>()
    for (i in 0 until input.lastIndex) {
        result.add(input[i + 1] - input[i])
    }
    return result
}

fun calculateSumOfExtrapolatedValues(input: List<List<Long>>): Long {
    return input.sumOf { extrapolate(it.toMutableList()) }
}

fun main() {
    val sequences = listOf(
        listOf(0L, 3L, 6L, 9L, 12L, 15L),
        listOf(1L, 3L, 6L, 10L, 15L, 21L),
        listOf(10L, 13L, 16L, 21L, 30L, 45L)
    )

    val lines = File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day9.txt").bufferedReader()
        .readLines()
        .filter { it.isNotBlank() }
//        .take(1)
        .map { it -> it.split(" ").map { it.toLong() } }


//    println("The sum of the extrapolated values is: ${calculateSumOfExtrapolatedValues(sequences)}")
    println("The sum of the extrapolated values is: ${calculateSumOfExtrapolatedValues(lines)}")
}