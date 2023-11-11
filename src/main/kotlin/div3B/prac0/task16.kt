package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val deque = ArrayDeque<Int>()

    while(true) {
        val entries = reader.readLine().trim().split(' ')
        val result = when (entries[0]) {
            "push" -> {
                deque.addLast(entries[1].toInt())
                "ok"
            }
            "pop" -> {
                deque.removeFirstOrNull()?.toString() ?: "error"
            }
            "front" -> {
                deque.firstOrNull()?.toString() ?: "error"
            }
            "size" -> {
                deque.size.toString()
            }
            "clear" -> {
                deque.clear()
                "ok"
            }
            else -> {
                "bye"
            }
        }

        writer.println(result)
        if (result == "bye") {
            break
        }
    }


    writer.close()
}
