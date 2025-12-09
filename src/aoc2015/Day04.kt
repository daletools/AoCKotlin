package aoc2015

import Day
import util.md5

private class Day04: Day(2015, 4) {
  override fun partOne(input: List<String>): Long {
    var num = 1
    var hash = (input[0] + num).md5()

    while (hash.take(5) != "00000") {
      num++
      hash = (input[0] + num).md5()
    }
    return num.toLong()
  }

  override fun partTwo(input: List<String>): Long {
    var num = 1
    var hash = (input[0] + num).md5()

    while (hash.take(6) != "000000") {
      num++
      hash = (input[0] + num).md5()
    }
    return num.toLong()
  }

}

fun main() {
  val day = Day04()
  day.run()
}