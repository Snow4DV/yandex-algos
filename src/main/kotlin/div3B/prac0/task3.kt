package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.max

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val nDiego = reader.getNum()
    val diegoStickers = reader.readLine().trim().split(' ').map { it.toInt() }
        .toHashSet().sorted() // sorted list of unique stickers

    val collCount = reader.getNum()
    val collStickers = reader.readLine().trim().split(' ').map { it.toInt() }

    collStickers.forEach {
        val countOfGoodStickers = binSearchCountOfGoodStickersInCollection(diegoStickers, it - 1)
        writer.println(countOfGoodStickers)
    }
    writer.close()
}

fun binSearchCountOfGoodStickersInCollection(diegoStickers: List<Int>, maxGoodSticker: Int): Int {
    var l = 0
    var r = diegoStickers.indices.last

    while(l < r) {
        val m = (l+r+1)/2
        if(diegoStickers[m] <= maxGoodSticker) {
            l = m
        } else {
            r = m - 1
        }
    }

    return if(diegoStickers[l] <= maxGoodSticker) l + 1 else 0
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()