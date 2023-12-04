package aoc2023.day3

import java.io.File


fun main(args: Array<String>) {
    val schematic =
        File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\day3.txt").bufferedReader()
//    File("C:\\Users\\Mike-AMD\\IntelliJIDEAProjects\\AOC\\src\\jvmMain\\resources\\2023\\tmp.txt").bufferedReader()
            .readLines()

    val travelled = mutableSetOf<Pair<Int, Int>>()
    val symbolsSet = setOf("$", "#", "@", "!", "%", "^", "&", "*","(",")","_","+","-","=","/")


    fun search(i: Int, j: Int): String {
        if (travelled.contains(i to j)) return ""
        if (i < 0 || i >= schematic.size) return ""
        if (j < 0 || j >= schematic[0].length) return ""

        travelled.add(i to j)
        if (schematic[i][j].toString() == "." || symbolsSet.contains(schematic[i][j].toString())) return ""

        var s = schematic[i][j].toString()
        s = search(i, j - 1) + s
        s += search(i, j + 1)

        print(" $s ")
        return s
    }

    val nums = mutableListOf<String>()
    val gears = mutableListOf<Pair<String, String>>()

    schematic.forEachIndexed { i, v ->
        schematic[i].forEachIndexed {j, c ->
            if (symbolsSet.contains(c.toString()) && !travelled.contains(i to j)) {
                println("\n$i $j $c ")

                val sublist = mutableListOf<String>()

                var search = search(i + 1, j)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i - 1, j)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i, j + 1)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i, j - 1)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i + 1, j + 1)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i - 1, j + 1)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i + 1, j - 1)
                if (search.isNotBlank()) sublist.add(search)
                search = search(i - 1, j - 1)
                if (search.isNotBlank()) sublist.add(search)

                if (sublist.size == 2) gears.add(sublist[0] to sublist[1])
                nums.addAll(sublist)
            }
        }
    }

    println()
    println(nums)
    println(nums.sumOf { it.toInt() })
    println(gears.sumOf { it.first.toInt() * it.second.toInt() })

}
