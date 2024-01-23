package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = reader.readLine().trim().split(" ").map { it.toInt() }

    val mergeList = Array<MutableList<Int>>(n+1) { mutableListOf() }

    repeat(m) {
        val (v1, v2) = reader.readLine().trim().split(" ").map { it.toInt() }
        mergeList[v1].add(v2)
        mergeList[v2].add(v1)
    }



    val writer = PrintWriter(System.out)

    val visited = IntArray(n + 1)
    for(v in 1..n) {
        if(visited[v] == 0 && !dfs3(mergeList, visited, v, 1)) {
            writer.println("NO")
            writer.close()
            return
        }
    }

    writer.println("YES")
    writer.close()


}


private fun dfs3(mergeList: Array<MutableList<Int>>, visited: IntArray, current: Int, color: Int): Boolean {
    visited[current] = color
    for(adj in mergeList[current]) {
        if(visited[adj] == 0) {
            if(!dfs3(mergeList, visited, adj, 3 - color)) return false
        } else if(visited[adj] == color) {
            return false
        }
    }
    return true
}
