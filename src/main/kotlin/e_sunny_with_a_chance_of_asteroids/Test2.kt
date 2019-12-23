package main.kotlin.e_sunny_with_a_chance_of_asteroids

const val instruction2 = 5

fun instruction2(): Int {
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
                instructions[instructions[indx + 1]] = instruction2
                s += 2
            }
            4 -> {
                output = getValue(instructions, instructions[indx + 1], 0, code)
                s += 2
            }
            5 -> {
                if(getValue(instructions, instructions[indx + 1], 0, code) != 0) {
                    s = getValue(instructions, instructions[indx + 2], 1, code)
                } else s += 3
            }
            6 -> {
                if(getValue(instructions, instructions[indx + 1], 0, code) == 0) {
                    s = getValue(instructions, instructions[indx + 2], 1, code)
                } else s += 3
            }
            7 -> {
                instructions[instructions[indx + 3]] =
                    if (getValue(instructions, instructions[indx + 1], 0, code) <
                        getValue(instructions, instructions[indx + 2], 1, code)) 1 else 0
                s += 4
            }
            8 -> {
                instructions[instructions[indx + 3]] =
                    if (getValue(instructions, instructions[indx + 1], 0, code) ==
                        getValue(instructions, instructions[indx + 2], 1, code)) 1 else 0
                s += 4
            }
            else -> {
                s += instructions.size
            }
        }
        first = false

    }
    return output
}

fun main() {
    println("Part_2: ${instruction2()}")
}
