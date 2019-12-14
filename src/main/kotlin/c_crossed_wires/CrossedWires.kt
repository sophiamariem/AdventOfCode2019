package main.kotlin.c_crossed_wires

import java.awt.Point
import java.util.*
import java.io.File
import kotlin.collections.ArrayList
import kotlin.math.abs

const val FILENAME = "src/main/kotlin/c_crossed_wires/input.txt"

data class Position(val direction: String, val count: Int)
data class Wire(val locations: HashMap<Point, Int>)

fun readInput(i: Int): List<String> {
    val lines = File(FILENAME).readLines()
    val line = lines[i].trim()
    return line.split(",").toList()
}

fun getList(pathList: List<String>, point: Point): Wire {
    var list: ArrayList<Position> = ArrayList()
    pathList.forEach {
        list.add(Position(it.take(1), it.drop(1).toInt()))
    }

    var pointList: HashMap<Point, Int> = HashMap()
    var newY = 0
    var newX = 0
    var count = 0

    list.forEach {
        when (it.direction) {
            "U" -> newY = point.y + it.count
            "D" -> newY = point.y - it.count
            "R" -> newX = point.x + it.count
            "L" -> newX = point.x - it.count
        }

        for (i in point.x until newX) {
            pointList[Point(i, point.y)] = count
            count = count.inc()
        }
        for (i in newX until point.x) {
            pointList[Point(i, point.y)] = count
            count = count.inc()
        }
        point.x = newX

        for (i in point.y until newY) {
            pointList[Point(point.x, i)] = count
            count = count.inc()
        }
        for (i in newY until point.y) {
            pointList[Point(point.x, i)] = count
            count = count.inc()
        }
        point.y = newY
    }
    return Wire(pointList)
}

fun getIntersection(first: Wire, second: Wire): LinkedHashSet<Point> {
    val results = LinkedHashSet<Point>()

    for (element in first.locations.keys) {
        if (second.locations.keys.contains(element)) {
            results.add(element)
        }
    }

    return results
}

fun findWireIntersection(): Int? {
    var first = getList(readInput(0), Point(-1, 0))
    var second = getList(readInput(1), Point(0, 1))

    var inter = getIntersection(first, second)
    return inter.map { abs(it.x) + abs(it.y) }.min()
}

fun findShortestPath(): Int? {
    var first = getList(readInput(0), Point(0, 0))
    var second = getList(readInput(1), Point(0, 0))

    var inter = getIntersection(first, second)
    return inter.map { abs(first.locations[it]!!.toInt()) + abs(second.locations[it]!!.toInt()) }.min()
}

fun main() {
    println("Part_1: ${findWireIntersection()}")
    println("Part_2: ${findShortestPath()}")
}
