//package aoc2020
//
//import Day
//import util.println
//
//fun main() = Day02().run()
//
//class Day02: Day(2020, 3) {
//  override fun partOne(): Any {
//    val width = input[0].length
//    val height = input.size
//    var count = 0
//    val pos = intArrayOf(0, 0) //y,x or row,col
//    while (pos[0] < height) {
//      //"${input[pos[0]].substring(0, pos[1])}*${input[pos[0]][pos[1]]}*${input[pos[0]].substring(pos[1] + 1)}".println()
//      if (input[pos[0]][pos[1]] == '#') count++
//      pos[0]++
//      pos[1] = (pos[1] + 3) % (width)
//    }
//    return count
//  }
//
//  override fun partTwo(): Any {
//    var count = slope(1, 1)
//    count *= slope(1, 3)
//    count *= slope(1, 5)
//    count *= slope(1, 7)
//    count *= slope(2, 1)
//    return count
//  }
//
//  fun slope(down: Int, right: Int): Long {
//    val width = input[0].length
//    val height = input.size
//    var count = 0L
//    val pos = intArrayOf(0, 0) //y,x or row,col
//    while (pos[0] < height) {
//      if (input[pos[0]][pos[1]] == '#') count++
//      pos[0] += down
//      pos[1] = (pos[1] + right) % (width)
//    }
//    return count
//  }
//
//
//  val test ="..##.......\n" +
//      "#...#...#..\n" +
//      ".#....#..#.\n" +
//      "..#.#...#.#\n" +
//      ".#...##..#.\n" +
//      "..#.##.....\n" +
//      ".#.#.#....#\n" +
//      ".#........#\n" +
//      "#.##...#...\n" +
//      "#...##....#\n" +
//      ".#..#...#.#\n"
//}
//
