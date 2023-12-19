package aoc2023.day6

import java.math.BigInteger
import kotlin.math.floor

fun BigInteger.until(to: BigInteger) = generateSequence(this) { it + BigInteger.ONE }.takeWhile { it < to }
fun countWinningWays(times: List<BigInteger>, distances: List<BigInteger>): Int {
    var totalWays = 1L
    for (i in times.indices) {
        totalWays *= countWaysForOneRace(times[i], distances[i])
    }
    return totalWays.toInt()
}

fun countWaysForOneRace(time: BigInteger, distance: BigInteger): Long {
    var count = 0L
    for (i in BigInteger.ONE.until(time)) {
        if (i.times(time.minus(i)) > distance) count++
    }
    return count
}

fun main() {
    val times = listOf(BigInteger("7"), BigInteger("15"), BigInteger("30"))
    val distances = listOf(BigInteger("9"), BigInteger("40"), BigInteger("200"))

    println(countWinningWays(times, distances)) // Output: 288

    val timesAOC = listOf(BigInteger("44707080"))
    val distancesAOC = listOf(BigInteger("283113411341491"))
    println(countWinningWays(timesAOC, distancesAOC)) //
}
