package aoc2020

import Day

fun main() = Day01().run()

class Day01: Day(2020, 2) {
  override fun partOne(): Any {
    var count = 0
    input.forEach { line->
      val (policy, pass) = line.split(": ")
      val (range, char) = policy.split(" ")
      val polRange: IntRange = IntRange(
        range.substringBefore("-").toInt(),
        range.substringAfter("-").toInt()
      )
      if (pass.count { it == char[0] } in polRange) count++
    }
    return count
  }

  override fun partTwo(): Any {
    var count = 0
    input.forEach { line->
      val (policy, pass) = line.split(": ")
      val (range, char) = policy.split(" ")
      val (first, second) = range.split("-").map { it.toInt() }
      if ((pass[first - 1] == char[0]).xor((pass[second - 1] == char[0]))) count++
    }
    return count
  }

}