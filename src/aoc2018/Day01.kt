package aoc2018
import util.*

fun main() {
    fun part1(input: List<String>): Int {
        var freq : Int = 0

        input.forEach {
            freq += it.toInt()
        }

        return freq
    }

    fun part2(input: List<String>): Int {
        val freqs  = mutableSetOf<Int>(0)
        var currentfreq = 0

        while (true) {
            input.forEach {
                val delta = it.toInt()
                currentfreq += delta

                if (freqs.contains(currentfreq)) {
                    return currentfreq
                } else {
                    freqs.add(currentfreq)
                }
            }
        }
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    //val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
