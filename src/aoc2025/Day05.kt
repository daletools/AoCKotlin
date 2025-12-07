package aoc2025

import Day
import java.math.BigInteger

private class Day05: Day(2025, 5) {
  override fun partOne(input: List<String>): Long {
    val ranges = input.slice(0..<input.indexOf("")).map {
      val (start, end) = it.split('-')
      Pair(BigInteger(start), BigInteger(end))
    }
    val ingredients = input.slice(input.indexOf("")+1..input.lastIndex)

    var sum = 0L

    for (ingredient in ingredients) {
      val ing = BigInteger(ingredient)
      for (range in ranges) {
        if (ing >= range.first && ing <= range.second) {
          sum++
          break
        }
      }
    }
    return sum
  }

  override fun partTwo(input: List<String>): Long {
    return input.slice(0..<input.indexOf("")).map {
      val (first, last) = it.split('-')
      Pair(first.toLong(), last.toLong())
    }.sortedByDescending { it.second - it.first }.fold(
      mutableListOf<Pair<Long, Long>>()
    ) { acc, el: Pair<Long, Long> ->
      var tempEl = el
      var collision = false
      for (range in acc) {
        if (tempEl.first > range.second || tempEl.second < range.first) continue
        if (tempEl.first >= range.first && tempEl.second <= range.second) {
          collision = true
          break
        }
        val a1 = tempEl.first
        val a2 = tempEl.second
        val b1 = range.first - 1
        val b2 = range.second + 1

        if (a1 > b1) {
          tempEl = Pair(
            b2, a2
          )
        } else if (a2 < b2) {
          tempEl = Pair(
            a1, b1
          )
        }
      }
      if (!collision) {
        acc.add(tempEl)
      }
      acc
    }.sumOf { (it.second - it.first) + 1 }
  }
}

fun main() {
  val day = Day05()

  day.benchmark(1, day::partOne)
  day.benchmark(2, day::partTwo)
}