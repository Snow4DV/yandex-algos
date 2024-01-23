package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().trim().toInt()

    val arr = Array(n) { Array(n) { IntArray(n) } }
    // -1 == busy
    // -2 == unvisited

    // arr[depth][x][y]

    lateinit var begin: Triple<Int, Int, Int>

    repeat(n) { depth ->
        reader.readLine()
        repeat(n) { x ->
            reader.readLine().trim().forEachIndexed { y, it ->
                when (it) {
                    '#' -> arr[depth][x][y] = -1
                    '.' -> arr[depth][x][y] = -2
                    'S' -> {
                        arr[depth][x][y] = 0
                        begin = Triple(depth, x, y)
                    }
                }
            }
        }
    }


    fun getWays(current: Triple<Int, Int, Int>): List<Triple<Int, Int, Int>> {
        val depth = current.first
        val x = current.second
        val y = current.third
        return listOf(
            Triple(depth - 1, x, y),
            Triple(depth + 1, x, y),
            Triple(depth, x - 1, y),
            Triple(depth, x + 1, y),
            Triple(depth, x, y - 1),
            Triple(depth, x, y + 1)
        ).filter { it.first in arr.indices && it.second in arr[it.first].indices && it.third in arr[it.first][it.second].indices }
    }

    val queue = LinkedList<Triple<Int, Int, Int>>()
    queue.addLast(Triple(begin.first, begin.second, begin.third))

    var minRoute = n*n*n

    while(queue.size > 0) {
        val coords = queue.removeFirst()
        val len = arr.get(coords)

        getWays(coords).forEach { neigh ->
            if(arr.get(neigh) == -2) {
                arr.set(neigh, len + 1)
                queue.addLast(neigh)
                if(neigh.first == 0) {
                    minRoute = min(minRoute, arr.get(neigh))
                }
            }
        }
    }

    println(minRoute)


}

private fun Array<Array<IntArray>>.get(triple: Triple<Int, Int, Int>): Int {
    return this[triple.first][triple.second][triple.third]
}

private fun Array<Array<IntArray>>.set(triple: Triple<Int, Int, Int>, value: Int) {
    this[triple.first][triple.second][triple.third] = value
}
