package main.kotlin.e_sunny_with_a_chance_of_asteroids

import java.io.File

const val FILENAME = "src/main/kotlin/e_sunny_with_a_chance_of_asteroids/input.txt"
const val instruction = 1

data class Code(val code: ArrayList<Int>)

fun getList(): ArrayList<Int> {
    var input = ""
    File(FILENAME).forEachLine { input = it }
    var l = input.split(",").toList().map { it.toInt() }.toList()
    return ArrayList(l)
}

fun getCode(code: Int): ArrayList<Int> {
    var params: ArrayList<Int> = ArrayList(3)
    params.add((code / 100) % 10)
    params.add(code / 1000)
    params.add(0) // always position
    return params
}

fun instruction(): Int {
    var instructions = getList()
    var s = 0
    var output = -1
    var first = true
    for (n in instructions) {
        var indx = 0
        if (!first) {
            indx = s
        }

        if (indx > instructions.size) {
            break
        }

        var code = getCode(instructions[indx])

        when (instructions[indx] % 100) {
            1 -> {
                instructions[instructions[indx + 3]] =
                    getValue(instructions, instructions[indx + 1], 0, code) +
                            getValue(instructions, instructions[indx + 2], 1, code)
                s += 4
            }
            2 -> {
                instructions[instructions[indx + 3]] =
                    getValue(instructions, instructions[indx + 1], 0, code) *
                            getValue(instructions, instructions[indx + 2], 1, code)
                s += 4
            }
            3 -> {
                instructions[instructions[indx + 1]] = instruction
                s += 2
            }
            4 -> {
                output = getValue(instructions, instructions[indx + 1], 0, code)
                s += 2
            }
            else -> {
                output = -1
                s += instructions.size
            }
        }
        first = false

    }
    return output
}

fun getValue(instructions: ArrayList<Int>, value: Int, index: Int, code: ArrayList<Int>): Int {
    return when (code[index]) {
        0 -> instructions[value]
        1 -> value
        else -> 0
    }
}

fun main() {
    println("Part_1: ${instruction()}")
}
