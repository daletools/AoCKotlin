package aoc2025

import util.*

import Day
import org.apache.commons.math3.util.ArithmeticUtils.pow
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

private class Day03 : Day(2025, 3) {
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
    val lines = input.map { line ->
      line.toCharArray()
        .map { cell ->
          cell.digitToInt()
        }
    }

    var sum = 0L
    val test = mutableListOf<String>()

    for (line in lines) {
      var farthestIndex = 0
      var str = ""
      val len = line.lastIndex
      repeat (12) {
        val section = line.slice(farthestIndex..(len + 1 - (12 - str.length)))
        val max = section.max()
        str += max
        farthestIndex += section.indexOfFirst { it == max } + 1
      }
      test.add(str)
      sum += str.toLong()
    }
    return sum
  }
}

fun main() {
  val day = Day03()
  day.run()
  day.benchmark(1, day::partOne)
  day.benchmark(2, day::partTwo)
}