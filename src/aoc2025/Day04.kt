package aoc2025

import Day
import util.ComplexInt

private class Day04 : Day(2025, 4) {
  override fun partOne(input: List<String>): Long {
    val map = mutableListOf<ComplexInt>()
    for (row in input.indices) {
      for (col in input[row].indices) {
        if (input[row][col] == '@') {
          map.add(ComplexInt(row, col))
        }
      }
    }
    var sum = 0

    for (point in map) {
      var n = 0
      for (other in map) {
        if (point == other) continue
        if (point.isDiagonallyAdjacent(other)) n++
      }
      if (n < 4) sum++
    }
    return sum.toLong()
  }

  override fun partTwo(input: List<String>): Long =
    input.mapIndexed { row, line ->
      line.mapIndexed { col, cell ->
        if (cell != '@') {
          false
        } else {
          listOf(
            input.getOrNull(row - 1)?.getOrNull(col - 1) == '@',
            input.getOrNull(row - 1)?.getOrNull(col) == '@',
            input.getOrNull(row - 1)?.getOrNull(col + 1) == '@',
            input.getOrNull(row)?.getOrNull(col - 1) == '@',
            input.getOrNull(row)?.getOrNull(col + 1) == '@',
            input.getOrNull(row + 1)?.getOrNull(col - 1) == '@',
            input.getOrNull(row + 1)?.getOrNull(col) == '@',
            input.getOrNull(row + 1)?.getOrNull(col + 1) == '@',
          ).count { it } < 4
        }
      }
    }
      .flatten()
      .count { it }
      .toLong()
}

fun main() {
  val day = Day04()
  day.run()
}