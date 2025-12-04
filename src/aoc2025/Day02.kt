package aoc2025

import Day


class Day02 : Day(2025, 2) {
  override fun partOne(input: List<String>): Long {
    var sum = 0L
    val ranges = input[0]
      .split(',')
      .map { range ->
        val dash = range.indexOf('-')
        range.take(dash).toLong()..range.substring((dash + 1)..range.lastIndex).toLong()
      }
    for (range in ranges) {
      for (num in range) {
        if (num.toString().length % 2 == 1) continue

        val str = num.toString()

        if (str.take(str.length / 2) == str.takeLast(str.length / 2)) sum += num

      }
    }
    return sum
  }

  override fun partTwo(input: List<String>): Long {
    var sum = 0L
    val ranges = input[0]
      .split(',')
      .map { range ->
        val dash = range.indexOf('-')
        range.take(dash).toLong()..range.substring((dash + 1)..range.lastIndex).toLong()
      }

    for (range in ranges) {
      for (num in range) {
        for (len in 1..((num.toString().length) / 2)) {
          val digits = num.toString().length
          if (digits % len != 0) continue
          val parts = num.toString().chunked(len).toSet()
          if (parts.size == 1) {
            sum += num
            break
          }
        }
      }
    }
    return sum
  }
}

fun main() {
  val day = Day02()
  day.benchmark(1, day::partOne)
  day.benchmark(2, day::partTwo)
}