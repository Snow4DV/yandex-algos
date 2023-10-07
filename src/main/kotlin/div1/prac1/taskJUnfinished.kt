package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() { // я когда-нибудь обязательно это решу, но так не хочется(((( задача хуже скорой помощи без перебора, надо писать кучу ифов
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (a, b, c, d, e, f) = List<Int>(5) {reader.getNum()}

    if(a == 0)

    writer.close()
}

private operator fun <E> List<E>.component6(): E {
    return this[5]
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

