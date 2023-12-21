package aoc2023.day8

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val paths = HashMap<String, List<String>>()
//    paths["AAA"] = listOf("BBB", "CCC")
//    paths["BBB"] = listOf("DDD", "EEE")
//    paths["CCC"] = listOf("ZZZ", "GGG")
//    paths["DDD"] = listOf("DDD", "DDD")
//    paths["EEE"] = listOf("EEE", "EEE")
//    paths["GGG"] = listOf("GGG", "GGG")
//    paths["ZZZ"] = listOf("ZZZ", "ZZZ")
//
//    var directions = "LLR"

    val lines = File("C:\\Users\\mikew\\IdeaProjects\\aoc\\src\\jvmMain\\resources\\2023\\day8.txt").bufferedReader()
        .readLines()
        .filter { it.isNotBlank() }
    val regex = "([A-Z]*) = \\(([A-Z]*), ([A-Z]*)\\)".toRegex()
    var directions = ""
    lines.forEachIndexed() { i, v ->
        if(i == 0)
            directions = v
        else {
            val match = regex.find(v)
            paths[match?.groupValues?.get(1)!!] = listOf(match.groupValues[2], match.groupValues[3])
        }
    }
    println(directions)
    paths.forEach { println(it) }
    println(calculateSteps("AAA", "ZZZ", directions, paths))
}

fun calculateSteps(start: String, end: String, directions: String, paths: HashMap<String, List<String>>): Int {
    var steps = 0
    var index = 0
    var currentNode = start

    while (currentNode != end) {
        val direction = directions[index % directions.length]
        currentNode = if (direction == 'L') paths[currentNode]!![0] else paths[currentNode]!![1]
        steps++
        index++
    }
    return steps
}