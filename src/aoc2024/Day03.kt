package aoc2024

import util.println
import util.readInput
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Long {
        var sum : Long = 0
        val regex = Regex("""mul\(\d{1,3},\d{1,3}\)""")
        regex.findAll(input.joinToString(",")).forEach { match ->
            val (a, b) = match.value.drop(4).dropLast(1).split(",").map { it.toInt() }
            sum += a * b
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        var str = input.joinToString("")
        var sum : Long = 0
        val regex = Regex("""mul\(\d{1,3},\d{1,3}\)""")
        val on = """do\(\)""".toRegex()
        val off = """don't\(\)""".toRegex()
        var enabled = true

        while (regex.containsMatchIn(str)) {
            var nextOn = str.length
            var nextOff = str.length
            val nextMatch = regex.find(str)
            if (on.containsMatchIn(str)) {
                nextOn = on.find(str)?.range?.first ?: str.length
            }
            if (off.containsMatchIn(str)) {
                nextOff = off.find(str)?.range?.first ?: str.length
            }

            if (nextMatch!!.range.first < min(nextOn, nextOff)) {
                if (enabled) {
                    val (a, b) = nextMatch.value.drop(4).dropLast(1).split(",").map { it.toInt() }
                    sum += a * b
                }
                str = str.substring(nextMatch.range.last)
            } else if (nextOn < nextOff) {
                enabled = true
                str = str.substring(nextOn + 4)
            } else {
                enabled = false
                str = str.substring(nextOff + 4)
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
