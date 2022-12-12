package aoc2022.day2

import java.io.File

enum class Choice(val alias: List<String>, val score: Int) {
    R(listOf("X", "A"),1),
    P(listOf("Y", "B"),2),
    S(listOf("Z", "C"),3);
}

fun getChoiceFromAlias(s: String): Choice {
    if (Choice.R.alias.contains(s)) return Choice.R
    if (Choice.P.alias.contains(s)) return Choice.P
    if (Choice.S.alias.contains(s)) return Choice.S
    throw IllegalArgumentException("invalid input $s")
}

val choices = listOf(Choice.R, Choice.P, Choice.S)
val actualList = File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\day2.txt").bufferedReader().readLines()
val list = listOf("A Y", "B X", "C Z")

fun getMyChoice(direction: String, other: Choice): Choice {
    return when (direction) {
        "X" -> choices[if (choices.indexOf(other) - 1 < 0) choices.size - 1 else choices.indexOf(other) - 1 ]
        "Y" -> other
        "Z" -> choices[if (choices.indexOf(other) + 1 >= choices.size) 0 else choices.indexOf(other) + 1]
        else -> throw IllegalArgumentException("invalid input $direction")
    }
}

fun main() {
    print(actualList.asSequence().map { it ->
        val pair = it.split(" ")
        val oppChoice = getChoiceFromAlias(pair[0])
        var myChoice: Choice? = null
        try {
            myChoice = getMyChoice(pair[1], oppChoice)
        } catch (e: Exception) {
            println()
        }
        playRound(myChoice!!, oppChoice) + myChoice.score
    }.sum())
}

fun playRound(my: Choice, other: Choice): Int {
    if (my == Choice.R && other == Choice.S ||
        my == Choice.P && other == Choice.R ||
        my == Choice.S && other == Choice.P)  return 6
    if (my == other) return 3
    return 0
}