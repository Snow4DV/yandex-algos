package div1.prac6

import java.io.*
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, l) = reader.readLine().trim().split(' ').map { it.toInt() }

    val sequences = Array<List<Int>>(n) { reader.readLine().trim().split(' ').map { it.toInt() } }

    for(i in sequences.indices) {
        for(j in i+1..sequences.indices.last) {
            writer.println(findLeftMedian(sequences[i], sequences[j]))
        }
    }

    writer.close()
}

private fun findLeftMedian(seq1: List<Int>, seq2: List<Int>): Int {
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