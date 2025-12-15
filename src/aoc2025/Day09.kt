package aoc2025

import Day
import util.ComplexInt
import util.println
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private class Day09: Day(2025, 9) {
  override fun partOne(input: List<String>): Long {
    var max = 0L
    for (i in input) {
      for (j in input) {
        if (i == j) continue
        val a = i.split(',').map { it.toLong() }
        val b = j.split(',').map { it.toLong() }
        max = max(max, (abs(a[0] - b[0]) + 1) * (abs(a[1] - b[1]) + 1))
      }
    }

    return max
  }

  override fun partTwo(input: List<String>): Long {
    val coordinates = input.map { line ->
      line.split(',').map { it.toInt() }
    }
    val lines = coordinates.zipWithNext()
    var max = 0L

   for (i in coordinates.indices) {
     val a = coordinates[i]
     for (j in coordinates.indices) {
       val b = coordinates[j]
       if (i == j) continue
       val size = abs((a[0] - b[0]) + 1L) * (abs(a[1] - b[1]) + 1)
       if (size < max) continue
       val upper = listOf(Pair(a[0], a[1]), Pair(a[0], b[1]))
       val right = listOf(Pair(a[0], b[1]), Pair(b[0], b[1]))
       val lower = listOf(Pair(b[0], a[1]), Pair(b[0], b[1]))
       val left = listOf(Pair(a[0], a[1]), Pair(b[0], a[1]))

       var collision = false
       for (line in lines) {
         //line is vertical
         if (line.first[0] == line.second[0]) {
           val x = line.first[0]
           if (x in upper[0].second..upper[1].second) {
             val (y1, y2) = listOf(line.first[1], line.second[1]).sorted()
             if (upper[0].first in (y1 + 1)..<y2 || lower[0].first in (y1 + 1)..<y2) {
               collision = true
               break
             }
           }
           //line is horizontal
         } else {
           val y = line.first[1]
            if (y in right[0].first..right[1].first) {
              val (x1, x2) = listOf(line.first[0], line.second[0])
              if (left[1].first in (x1 + 1)..<x2 || right[1].first in (x1 + 1..<x2)) {
                collision = true
                break
              }
            }
         }
       }
       if (!collision) {
         max = max(max, size)
       }
     }
   }

    return max
  }

}

fun main() {
  val day = Day09()
  day.run()
}