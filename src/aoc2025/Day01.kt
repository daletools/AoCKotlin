package aoc2025

import Day
import util.println
import kotlin.enums.enumEntries
import kotlin.math.abs
import kotlin.math.max

private class Day01 : Day(2025, 1) {
  override fun partOne(input: List<String>) =
    input
      .map {
        if (it.startsWith('R')) {
          it.drop(1).toInt()
        } else {
          it.drop(1).toInt() * -1
        }
      }.runningFold(50) { acc, turn ->
        (acc + turn) % 100
      }.count {
        it == 0
      }.toLong()

  override fun partTwo(input: List<String>): Long {
    var count = 0
    var num = 50
    input.forEach {
      val turn = it.drop(1).toInt()
      if (it.startsWith('L')) {
        repeat(turn) {
          num--
          if (num == 0) count++
          if (num < 0) num = 99
        }
      } else {
        repeat(turn) {
          num++
          if (num > 99) num = 0
          if (num == 0) count++
        }
      }
    }
    return count.toLong()
  }

  fun partTwo2(input: List<String>) =
    input
      .map {
        if (it.startsWith('R')) {
          it.drop(1).toInt()
        } else {
          it.drop(1).toInt() * -1
        }
      }.runningFold(50) { acc, turn ->
        (acc % 100) + turn
      }
}

fun main() {
  Day01().run()
  Day01().partTwo2(Day01().input).println()
}