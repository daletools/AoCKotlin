package aoc2022

import Day
import util.println
import java.util.PriorityQueue
import kotlin.math.max

private class Day01: Day(2022, 1) {
  override fun partOne(): Any {
    var max = 0L
    var curr = 0L
    input.forEach { line ->
      if (line.isEmpty()) {
        max = max(max, curr)
        curr = 0
      } else {
        curr += line.toLong()
      }
    }
    return max(max, curr)
  }

  override fun partTwo(): Any {
    val elves = PriorityQueue<Long>(Comparator.reverseOrder())
    var curr = 0L
    input.forEach { line ->
      if (line.isEmpty()) {
        elves.add(curr)
        curr = 0
      } else {
        curr += line.toLong()
      }
    }
    return elves.poll() + elves.poll() + elves.poll()
  }

}

fun main() {
  Day01().run()
}