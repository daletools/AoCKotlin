import kotlinx.benchmark.*
import util.*
import java.io.File
import java.nio.file.Paths
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

abstract class Day(val year:Int, val day: Int) {
  val input: List<String> by lazy { getInput(year, day) }

  abstract fun partOne(input: List<String> = this.input): Long
  abstract fun partTwo(input: List<String> = this.input): Long

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
    else listOf()
    if (part == 0 || part == 1) {
      "Part One:".println()
      try {
        if (testData.isNotEmpty()) {
          "Test:".println()
          val ms = measureTimeMillis {
            partOne(testData).println()
          }
          println("calculated in $ms ms")
        }
        val ms = measureTimeMillis {
          partOne(input).println()
        }
        println("calculated in $ms ms")
      } catch (e: Error) {
        e.println()
      }
    }
    if (part == 0 || part == 2) {
      "Part Two:".println()
      try {
        if (testData.isNotEmpty()) {
          "Test:".println()
          val ms = measureTimeMillis {
            partTwo(testData).println()
          }
          println("calculated in $ms ms")
        }
        val ms = measureTimeMillis {
          partTwo(input).println()
        }
        println("calculated in $ms ms")
      } catch (e: Error) {
        e.println()
      }
    }
  }

  fun benchmark(part: Int, function: (List<String>) -> Long) {
    val data = getInput(year, day)
    var spinner: Long = System.currentTimeMillis()

    repeat(100) { spinner += function(data) xor spinner }
    println("Warmed up!")
    val times = mutableListOf<Long>()

    repeat(5) {
      System.gc()
      Thread.sleep(10)
    }

    repeat(1000) {
    val time = measureNanoTime {
      spinner += function(data) xor spinner
    }
    times.add(time)
    }

    println("Spinner $spinner")

    val totalMs = times.sum() / 1_000_000.0
    val averageMs = times.average() / 1_000_000.0
    val medianMs = times.sorted()[times.size / 2] / 1_000_000.0

    println("Part $part Benchmark:")
    println("  Total time: ${"%.3f".format(totalMs)} ms")
    println("  Average:    ${"%.3f".format(averageMs)} ms")
    println("  Median:     ${"%.3f".format(medianMs)} ms")
  }
}