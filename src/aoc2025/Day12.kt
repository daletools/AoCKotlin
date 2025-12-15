package aoc2025

import Day

private class Day12: Day(2025, 12) {
  override fun partOne(input: List<String>): Long {
    val separator = input.indexOfLast { it == "" }
    val blocks = input.slice(0 until separator)
    val grids = input
      .slice(separator + 1 until input.size)
      .map { line ->
        val (def, reqs) = line.split(": ")
        val dims = def.split("x").map { it.toInt() }
        Pair(dims[0], dims[1]) to reqs.split(" ").map { it.toInt() }
      }

    return grids.count { grid ->
      val numBlocks = grid.second.sum()
      val blocksPerRow = grid.first.first / 3
      val numRows = grid.first.second / 3
      numBlocks <= blocksPerRow * numRows
    }.toLong()
  }

  override fun partTwo(input: List<String>): Long {
    TODO("Not yet implemented")
  }

}

fun main() {
  val day = Day12()
  day.run()
}