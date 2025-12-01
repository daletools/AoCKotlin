//package aoc2015
//
//import Day
//import util.println
//import kotlin.math.min
//
//fun main() = Day09().run()
//
//class Day09: Day(2015, 9) {
//  override fun partOne(): Any {
//    val locations = sortedSetOf<String>()
//    input.forEach { line ->
//      val locs = line.split(" = ")
//      locs[0].split(" to ").forEach { loc ->
//        locations.add(loc)
//      }
//    }
//    val matrix = Array<IntArray>(locations.size) { IntArray(locations.size) { Int.MAX_VALUE } }
//    input.forEach { line ->
//      val parse = line.split(" = ")
//      val locs = parse[0].split(" to ")
//      val locA = locations.indexOf(locs[0])
//      val locB = locations.indexOf(locs[1])
//      matrix[locA][locB] = parse[1].toInt()
//      matrix[locB][locA] = parse[1].toInt()
//    }
//    var minweight = Int.MAX_VALUE
//    val visited = BooleanArray(locations.size) { false }
//    val currentPath = mutableListOf<Int>()
//    var bestPath = listOf<Int>()
//
//    fun backtrack(currWeight: Int) {
//      if (currentPath.size == locations.size) {
//        if (currWeight < minweight) {
//          minweight = currWeight
//          bestPath = currentPath.toList()
//          return
//        }
//      }
//
//      val lastLoc = currentPath.last()
//
//    }
//
//    return minweight
//  }
//
//  override fun partTwo(): Any {
//    TODO("Not yet implemented")
//  }
//
//  override fun partOne(input: List<String>): Any {
//    TODO("Not yet implemented")
//  }
//
//  override fun partTwo(input: List<String>): Any {
//    TODO("Not yet implemented")
//  }
//
//}