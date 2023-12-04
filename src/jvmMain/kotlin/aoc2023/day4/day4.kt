package aoc2023.day4

import java.io.File
import kotlin.math.pow

fun main() {
    val cards =
        File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day4.txt").bufferedReader()
//    File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\tmp.txt").bufferedReader()
            .readLines()

    fun numberOfWins(s: String): List<String> {
        val split = s.split(":")
        val numSplit = split[1].split("|")

        val haveNums = numSplit[1].split(" ")
            .filter { it.isNotBlank() }
            .toSet()

        return numSplit[0].split(" ")
            .filter { it.isNotBlank() }
            .filter { haveNums.contains(it) }
    }


    println(cards.sumOf {
        val winningNums = numberOfWins(it)
        if (winningNums.isNotEmpty()) 2.0.pow(winningNums.size - 1) else 0.0
    })

    val cardWins = mutableListOf<Int>()
    cards.reversed().forEach {
        val wins = numberOfWins(it)

        if (wins.isEmpty())
            cardWins.add(0)
        else {
            val currentWins = if (cardWins.size < wins.size) cardWins.size else wins.size
            cardWins.add(cardWins.takeLast(wins.size).sum() + currentWins)
        }
    }

    println(cardWins)
    println(cardWins.sum() + cards.size)
}