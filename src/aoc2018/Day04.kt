package aoc2018

import util.println
import util.readInput

fun main() {
    fun part1(input: List<String>): Int {
        val schedule : List<Timestamp> = input.map {
            val split = it.split("]")
            val datetime = split[0].drop(1).split(" ")
            val date = datetime[0].split("-")
            val time = datetime[1].split(":")
            Timestamp(ElfDate(date[0], date[1], date[2], time[0], time[1]), split[1].trim())
        }.sortedBy { it.date }

        val guards : MutableMap<Int, Int> = mutableMapOf(-1 to -1)

        var currentGuard : Int = -1
        var sleepTime = -1
        for (i in schedule.indices) {
            val action = schedule[i].action
            if (action.contains("#")) {
                currentGuard = action.split(" ")[1].drop(1).toInt()
                guards[currentGuard] = guards.getOrDefault(currentGuard, 0)
            } else if (action.contains("falls")) {
                sleepTime = schedule[i].date.minute
            } else if (action.contains("wake")) {
                guards[currentGuard] = guards[currentGuard]!! + (schedule[i].date.minute - sleepTime)
                sleepTime = 0
            }
        }

        return guards.maxOf { it.key }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    //val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Input")
    //val test = readInput("Test")
    part1(input).println()
    part2(input).println()
}


class ElfDate(
    val year : Int,
    val month : Int,
    val day : Int,
    val hour : Int,
    val minute :Int): Comparable<ElfDate> {

    constructor(
        year: String,
        month: String,
        day: String,
        hour: String,
        minute: String)
            : this(year.toInt(),
                month.toInt(),
                day.toInt(),
                hour.toInt(),
                minute.toInt()
            )

    override fun compareTo(other: ElfDate): Int = compareValuesBy(this, other,
    { it.year }, { it.month }, { it.day }, { it.hour }, { it.minute })
}

class Timestamp(val date : ElfDate, val action : String) {

}