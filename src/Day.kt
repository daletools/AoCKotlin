import util.*

abstract class Day(val year:Int, val day: Int) {
  val input by lazy { getInput(year, day) }

  abstract fun partOne(): Any
  abstract fun partTwo(): Any

  fun run(part: Int = 0) {
    "Advent of Code $year - Day $day".println()
    if (part == 0 || part == 1) {
      "Part One:".println()
      try {
        partOne().println()
      } catch (e: Error) {
        e.println()
      }
    }
    if (part == 0 || part == 2) {
      "Part Two:".println()
      try {
        partTwo().println()
      } catch (e: Error) {
        e.println()
      }
    }
  }
}