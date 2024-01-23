package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    var (n, m, s, t, q) = reader.readLine().trim().split(" ").map { it.toInt() }
    s--
    t--

    val arr = Array(n) { IntArray(m) { -1 } } // -1 - unvisited; -2 = flea (bloha!1!)

    arr[s][t] = 0

    val fleaCoords = ArrayList<Pair<Int, Int>>(q * 2)
    var fleaCounter = q

    repeat(q) {
        val (x, y) = reader.readLine().trim().split(" ").map { it.toInt() - 1 }
        if(x == s && y == t) {
            fleaCounter--
        } else {
            arr[x][y] = -2
            fleaCoords.add(x to y)
        }
    }

    val queue = LinkedList<Pair<Int, Int>>()
    queue.addLast(s to t)

    fun tryGet(i: Int, j: Int): Pair<Int, Int>? {
        if (i in arr.indices && j in arr[i].indices) return i to j
        return null
    }

    fun getAllMovements(i: Int, j: Int): List<Pair<Int, Int>> {
        return listOf(
            i - 2 to j - 1,
            i - 2 to j + 1,
            i - 1 to j + 2,
            i + 1 to j + 2,
            i + 2 to j + 1,
            i + 2 to j - 1,
            i + 1 to j - 2,
            i - 1 to j - 2
        ).mapNotNull { tryGet(it.first, it.second) }
    }


    while (queue.size > 0 && fleaCounter > 0) {
        val top = queue.removeFirst()
        val children = getAllMovements(top.first, top.second)
        for(child in children) {
            val cond = arr.get(child)
            if(cond == -1) {
                arr.set(child, arr.get(top) + 1)
                queue.addLast(child)
            } else if(cond == -2) {
                arr.set(child, arr.get(top) + 1)
                queue.addLast(child)
                fleaCounter--
            }
        }
    }

    if(fleaCounter == 0) {
        var sum = 0
        fleaCoords.forEach {
            val get = arr.get(it)
            sum += get
        }
        println(sum)
    } else {
        println(-1)
    }
}

private fun Array<IntArray>.get(pair: Pair<Int, Int>): Int {
    return this[pair.first][pair.second]
}

private fun Array<IntArray>.set(pair: Pair<Int, Int>, value: Int) {
    this[pair.first][pair.second] = value
}
