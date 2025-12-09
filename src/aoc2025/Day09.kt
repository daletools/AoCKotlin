package aoc2025

import Day
import util.ComplexInt
import util.println
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private class Day09: Day(2025, 9) {
  override fun partOne(input: List<String>): Long {
    var max = 0L
    for (i in input) {
      for (j in input) {
        if (i == j) continue
        val a = i.split(',').map { it.toLong() }
        val b = j.split(',').map { it.toLong() }
        max = max(max, (abs(a[0] - b[0]) + 1) * (abs(a[1] - b[1]) + 1))
      }
    }

    return max
  }

  override fun partTwo(input: List<String>): Long {
    val coordinates = input.map { line ->
      line.split(',').map { it.toInt() }
    }

    var max = 0L
    var candidate1: List<Int> = listOf()
    var candidate2: List<Int> = listOf()

    for (i in coordinates.indices) {
      val a = coordinates[i]
      for (j in coordinates.indices) {
        if (i == j) continue
        val b = coordinates[j]
        val size = (abs(a[0] - b[0]) + 1) * (abs(a[1] - b[1]) + 1)
        if (size < max) continue
        val (minY, maxY) = listOf(a[0], b[0]).sorted()
        val (minX, maxX) = listOf(a[1], b[1]).sorted()

        val interference = coordinates.any {
          val c = it[0] in minY+1..maxY-1
          val d = it[1] in minX+1..maxX-1

          it.toString() != a.toString() &&
          it.toString() != b.toString() &&
          it[0] in minY+1..maxY-1 &&
          it[1] in minX+1..maxX-1 &&
          i % 2 == j % 2
        }

        if (!interference) {
          max = max(max, size.toLong())
          candidate1 = a
          candidate2 = b
        }
      }
    }

    candidate1.toString().println()
    candidate2.toString().println()

    return max
  }

}

fun main() {
  val day = Day09()
  day.run()
}