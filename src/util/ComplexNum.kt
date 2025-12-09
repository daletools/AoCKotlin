package util

import kotlin.math.abs
import kotlin.math.max

data class ComplexInt(var real: Int, var complex: Int = 0) {

  val x get() = real
  val y get() = complex
  enum class Direction(val vector: ComplexInt) {
    NORTH(ComplexInt(0, -1)),
    EAST(ComplexInt(1, 0)),
    SOUTH(ComplexInt(0, 1)),
    WEST(ComplexInt(-1, 0)),
    NORTHEAST(ComplexInt(1, -1)),
    SOUTHEAST(ComplexInt(1, 1)),
    SOUTHWEST(ComplexInt(-1, 1)),
    NORTHWEST(ComplexInt(-1, -1));
  }

  operator fun plus(other: ComplexInt) = ComplexInt(real + other.real, complex + other.complex)

  operator fun minus(other: ComplexInt): ComplexInt = ComplexInt(real - other.real, complex - other.complex)

  operator fun times(other: ComplexInt) = ComplexInt(
    (real * other.real) - (complex * other.complex),
    (real * other.complex) + (complex * other.real)
  )

  operator fun times(scalar: Int) = ComplexInt(real * scalar, complex * scalar)

  fun shift(direction: Direction, magnitude: Int = 1) = this + (direction.vector * magnitude)

  fun shiftThis(direction: Direction, magnitude: Int = 1) {
    val new = shift(direction, magnitude)
    this.real = new.x
    this.complex = new.y
  }

  fun manhattanDistance(other: ComplexInt) = abs(real - other.real) + abs(complex - other.complex)
  fun isAdjacent(other: ComplexInt) = manhattanDistance(other) == 1
  fun chebyshevDistance(other: ComplexInt) = max(abs(real - other.real), abs(complex - other.complex))
  fun isDiagonallyAdjacent(other: ComplexInt) = chebyshevDistance(other) == 1

  fun inBounds(minX: Int, maxX: Int, minY: Int, maxY: Int) = real in minX..maxX && complex in minY..maxY

  fun wrapped(minX: Int, maxX: Int, minY: Int, maxY: Int): ComplexInt {
    val width = maxX - minX + 1
    val height = maxY - minY + 1
    return ComplexInt(
      ((real - minX).mod(width)) + minX,
      ((complex - minY).mod(height)) + minY
    )
  }

  fun rectangleArea(other: ComplexInt): Int = (abs(y - other.y) + 1) * ((x - other.x) + 1)
}