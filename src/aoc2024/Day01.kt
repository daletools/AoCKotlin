package aoc2024
import util.*
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var listA : MutableList<Int> = mutableListOf()
        var listB : MutableList<Int> = mutableListOf()
        var sum = 0
        input.forEach {
            var split = it.split(" ")
            listA.add(split[0].toInt())
            listB.add(split[3].toInt())
        }

        listA.sort()
        listB.sort()

        for (i in listA.indices) {
            sum += abs(listA[i] - listB[i])
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var listA : MutableList<Int> = mutableListOf()
        var listB : MutableList<Int> = mutableListOf()
        var sum = 0
        input.forEach {
            var split = it.split(" ")
            listA.add(split[0].toInt())
            listB.add(split[3].toInt())
        }

        listA.sort()
        listB.sort()

        for (i in listA.indices) {
            sum += listA[i] * listB.count { it == listA[i] }
        }

        return sum
    }

    val input = readInput("Input")
    val start : Long = System.nanoTime()
    part1(input).println()
    val mid : Long = System.nanoTime()
    part2(input).println()
    val end : Long = System.nanoTime()

    """Part 1 took ${(mid - start) / 1000000} ms""".println()
    """Part 2 took ${(end - mid) / 1000000} ms""".println()
}
