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

    val lines = File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day8.txt").bufferedReader()
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
//    println(calculateSteps("AAA", "ZZZ", directions, paths))
    println(stepAll(paths.keys.filter { it.endsWith("A") }, directions, paths))
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


fun stepAll(startingNodes: List<String>, directions: String, paths: HashMap<String, List<String>>): Long {
    println(startingNodes)

    tailrec fun stepRecur(node: String, directionIndex: Int, total: Int, stepsCount: MutableList<Int>): List<Int> {
        if (stepsCount.size > 1) return stepsCount
        var nextTotal = total + 1
        if (node.endsWith("Z") ) {
            stepsCount.add(total)
            nextTotal = 0
        }

        val nextSteps = if (directions[directionIndex] == 'L') paths[node]!![0] else paths[node]!![1]
        return stepRecur(nextSteps, (directionIndex + 1) % directions.length, nextTotal, stepsCount)
    }
    val counts: MutableList<List<Int>> = mutableListOf()
    startingNodes.forEach { counts.add(stepRecur(it, 0, 0, mutableListOf())) }

    println(counts)

    return lcmOfMany(counts.map { it[0].toLong() })
}
fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    return a / gcd(a, b) * b
}

fun lcmOfMany(numbers: List<Long>): Long {
    if(numbers.isEmpty()) {
        throw IllegalArgumentException("Input list must not be empty")
    }

    var result = numbers[0]
    for (i in 1 until numbers.size) {
        result = lcm(result, numbers[i])
    }

    return result
}