package aoc2023.day2

import java.io.File
import java.lang.Math.max

fun main(args: Array<String>) {

    val readLines =
        File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day2.txt").bufferedReader()
//    File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\tmp.txt").bufferedReader()
        .readLines()

    val games = readLines
        .map { Game.from(it) }

    games.forEach { println(it) }
    println(games.filter { it.isValid(12, 14, 13) }.map { it.id }.sum())
    println(games.map { it.power }.sum())
}

data class Game(val id: Int, val red: Int, val blue: Int, val green: Int, val power: Int) {

    public fun isValid(redLimit: Int, blueLimit: Int, greenLimit: Int): Boolean {
        if (red > redLimit) return false
        if (blue > blueLimit) return false
        if (green > greenLimit) return false
        return true;
    }

    companion object {
        public fun from(s: String): Game {
            val split = s.split(":")
            val id = split[0].split(" ")[1].toInt()

            var red = 0
            var blue = 0
            var green = 0

            val cubes = split[1].split(",",";")
            cubes.forEach {
                val game = it.split(" ").filter { it.isNotEmpty() }
                when (game[1]) {
                    "red" -> red = max(red, game[0].toInt())
                    "blue" -> blue = max(blue, game[0].toInt())
                    "green" -> green = max(green, game[0].toInt())
                }
            }

            return Game(id, red, blue, green, red * blue * green)
        }
    }
}