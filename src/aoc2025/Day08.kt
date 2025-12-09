package aoc2025

import Day
import kotlin.math.pow
import kotlin.math.sqrt

private class Day08: Day(2025, 8) {
  override fun partOne(input: List<String>): Long {
    val boxes = input.map {
      val (x, y, z) = it.split(',').map { el -> el.toLong() }
      Triple(x, y, z)
    }

    val distances = List(boxes.size) { row ->
      MutableList(boxes.size) { col ->
        if (row == col) {
          Double.MAX_VALUE
        } else {
          boxes[row].distanceTo(boxes[col])
        }
      }
    }

    val circuits = MutableList(input.size) { mutableListOf(it) }
    var connected = 0

    while (connected < 1000) {
      var r = 0
      var c = 0
      var min = Double.MAX_VALUE

      for (row in distances.indices) {
        for (col in distances[row].indices) {
          if (distances[row][col] < min) {
            r = row
            c = col
            min = distances[row][col]
          }
        }
      }
      distances[r][c] = Double.MAX_VALUE
      distances[c][r] = Double.MAX_VALUE
      val cIndex = circuits.indexOfFirst { it.contains(c) }
      val rIndex = circuits.indexOfFirst { it.contains(r) }

      if (cIndex != rIndex) {
        circuits[cIndex].addAll(circuits[rIndex])
        circuits[rIndex].clear()
      }

      connected++
    }

    return circuits.sortedByDescending { it.size }.take(3).fold(1) { acc, circuit -> acc * circuit.size }
  }

  fun Triple<Long, Long, Long>.distanceTo(other: Triple<Long, Long, Long>): Double =
    sqrt(
      (this.first.toDouble() - other.first).pow(2) +
          (this.second.toDouble() - other.second).pow(2) +
          (this.third.toDouble() - other.third).pow(2)
    )


  override fun partTwo(input: List<String>): Long {
    val boxes = input.map {
      val (x, y, z) = it.split(',').map { el -> el.toLong() }
      Triple(x, y, z)
    }

    val distances = List(boxes.size) { row ->
      MutableList(boxes.size) { col ->
        if (row == col) {
          Double.MAX_VALUE
        } else {
          boxes[row].distanceTo(boxes[col])
        }
      }
    }

    val circuits = MutableList(input.size) { mutableListOf(it) }

    var ans = 0L

    while (circuits.filter { it.isNotEmpty() }.size != 1) {
      var r = 0
      var c = 0
      var min = Double.MAX_VALUE

      for (row in distances.indices) {
        for (col in distances[row].indices) {
          if (distances[row][col] < min) {
            r = row
            c = col
            min = distances[row][col]
          }
        }
      }
      distances[r][c] = Double.MAX_VALUE
      distances[c][r] = Double.MAX_VALUE
      val cIndex = circuits.indexOfFirst { it.contains(c) }
      val rIndex = circuits.indexOfFirst { it.contains(r) }

      if (cIndex != rIndex) {
        circuits[cIndex].addAll(circuits[rIndex])
        circuits[rIndex].clear()
      }

      ans = boxes[r].first * boxes[c].first

    }

    return ans
  }

}

fun main() {
  val day = Day08()
  day.run()
}