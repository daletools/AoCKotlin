package aoc2015

import Day

private class Day01: Day(2015, 1) {
  override fun partOne(input: List<String>): Long =
    input[0].sumOf { if (it == '(') 1L else -1L }

  override fun partTwo(input: List<String>): Long =
     input[0]
      .map { if (it == '(') 1 else -1 }
      .runningFold(0) { acc, el ->
        acc + el
      }
      .indexOfFirst { it == -1 }
      .toLong()
}

fun main() {
  val day = Day01()
  day.run()
}