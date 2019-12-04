package main.kotlin.b_program_alarm

import java.io.File

const val FILENAME = "src/main/kotlin/b_program_alarm/input.txt"
const val wanted = 19690720

fun getList(): List<Int> {
    var input = ""
    File(FILENAME).forEachLine { input = it }
    return input.split(",").toList().map { it.toInt() }.toList()
}

fun doCalc(noun: Int, verb: Int): List<Int> {
    var list = getList().toMutableList()
    list[1] = noun
    list[2] = verb

    var index = 0
    while (list[index] != 99) {
        when (list[index]) {
            1 -> list[list[index + 3]] = list[list[index + 1]] + list[list[index + 2]]
            2 -> list[list[index + 3]] = list[list[index + 1]] * list[list[index + 2]]
            else -> {
                list[0] = -1
                return list
            }
        }
        index += 4
    }
    return list
}

fun getFinalAtPos0(): Int {
    return doCalc(12, 2)[0]
}

fun getForResult(): Int {
    for (i in 0..99) {
        for (j in 0..99) {
            var result = doCalc(i, j).toMutableList()
            if (result[0] == wanted) {
                return 100 * result[1] + result[2]
            }
        }
    }

    return -1
}

fun main() {
    println("Part_1: ${getFinalAtPos0()}")
    println("Part_2: ${getForResult()}")
}
