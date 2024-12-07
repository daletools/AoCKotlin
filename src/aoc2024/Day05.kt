package aoc2024

import util.println
import util.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        var rules = mutableListOf<Pair<Int, Int>>()
        var updates = mutableListOf<List<Int>>()

        input.forEach { line ->
            if (line.contains('|')) {
                val (a, b) = line.split('|').map { it.toInt() }
                rules.add(Pair(a, b))
            } else if (line.contains(',')) {
                val lineSplit = line.split(',').map { it.toInt() }
                updates.add(lineSplit)
            }
        }

        updates.forEach {update ->
            val checked = mutableListOf<Int>()
            var valid = true
            update.forEachIndexed { i, page ->
                val predecessors : List<Int> = rules.filter { it.second == page }.unzip().first
                val sub = update.slice(i + 1..update.lastIndex)
                sub.forEach {
                    if (predecessors.contains(it)) {
                        valid = false
                    }
                }
            }
            if (valid) {
                sum += update[update.size / 2]
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        var rules = mutableListOf<Pair<Int, Int>>()
        var updates = mutableListOf<List<Int>>()

        input.forEach { line ->
            if (line.contains('|')) {
                val (a, b) = line.split('|').map { it.toInt() }
                rules.add(Pair(a, b))
            } else if (line.contains(',')) {
                val lineSplit = line.split(',').map { it.toInt() }
                updates.add(lineSplit)
            }
        }

        updates.forEach {update ->
            var valid = true
            update.forEachIndexed { i, page ->
                val predecessors : List<Int> = rules.filter { it.second == page }.unzip().first
                val sub = update.slice(i + 1..update.lastIndex)
                sub.forEach {
                    if (predecessors.contains(it)) {
                        valid = false
                    }
                }
            }
            if (!valid) {
                val book = mutableListOf<Int>()
                val ruleSet : MutableList<Pair<Int, Int>> = rules.filter {
                    (it.first in update && it.second in update)
                }.toMutableList()

                while (book.size < update.size) {
                    book.add(update.filter {
                        it !in ruleSet.unzip().second && it !in book
                    }[0])
                    ruleSet.removeAll { book.last() == it.first }
                }



                sum += book[book.size / 2]
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
