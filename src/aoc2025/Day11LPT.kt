package aoc2025

import Day

private class Day11LPT: Day(2025, 11) {
  override fun partOne(input: List<String>): Long {
    return 0L
  }

  override fun partTwo(input: List<String>): Long {
    val devices = input.associate { line ->
      val (intake, output) = line.split(": ")
      intake to output.split(" ").toMutableSet()
    }.toMutableMap()

    while (devices.values.any { it.size == 1 }) {
      val singles = devices.keys.filter { devices[it]?.size == 1 }
      singles.forEach { key ->
        val end = devices[key]?.first()
        devices.remove(key)
        for (other in devices.keys) {
          if (key == other) continue
          if (devices[other]?.contains(key) ?: false) {
            devices[other]?.remove(key)
            devices[other]?.add(end ?: "BREAK")
          }
        }
      }
    }

    return 0L
  }

}

fun main() {
  val day = Day11LPT()
  day.run()
}