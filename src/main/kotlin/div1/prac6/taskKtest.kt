package div1.prac6

import java.io.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, l) = reader.readLine().trim().split(' ').map { it.toInt() }

    val sequences = Array<Array<Int>>(n) { reader.readLine().trim().split(' ').map { it.toInt() }.toTypedArray() }

    for(i in sequences.indices) {
        for(j in i+1..sequences.indices.last) {
            println(binSearchL(sequences[i], sequences[j]))
        }
    }

    writer.close()
}

private fun findLeftMedian(seq1: Array<Int>, seq2: Array<Int>): Int {
    var i = 0
    var j = 0

    while(i+j < seq1.size) {
        if(j >= seq2.size || seq1[i] < seq2[j]) {
            i++
        } else {
            j++
        }
    }

    return if(j == 0) seq1[i-1] else if(i == 0) seq2[j-1] else max(seq1[i-1], seq2[j-1])
}



private fun binSearchL(seq1: Array<Int>, seq2: Array<Int>): Int {
    // iterating through X - possible median number
    var l = min(seq1.first(), seq2.first())
    var r = max(seq1.last(), seq2.last())

    while(l < r) {

        if(abs(l-r) == 1) {
            val countNumbersLessEqThanX1 = countNumbersLessEqThanX(seq1, seq2, l)
            if(countNumbersLessEqThanX1 >= seq1.size) {
                return l
            } else {
                return r
            }
        }


        val m = (l+r)/2

        val countNumbersLessEqThanX = countNumbersLessEqThanX(seq1, seq2, m)
        if(countNumbersLessEqThanX > seq1.size) {
            r = m
        } else if(countNumbersLessEqThanX < seq1.size) {
            l = m + 1
        } else {
            r = m
        }
    }

    return l
}


private fun countNumbersLessEqThanX(seq1: Array<Int>, seq2: Array<Int>, x: Int): Int {
    val countOfSeq1 = countOfNumbersLessEqThanXInOneSeq(seq1, x)
    val countOfSeq2 = countOfNumbersLessEqThanXInOneSeq(seq2, x)
    return countOfSeq1 + countOfSeq2
}

private fun countOfNumbersLessEqThanXInOneSeq(seq: Array<Int>, x: Int): Int {
    //binSearchRLastIndexLessThanX
    var l = 0
    var r = seq.size - 1
    while(l < r) {
        val m = (l+r+1)/2
        if(seq[m] <= x) {
            l = m
        } else {
            r = m - 1
        }
    }

    if(seq[l] > x) return 0
    return l + 1
}
/*
272
572
278
272
-376
278
 */