package div2B.prac7

import java.io.*
import kotlin.collections.ArrayList

/*
Решение с помощью бинпоиска по префиксным суммам - не заходит
 */
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, m) = reader.readLine().trim().split(' ').map {it.toInt()}

    var prefixSums = ArrayList<Pair<Int,Int>>(n + 1)
    prefixSums.add(Integer.MIN_VALUE to 0)

    reader.readLine().trim().split(' ').map { it.toInt() }.sorted().forEach {
        prefixSums.add(it to (prefixSums.lastOrNull()?.second ?: 0) + 1)
    }


    repeat(m) {
        val (left, right) = reader.readLine().trim().split(' ').map { it.toInt() }
        println(kittensCountOnRange(prefixSums, left, right))
    }

    writer.close()
}

private fun kittensCountOnRange(prefixSums: ArrayList<Pair<Int, Int>>, left: Int, right: Int): Int {
    var lIndex = findBinSearchR(prefixSums) { it.first < left }
    val rIndex = findBinSearchR(prefixSums) { it.first <= right }

    if (prefixSums[rIndex].first < left) return 0
    return prefixSums[rIndex].second - prefixSums[lIndex].second
}

private fun findBinSearchL(prefixSums: ArrayList<Pair<Int, Int>>, check: (Pair<Int, Int>) -> Boolean): Int {
    var l = 0
    var r = prefixSums.indices.last
    while(l < r) {
        val m = (l+r)/2
        if(check(prefixSums[m])) {
            r = m
        } else {
            l = m + 1
        }
    }

    return if(check(prefixSums[l])) l else -1
}


private fun findBinSearchR(prefixSums: ArrayList<Pair<Int, Int>>, check: (Pair<Int, Int>) -> Boolean): Int {
    var l = 0
    var r = prefixSums.indices.last
    while(l < r) {
        val m = (l+r+1)/2
        if(check(prefixSums[m])) {
            l = m
        } else {
            r = m - 1
        }
    }

    return if(check(prefixSums[l])) l else -1
}
