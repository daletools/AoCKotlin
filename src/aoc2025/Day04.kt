package aoc2025

import Day
import util.ComplexInt

private class Day04: Day(2025, 4) {
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

  override fun partTwo(input: List<String>): Long {
    val map = mutableListOf<ComplexInt>()
    for (row in input.indices) {
      for (col in input[row].indices) {
        if (input[row][col] == '@') {
          map.add(ComplexInt(row, col))
        }
      }
    }

    var curr = map.size
    var sum = 0
    do {
      curr = map.size
      val copy = map.toList()
      for (point in copy) {
        var n = 0
        for (other in copy) {
          if (point == other) continue
          if (point.isDiagonallyAdjacent(other)) n++
        }
        if (n < 4) {
          sum++
          map.remove(point)
        }
      }
    } while (map.size < curr)

    return sum.toLong()
  }

}

fun main() {
  val day = Day04()
  day.run()
}