package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.max

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    var maxHeight = 0
    val symbols = HashMap<Char, Int>()

    reader.readLines().forEach { line ->
        line.trim().forEach { char ->
            if(char != ' ') {
                symbols[char] = (symbols[char] ?: 0) + 1
                maxHeight = max(maxHeight, symbols[char]!!)
            }
        }
    }

    val sortedKeys = symbols.keys.sorted()
    for (height in maxHeight downTo 1) {
        sortedKeys.forEach {key ->
            writer.print(if(height <= symbols[key]!!) '#' else ' ')
        }
        writer.println()
    }
    sortedKeys.forEach(writer::print)

    writer.close()
}