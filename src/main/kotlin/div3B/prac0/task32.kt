package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = reader.readLine().trim().split(' ').map { it.toInt() }
    val mergeList = Array<MutableSet<Int>>(n + 1) { mutableSetOf() }
    val visited = IntArray(n+1)

    repeat(m) {
        val (v1, v2) = reader.readLine().trim().split(' ').map { it.toInt() }
        mergeList[v1].add(v2)
        mergeList[v2].add(v1)
    }


    var comp = 0
    visited.forEachIndexed { index, it ->
        if(it == 0) {
            comp++
            dfs2(mergeList, visited, index, comp)
        }
    }

    val res = Array<MutableList<Int>>(comp + 1) { mutableListOf() }

    for(i in 1..n) {
        res[visited[i]].add(i)
    }

    val writer = PrintWriter(System.out)

    writer.println(comp - 1)

    for(i in 2..comp) {
        val oneComp = res[i]
        writer.println(oneComp.size)
        writer.println(oneComp.joinToString(" "))
    }

    writer.close()


}

private fun dfs2(mergeList: Array<MutableSet<Int>>, visited: IntArray, current: Int, comp: Int) {
    visited[current] = comp
    for(v in mergeList[current]) {
        if(visited[v] == 0) {
            dfs2(mergeList, visited, v, comp)
        }
    }
}