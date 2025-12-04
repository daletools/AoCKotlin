package aoc2025
import util.*

import Day
import kotlin.math.min

private class Day03: Day(2025, 3) {
  override fun partOne(input: List<String>): Long {
    return input.map { line ->
      line.toCharArray()
        .map { cell ->
          cell.digitToInt()
        }
    }.sumOf { line ->
      line.foldIndexed(0) { index, acc, digit ->
        if (acc < 10) {
          digit * 10 + acc
        } else if (index < line.lastIndex && digit > acc / 10) {
          digit * 10
        } else if (digit > acc % 10) {
          (acc / 10 * 10) + digit
        } else {
          acc
        }
      }.toLong()
    }
  }

  override fun partTwo(input: List<String>): Long {
    return input.map { line ->
      line.toCharArray()
        .map { cell ->
          cell.digitToInt()
        }
    }.sumOf { line ->
      line.foldIndexed(100_000_000_000L) { index, acc, digit ->
        val maxRemainingDigits = min(line.size - index, 12)

        if (acc % 10_000_000_000 < digit) {
          (digit * 100_000_000_000) + (acc / 10_000_000_000 * 10_000_000_000)
        } else if (acc.toString().contains('0')) {
          acc.toString()
            .replaceFirst('0', digit.toChar())
            .toLong()
        } else if (index < line.lastIndex && digit > acc / 10) {
          digit * 10L
        } else if (digit > acc % 10) {
          (acc / 10 * 10) + digit
        } else {
          acc
        }
      }
    }
  }

}

fun main() {
  val day = Day03()
  day.run()
}