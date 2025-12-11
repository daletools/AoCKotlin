package aoc2025

import Day
import util.println
import kotlin.math.pow

private class Day10: Day(2025, 10) {

  class Machine(
    val lights: String,
    val buttons: List<List<Int>>,
    val joltage: List<Int>
  )

  override fun partOne(input: List<String>): Long {
    val machines = input.map { line ->
      val segments = line.split(" ")
      val lights = segments.first().drop(1).dropLast(1).map { if (it == '#') 1 else 0 }.joinToString("")
      val buttons = segments.drop(1).dropLast(1).map { it.drop(1).dropLast(1).split(',').map { each -> each.toInt() } }
      val joltage = segments.last().drop(1).dropLast(1).split(',').map { it.toInt() }
      Machine(lights, buttons, joltage)
    }

    var sum = 0L

    for (machine in machines) {
      var min = Int.MAX_VALUE
      val combinations = 2.0.pow(machine.buttons.size).toInt()
      for (sequence in 0..combinations) {
        val state = MutableList(machine.lights.length) { 0 }
        var pressed = 0
        val toPress = sequence.toString(2).padStart(machine.buttons.size, '0')
        for (switch in toPress.indices) {
          if (toPress[switch] == '1') {
            machine.buttons[switch].forEach { i ->
              state[i] = if (state[i] == 0) 1 else 0
            }
            pressed++
          }
        }
        if (state.joinToString("") == machine.lights) {
          if (pressed < min) min = pressed
        }
      }
      sum += min
    }

    return sum
  }

  override fun partTwo(input: List<String>): Long {
    val machines = input.map { line ->
      val segments = line.split(" ")
      val lights = segments.first().drop(1).dropLast(1).map { if (it == '#') 1 else 0 }.joinToString("")
      val buttons = segments.drop(1).dropLast(1).map { it.drop(1).dropLast(1).split(',').map { each -> each.toInt() } }
      val joltage = segments.last().drop(1).dropLast(1).split(',').map { it.toInt() }
      Machine(lights, buttons, joltage)
    }
    "Running ${machines.size} machines!".println()
    var sum = 0L
    var count = 0
    for (machine in machines) {
      "Running machine $count".println()
      count++
      val targetJoltage = machine.joltage.joinToString(",")
      var min = Int.MAX_VALUE
      val mask = MutableList(machine.buttons.size) { 0 }
      val maxPresses = machine.joltage.max()
      while (mask.last() <= maxPresses) {
        val state = MutableList(machine.joltage.size) { 0 }
        mask[0]++
        for (i in mask.indices) {
          if (mask[i] > maxPresses) {
            if (i != mask.lastIndex) {
              mask[i] = 0
              mask[i + 1]++
            }
          }
        }
        for (i in mask.indices) {
          val presses = mask[i]
          val button = machine.buttons[i]
          button.forEach { value ->
            state[value] += presses
          }
        }
        val finalState = state.joinToString(",")
        if (mask.joinToString(",") == "1,3,0,3,1,1") {
          "pause".println()
        }
        if (finalState == targetJoltage) {
          if (mask.sum() < min) min = mask.sum()
        }
      }

      sum += min
    }

    return sum
  }



}

fun main() {
  val day = Day10()
  day.run()
}