package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = reader.readLine().trim().split(' ').map { it.toInt() }
    val mergeList = Array<MutableSet<Int>>(n + 1) { mutableSetOf() }
    val visited = ByteArray(n+1)

    repeat(m) {
        val (v1, v2) = reader.readLine().trim().split(' ').map { it.toInt() }
        mergeList[v1].add(v2)
        mergeList[v2].add(v1)
    }

    dfs(mergeList, visited, 1)

    val result = mutableListOf<Int>()
    var counter = 0
    visited.forEachIndexed { index, it ->
        if(it.compareTo(0) != 0) {
            result.add(index)
            counter++
        }
    }

    println(counter)
    println(result.joinToString(" "))


}

private fun dfs(mergeList: Array<MutableSet<Int>>, visited: ByteArray, current: Int) {
    visited[current] = 1.toByte()
    for(v in mergeList[current]) {
        if(visited[v].compareTo(0) == 0) {
            dfs(mergeList, visited, v)
        }
    }
}