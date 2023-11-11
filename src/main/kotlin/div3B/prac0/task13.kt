package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.IllegalArgumentException
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val stack = LinkedList<Int>()

    reader.readLine().trim().split(' ').forEach {
        val itIsOperation = it in "+-*"
        if(itIsOperation) {
            val num2 = stack.pop()
            val num1 = stack.pop()
            val res = when(it.first()) {
                '*' -> num1 * num2
                '-' -> num1 - num2
                '+' -> num1 + num2
                else -> throw IllegalArgumentException()
            }
            stack.push(res)
        } else {
            stack.push(it.toInt())
        }
    }

    writer.println(stack.pop())

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()