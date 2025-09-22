import util.*

abstract class Day(val year:Int, val day: Int) {
  val input by lazy { getInput(year, day) }

  abstract fun partOne(): Any
  abstract fun partTwo(): Any

  fun run() {
    "Advent of Code $year - Day $day".println()
    "Part One:".println()
    partOne().println()
    "Part Two:".println()
    partTwo().println()
  }
}