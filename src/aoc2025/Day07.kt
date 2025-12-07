package aoc2025

import Day
import util.ComplexInt
import util.ComplexInt.Direction

private class Day07: Day(2025, 7) {
  override fun partOne(input: List<String>): Long {
    val emitter = input[0].indexOf('S')
    val beams = mutableSetOf<Int>(emitter)
    var sum = 0L

    input.forEach { line ->
      if (line.contains('^')) {
        val splitters = mutableSetOf<Int>()
        for (i in line.indices)
          if (line[i] == '^') splitters.add(i)

        val collisions = beams.intersect(splitters)
        sum += collisions.size
        beams.removeAll(collisions)
        for (collision in collisions) {
          beams.add(collision - 1)
          beams.add(collision + 1)
        }
      }
    }

    return sum
  }

  override fun partTwo(input: List<String>): Long {
    val emitter = input[0].indexOf('S')
    val beams = mutableMapOf(emitter to 1L)

    input.forEach { line ->
      if (line.contains('^')) {
        val splitters = mutableSetOf<Int>()
        for (i in line.indices)
          if (line[i] == '^') splitters.add(i)

        val collisions = beams.keys.intersect(splitters)
        val temp = mutableMapOf<Int, Long>()
        for (collision in collisions) {
          val count = beams[collision]
          beams[collision] = 0
          temp[collision] = count ?: 0L
        }
        temp.forEach { (key, value) ->
          val currL = beams[key - 1] ?: 0
          val currR = beams[key + 1] ?: 0
          beams[key - 1] = currL + value
          beams[key + 1] = currR + value
        }
      }
    }

    return beams.values.sum()
  }

}

fun main() {
  val day = Day07()
  day.run()
}