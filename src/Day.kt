import util.*
import java.io.File
import java.nio.file.Paths

abstract class Day(val year:Int, val day: Int) {
  val input: List<String> by lazy { getInput(year, day) }


  abstract fun partOne(input: List<String> = this.input): Any
  abstract fun partTwo(input: List<String> = this.input): Any
  fun partOne() {
    partOne(this.input)
  }

  fun run(part: Int = 0) {
    "Advent of Code $year - Day $day".println()
    val testFilePath = File(
      Paths.get("inputs", year.toString())
        .resolve(
          "day${day.toString().padStart(2, '0')}_test.txt")
        .toString()
    )
    val testData = if (testFilePath.exists()) testFilePath
      .readLines()
      .map { it.trim() }
    else listOf()
    if (part == 0 || part == 1) {
      "Part One:".println()
      try {
        if (testData.isNotEmpty()) {
          "Test:".println()
          partOne(testData).println()
        }
        partOne(input).println()
      } catch (e: Error) {
        e.println()
      }
    }
    if (part == 0 || part == 2) {
      "Part Two:".println()
      try {
        if (testData.isNotEmpty()) {
          "Test:".println()
          partTwo(testData).println()
        }
        partTwo(input).println()
      } catch (e: Error) {
        e.println()
      }
    }
  }
}