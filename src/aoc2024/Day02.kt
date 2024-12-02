package aoc2024

import util.println
import util.readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach { line ->
            val sequence = line.split(" ").map { it.toInt() }

            if (sequence.toSortedSet().sorted() == sequence || sequence.toSortedSet().sortedDescending() == sequence) {
                var b = true
                for (w in sequence.windowed(2)) {
                    if (abs(w[0] - w[1]) > 3) {
                        b = false
                        break
                    }
                }
                if (b) {
                    sum++
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach { line ->
            val sequence = line.split(" ").map { it.toInt() }


            var b = true
            for (w in sequence.windowed(2)) {
                if (abs(w[0] - w[1]) > 3) {
                    b = false
                    break
                }
            }
            if (b && (sequence.toSortedSet().sorted() == sequence || sequence.toSortedSet().sortedDescending() == sequence)) {
                sum++
            } else {
                for (i in sequence.indices) {
                    var b = true
                    val newSequence = mutableListOf<Int>()
                    for (j in sequence.indices) {
                        if (i != j) newSequence.add(sequence[j])
                    }
                    for (w in newSequence.windowed(2)) {
                        if (abs(w[0] - w[1]) > 3) {
                            b = false
                            break
                        }
                    }
                    if (b) {
                        if (newSequence.toSortedSet().sorted() == newSequence || newSequence.toSortedSet().sortedDescending() == newSequence) {
                            sum++
                            break
                        }
                    }
                }
            }
        }

        return sum
    }

    val input = readInput("Input")
    val test = readInput("Test")
    part1(test).println()
    part1(input).println()
    part2(test).println()
    part2(input).println()
}
