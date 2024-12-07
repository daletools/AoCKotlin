package aoc2024

import util.println
import util.readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    val input = readInput("Input")
    val test = readInput("Test")

    part1(test).println()
    part1(input).println()
    part2(test).println()
    part2(input).println()
}
