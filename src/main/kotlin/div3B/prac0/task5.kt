package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.math.max
import kotlin.math.min

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)


    val alphabet = "abcdefghijklmnopqrstuvwxyz".substring(0, reader.getNum()).toMutableList()
    fun getNextChar(prev: Char):Char {
        return if (prev == 'z') 'E' else (prev.code + 1).toChar()
    }

    val map = HashMap<Char, Int>()

    alphabet.forEach {
        map[it] = reader.getNum()
    }

    var goodCount = 0L
    alphabet.forEach {char ->
        val nextChar = getNextChar(char)
        val countOfPairs = min(map[char] ?: 0, map[nextChar] ?: 0)
        goodCount += countOfPairs
    }


    writer.println(goodCount)

    writer.close()
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()