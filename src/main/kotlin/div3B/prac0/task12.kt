package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.LinkedList

fun main() {

    fun getReversePar(par: Char): Char {
        return when(par) {
            '{' -> '}'
            '[' -> ']'
            '(' -> ')'
            '}' -> '{'
            ']' -> '['
            ')' -> '('
            else -> throw IllegalArgumentException()
        }
    }

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val openStack = LinkedList<Char>()

    var badFlag = false

    val line = reader.readLine()
    line.forEach {
        when(it) {
            '{', '[', '(' -> {
                openStack.addLast(it)
            }
            '}', ']', ')' -> {
                val reversed = getReversePar(it)

                if(openStack.peekLast() != reversed) {
                    badFlag = true
                    return@forEach
                }

                openStack.removeLast()
            }
        }
    }

    val res = if(openStack.size == 0 && !badFlag) "yes" else "no"
    writer.println(res)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()