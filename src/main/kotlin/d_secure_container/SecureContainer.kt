package main.kotlin.d_secure_container

var minRange = 245182
var maxRange = 790572

private fun minCriteria(): Sequence<String> {
    return (minRange..maxRange)
        .asSequence()
        .map { it.toString() }
        .filter { it.length == 6 }
        .filter { value -> value.windowed(2).any { it[0] == it[1] } }
        .filter { value -> value.windowed(2).none { it[0] > it[1] } }
}

fun extendedCriteria(s: String): Boolean {
    return s.groupBy { it }
        .mapValues { (k, v) -> v.size }
        .any { (k, v) -> v == 2 }
}

fun passwordCount(): Int {
    return minCriteria().count()
}

fun partTwo(): Int {
    return minCriteria().filter { extendedCriteria(it) }.count()
}

fun main() {
    println("Part_1: ${passwordCount()}")
    println("Part_2: ${partTwo()}")
}