package aoc2015

import Day

private class Day02: Day(2015, 2) {
  override fun partOne(input: List<String>): Long =
    input
      .fold(0L) { acc, meas ->
        val (height, width, length) = meas.split('x').map { it.toLong() }
        acc + 2*length*width + 2*width*height + 2*height*length + minOf(height * width, height * length, width * length)
      }

  override fun partTwo(input: List<String>): Long =
    input
      .fold(0L) { acc, meas ->
        val (height, width, length) = meas.split('x').map { it.toLong() }
        acc + (height * width * length) +
            minOf(
              2 * height + 2 * width,
              2 * height + 2 * length,
              2 * width + 2 * length
              )
      }
}

fun main() {
  val day = Day02()
  day.run()
}