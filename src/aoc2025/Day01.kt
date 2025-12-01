package aoc2025

import Day
import kotlin.enums.enumEntries

private class Day01 : Day(2025, 1) {
  fun partOne2(input: List<String>): Any {
    var num = 50
    var count = 0
    input.forEach {
      val turn = it.drop(1).toInt()
      if (it.startsWith('L')) {
        num -= turn % 100
      } else {
        num += turn % 100
      }
      if (num < 0) num += 100
      if (num > 99) num -= 100
      if (num == 0) count++
    }
    return count
  }

  override fun partOne(input: List<String>): Any {
    return input
      .map {
        if (it.startsWith('R')) {
          it.drop(1).toInt()
        } else {
          it.drop(1).toInt() * -1
        }
      }
      .fold(Pair(0, 50)) { (acc, state), turn ->
        val nextState = state + (turn % 100)
        val finalState = if (nextState in 0..99) {
          nextState
        } else {
          (nextState + 100) % 100
        }
        val newAcc = if (finalState == 0) acc + 1 else acc
        Pair(newAcc, finalState)
      }
      .first
  }

  override fun partTwo(input: List<String>): Any {
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
    return count
  }
}

fun main() = Day01().run()