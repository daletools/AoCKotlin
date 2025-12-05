package aoc2025

import Day
import util.*
import java.math.BigInteger
import kotlin.math.min
import kotlin.math.max

private class Day05Lpt : Day(2025, 5) {
  override fun partOne(input: List<String>): Long {
    TODO("Not yet implemented")
  }

  override fun partTwo(input: List<String>): Long {
    return input.slice(0..<input.indexOf("")).map {
      val (first, last) = it.split('-')
      Pair(BigInteger(first), BigInteger(last))
    }.sortedByDescending { it.second - it.first }.fold(
      mutableListOf<Pair<BigInteger, BigInteger>>()
    ) { acc, el: Pair<BigInteger, BigInteger> ->
      var tempEl = el
      var collision = false
      for (range in acc) {
        if (tempEl.first > range.second || tempEl.second < range.first) continue
        if (tempEl.first >= range.first && tempEl.second <= range.second) {
          collision = true
          break
        }
        val a1 = tempEl.first.toLong()
        val a2 = tempEl.second.toLong()
        val b1 = range.first.toLong() - 1
        val b2 = range.second.toLong() + 1

        if (a1 > b1) {
          tempEl = Pair(
            b2.toBigInteger(), a2.toBigInteger()
          )
        } else if (a2 < b2) {
          tempEl = Pair(
            a1.toBigInteger(), b1.toBigInteger()
          )
        }
      }
      if (!collision) {
        acc.add(tempEl)
      }
      acc
    }.sumOf { (it.second - it.first).toLong() + 1 }
  }
}

fun main() {
  val day = Day05Lpt()
  day.benchmark(2, day::partTwo)
}