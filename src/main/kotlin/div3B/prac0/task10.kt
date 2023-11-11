package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)
    val word = reader.readLine().trim()


    var charMap = HashMap<Char, Long>()
    for(i in word.indices) {
        val possibleCombosEndingWithChar = i + 1L
        val possibleCombosStartingWithChar = word.length - i - 0L
        val possibleCombinationsWithChar = possibleCombosStartingWithChar * possibleCombosEndingWithChar
        charMap[word[i]] = (charMap[word[i]] ?: 0L) + possibleCombinationsWithChar
    }

    charMap.keys.sorted().forEach {
        writer.println("${it}: ${charMap[it]}")
    }


    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()