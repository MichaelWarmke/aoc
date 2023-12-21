package aoc2023.day5

import java.io.File

fun main() {

    val almanac =
//        File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day5.txt").bufferedReader()
    File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\tmp.txt").bufferedReader()
            .readLines()

    val seeds = almanac[0].split(":")[1].split(" ").filter { it.isNotBlank() }

    val seedRanges = almanac[0].split(":")[1].split(" ").filter { it.isNotBlank() }
        .withIndex()
        .groupBy { it.index / 2 }
        .map { SeedRange(it.value[0].value.toLong(), it.value[1].value.toLong() + it.value[0].value.toLong()) }

    var i = 1
    var mapName = ""
    val maps = mutableMapOf<String, MutableList<Mapping>>()

    while (i < almanac.size) {
        if (almanac[i].isEmpty()) {
            i++
            continue
        }

        if (almanac[i].contains("map")) {
            mapName = almanac[i].split(" ")[0]
        } else {
            val map = Mapping.from(mapName, almanac[i])

            if (maps.containsKey(mapName)) {
                maps[mapName]?.add(map)
            } else {
                maps[mapName] = mutableListOf(map)
            }
        }
        i++
    }

    maps.forEach { println(it) }

    val seedsObj = seeds.map { Seed.from(maps, it.toLong()) }
    val locations = seedsObj.map { it.location }
    println(locations)
    println(locations.minOf { it })

   val seedRangeObjs = seedRanges.flatMap { listOf(Seed.from(maps, it.low), Seed.from(maps, it.high)) }
       .sortedBy { it.location }

   println(seedRangeObjs[0])
   println(seedRangeObjs[1])
}

data class SeedRange(val low: Long, val high: Long)

data class Seed(val num: Long, val soil: Long, val fert: Long, val water: Long, val light: Long, val temp: Long, val humid: Long, val location: Long) {

    companion object {
        fun from(maps: Map<String, List<Mapping>>, num: Long): Seed {

            fun gettingMapping(s: String, num: Long): Long {
                val map = maps[s]
                    ?.filter { it.isKeyApplicable(num) }
                    ?.map { it.getMappedValue(num) }

                return if(map?.isEmpty() == true) num else map?.get(0) ?: num

            }
            val soil = gettingMapping("seed-to-soil", num)
            val fert = gettingMapping("soil-to-fertilizer", soil)
            val water = gettingMapping("fertilizer-to-water", fert)
            val light = gettingMapping("water-to-light", water)
            val temp = gettingMapping("light-to-temperature", light)
            val humid = gettingMapping("temperature-to-humidity", temp)
            val location = gettingMapping("humidity-to-location", humid)
            return Seed(num, soil, fert, water, light, temp, humid, location)
        }
    }
}

data class Mapping(val name: String, val key: Long, val value: Long, val range: Long) {

    fun isKeyApplicable(num: Long): Boolean {
        return num in key until (key + range)
    }

    fun getMappedValue(num: Long): Long {
        return num - key + value
    }

    companion object {
        fun from(name: String, s: String): Mapping {
            val split = s.split(" ").filter { it.isNotBlank() }
            return Mapping(name, split[1].toLong(), split[0].toLong(), split[2].toLong())
        }
    }
}