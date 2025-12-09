package aoc2015

import Day

private class Day03: Day(2015, 3) {
  override fun partOne(input: List<String>): Long {
    var x = 0
    var y = 0
    val houses = mutableSetOf<Pair<Int, Int>>(Pair(0, 0))
    for (dir in input[0]) {
      when (dir) {
        '^' -> y--
        'v' -> y++
        '<' -> x--
        '>' -> x++
      }
      houses.add(Pair(x, y))
    }
    return houses.size.toLong()
  }

  override fun partTwo(input: List<String>): Long {
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0
    val houses = mutableSetOf<Pair<Int, Int>>(Pair(0, 0))
    for (i in input[0].indices) {
      val dir = input[0][i]
      when (dir) {
        '^' -> if (i % 2 == 0) y1-- else y2--
        'v' -> if (i % 2 == 0) y1++ else y2++
        '<' -> if (i % 2 == 0) x1-- else x2--
        '>' -> if (i % 2 == 0) x1++ else x2++
      }
      houses.add(if (i % 2 == 0) Pair(x1, y1) else Pair(x2, y2))
    }
    return houses.size.toLong()
  }

}

fun main() {
  val day = Day03()
  day.run()
}