package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() { // skipped this task

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)


    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()