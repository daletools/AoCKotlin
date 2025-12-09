package aoc2015

import Day

private class Day05: Day(2015, 5) {
  override fun partOne(input: List<String>): Long {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    val badStrings = setOf("ab", "cd", "pq", "xy")
    return input.count { line ->
      val pairs = line.zipWithNext()
      var vowelCount = 0
      var hasDouble = false
      pairs.forEachIndexed { i, it ->
        if (it.first in vowels || i == pairs.lastIndex && it.second in vowels) vowelCount++
        if ("${it.first}${it.second}" in badStrings) return@count false
        if (it.first == it.second) hasDouble = true
      }
      return@count hasDouble && vowelCount > 2
    }.toLong()
  }

  override fun partTwo(input: List<String>): Long {
    return input.count { line ->
      val pair = line.windowed(2).withIndex().any { (i, pair) ->
        line.indexOf(pair, i + 2) != -1
      }
      val tris = line.windowed(3).any { it.first() == it.last() }

      pair && tris
    }.toLong()
  }

}

fun main() {
  val day = Day05()
  day.run()
}