package aoc2018

import util.println
import util.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var twos = 0;
        var threes = 0;

        input.forEach { line ->
            val map = line.trim()
                .split("")
                .filterNot { it == "" }
                .groupingBy { it }.eachCount()

            if (map.values.contains(3)) {
                threes++
            }
            if (map.values.contains(2)) {
                twos++
            }
        }

        return twos * threes
    }

    fun part2(input: List<String>): String {
        var overlap : String = ""
        input.forEach { a ->
            input.forEach { b ->
                var diff = 0
                for (i in a.indices) {
                    if (a[i] != b[i]) diff++
                }
                if (diff == 1) {
                    for (i in a.indices) {
                        if (a[i] == b[i]) {
                            overlap += a[i]
                        }
                    }
                    return overlap
                }
            }
        }

        return overlap
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
