package aoc2024

import util.println
import util.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        var count : List<MutableList<Boolean>> = List(input.size) { MutableList(input[0].length) { false } }
        var coords = Pair(0,0)
        var facing = '.'
        input.forEachIndexed { i, it ->
            it.forEachIndexed { j, c ->
                if (c != '.' && c != '#') {
                    coords = Pair(i, j)
                    facing = c
                }
            }
        }

        while (coords.first in input.indices && coords.second in input[0].indices) {
            count[coords.first][coords.second] = true

//            for (i in count.indices) {
//                for (j in count.indices) {
//                    if (Pair(i, j) == coords) {
//                        print(facing)
//                    } else if (input[i][j] == '#') {
//                        print('#')
//                    } else if (count[i][j]) {
//                        print('X')
//                    } else {
//                        print('.')
//                    }
//                }
//                println()
//            }
//            println()



            when (facing) {
                '^' -> {
                    if (coords.first > 0 && input[coords.first - 1][coords.second] == '#') {
                        facing = '>'
                    } else {
                        coords = Pair(coords.first - 1, coords.second)
                    }
                }
                '>' -> {
                    if (coords.second < input[0].length && input[coords.first][coords.second + 1] == '#') {
                        facing = 'v'
                    } else {
                        coords = Pair(coords.first, coords.second + 1)
                    }
                }
                'v' -> {
                    if (coords.first < input.size - 1 && input[coords.first + 1][coords.second] == '#') {
                        facing = '<'
                    } else {
                        coords = Pair(coords.first + 1, coords.second)
                    }
                }
                '<' -> {
                    if (coords.second > 0 && input[coords.first][coords.second - 1] == '#') {
                        facing = '^'
                    } else {
                        coords = Pair(coords.first, coords.second - 1)
                    }
                }
            }
        }

        return count.sumOf { row ->
            row.count { it }
        }
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var count : List<MutableList<Char>> = List(input.size) { MutableList(input[0].length) { '.' } }
        var coords = Pair(0,0)
        var facing = '.'
        input.forEachIndexed { i, it ->
            it.forEachIndexed { j, c ->
                if (c != '.' && c != '#') {
                    coords = Pair(i, j)
                    facing = c
                }
            }
        }

        while (coords.first in input.indices && coords.second in input[0].indices) {

            if (count[coords.first][coords.second] != '.') {
                when (facing) {
                    '^' -> {
                        if (coords.second < input[0].length && input[coords.first][coords.second + 1] == '>') {
                            sum++
                        }
                    }
                    '>' -> {
                        if (coords.first < input.size - 1 && input[coords.first + 1][coords.second] == 'v') {
                            sum++
                        }
                    }
                    'v' -> {
                        if (coords.second > 0 && input[coords.first][coords.second - 1] == '<') {
                            sum++
                        }
                    }
                    '<' -> {
                        if (coords.first > 0 && input[coords.first - 1][coords.second] == '^') {
                            sum++
                        }
                    }
                }
            }


            count[coords.first][coords.second] = facing

            for (i in count.indices) {
                for (j in count.indices) {
                    if (Pair(i, j) == coords) {
                        print(facing)
                    } else if (input[i][j] == '#') {
                        print('#')
                    } else if (count[i][j] != '.') {
                        print(count[i][j])
                    } else {
                        print('.')
                    }
                }
                println()
            }
            println()

            when (facing) {
                '^' -> {
                    if (coords.first > 0 && input[coords.first - 1][coords.second] == '#') {
                        facing = '>'
                    } else {
                        coords = Pair(coords.first - 1, coords.second)
                    }
                }
                '>' -> {
                    if (coords.second < input[0].length && input[coords.first][coords.second + 1] == '#') {
                        facing = 'v'
                    } else {
                        coords = Pair(coords.first, coords.second + 1)
                    }
                }
                'v' -> {
                    if (coords.first < input.size - 1 && input[coords.first + 1][coords.second] == '#') {
                        facing = '<'
                    } else {
                        coords = Pair(coords.first + 1, coords.second)
                    }
                }
                '<' -> {
                    if (coords.second > 0 && input[coords.first][coords.second - 1] == '#') {
                        facing = '^'
                    } else {

                        coords = Pair(coords.first, coords.second - 1)
                    }
                }
            }

        }

        return sum
    }

    val input = readInput("Input")
    val test = readInput("Test")

    part1(test).println()
    part1(input).println()
    part2(test).println()
    part2(input).println()
}
