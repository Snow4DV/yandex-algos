package lec

import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)


    writer.close()
}


fun binSearch(w: Int, h: Int, n: Int): Int {
    var l = 1
    var r = max(w, h)

    while(l < r) {
        val m = (l + r + 1) / 2
        if(w - m * n >= 0 && h - m * n >= 0) {
            l = m
        } else {
            r = m - 1
        }
    }

    return l
}

