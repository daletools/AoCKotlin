package aoc2018

import util.println
import util.readInput

fun main() {
    fun part1(input: List<String>): Int {
        val fabric : List<MutableList<Int>> = List(2000) { MutableList(2000) { 0 } }
        var sum = 0
        input.forEach {
            val line = it.split(" ")
            val loc = line[2].dropLast(1).split(",")
            val size = line[3].split("x")
            for (row in (loc[1].toInt())..<loc[1].toInt() + size[1].toInt()) {
                for (col in (loc[0].toInt())..<(loc[0].toInt() + size[0].toInt())) {
                    fabric[row][col]++
                    if (fabric[row][col] == 2) sum++
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var claims : MutableList<Rect> = mutableListOf()
        input.forEach {
            val line = it.split(" ")
            val loc = line[2].dropLast(1).split(",")
            val size = line[3].split("x")
            claims.add(
                Rect(loc[0].toInt(), loc[1].toInt(), size[0].toInt(), size[1].toInt(), line[0].drop(1).toInt())
            )
        }

        claims.forEach { a ->
            var disjoint : Boolean = true
            claims.forEach { b ->
                if (a != b && a.intersect(b)) {
                    disjoint = false
                }
            }
            if (disjoint) return a.id
        }
        return -1
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
    //part2(test).println()
    part2(input).println()
}

class Rect(val x :Int, val y : Int, val width : Int, val height : Int, val id : Int) {
    fun intersect(other : Rect) : Boolean {
        return if (x > other.x + other.width || other.x > x + width ||
            y > other.y + other.height || other.y  > y + height) {
            false
        } else {
            true
        }
    }
}