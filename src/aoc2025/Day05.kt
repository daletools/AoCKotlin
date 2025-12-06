package aoc2025

import Day
import java.math.BigDecimal
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
    val ranges = input.slice(0..<input.indexOf("")).map {
      val (start, end) = it.split('-')
      Pair(BigInteger(start), BigInteger(end))
    }.sortedBy { it.first }
    val max = ranges.maxBy { it.second }.second
    println("max is $max")
    var sum = 0L
    for (i in 0..ranges.maxBy { it.second }.second.toLong()) {
      for (range in ranges) {
        if (BigInteger(i.toString()) in range.first..range.second) {
          sum++
          break
        }
      }
      if (i % 1000000 == 0L) {
        println("passed $i")
      }
    }

    return sum
  }

}

fun main() {
  val day = Day05()

  day.run()
}