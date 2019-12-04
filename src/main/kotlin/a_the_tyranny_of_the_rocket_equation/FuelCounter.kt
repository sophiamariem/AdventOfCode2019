package main.kotlin.a_the_tyranny_of_the_rocket_equation

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.max

const val FILENAME = "src/main/kotlin/a_the_tyranny_of_the_rocket_equation/input.txt"
val list: ArrayList<Int> = ArrayList()

fun calculateFuelTotalPartOne(): Int {
    File(FILENAME).forEachLine {
        list.add(calculateFuel(it.toInt()))
    }

    var sum = 0
    for (item in list) {
        sum += item
    }
    return sum
}

fun calculateFuelTotalPartTwo() = Files.readAllLines(Paths.get(FILENAME))
    .sumBy { calculate(it.toInt()) }

private fun calculate(tot: Int): Int {
    var fuel = calculateFuel(tot)
    var totalFuel = fuel
    while (fuel != 0) {
        fuel = calculateFuel(fuel)
        totalFuel += fuel
    }

    return totalFuel
}

private fun calculateFuel(it: Int) = max((it / 3) - 2, 0)

fun main() {
    println("Part_1: ${calculateFuelTotalPartOne()}")
    println("Part_2: ${calculateFuelTotalPartTwo()}")
}