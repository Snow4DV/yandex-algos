package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (a, b, c) = List<Int>(3) {reader.getNum()}.sorted()
    val (d, e) = List<Int>(2) {reader.getNum()}.sorted()

    writer.println(if((a <= d && b <= e) || (c <= e && a <= d)) "YES" else "NO")

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

