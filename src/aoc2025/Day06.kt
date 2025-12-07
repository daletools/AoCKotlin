package aoc2025

import Day

private class Day06: Day(2025, 6) {
  override fun partOne(input: List<String>): Long {
    val lists = input.map { it.trim().split("\\s+".toRegex()) }
    var sum = 0L

    for (col in lists[0].indices) {
      var acc = 0L
      for (row in 0..<lists.lastIndex) {
        if (row == 0) {
          acc = lists[0][col].toLong()
          continue
        }
        val operator = lists[lists.lastIndex][col]
        val term = lists[row][col].toLong()
        when (operator) {
          "*" -> acc *= term
          "+" -> acc += term
        }
      }
      sum += acc
    }

    return sum
  }

  override fun partTwo(input: List<String>): Long {
    val inputF = input.map { it.padEnd(input.maxOf { it.length }) }
    var sum = 0L
    val terms = mutableListOf<Long>()
    for (col in inputF[0].indices.reversed()) {
      val num = mutableListOf<Char>()
      var operator = '0'
      for (row in inputF.indices) {
        val cell = inputF[row][col]
        if (cell == ' ') continue
        if (cell.isDigit()) {
          num.add(cell)
        } else {
          operator = cell
        }
      }
      if (num.isEmpty()) continue
      terms.add(num.joinToString("").toLong())
      if (operator == '0') continue
      var temp = terms.removeLast()
      while (terms.isNotEmpty()) {
        when (operator) {
          '+' -> temp += terms.removeLast()
          '*' -> temp *= terms.removeLast()
        }
      }
      sum += temp
    }

    return sum
  }

}

fun main() {
  val day = Day06()
  day.run()
}