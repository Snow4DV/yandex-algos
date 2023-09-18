package div2B.prac4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val writer = PrintWriter(System.out)

    val map = HashMap<String, Int>()


    var line: String? = null

    while((reader.readLine().also { line = it }) != null) {
        val words = line?.split(" ") ?: listOf()
        words.forEach {
            map[it] = (map[it] ?: 0) + 1
        }
    }

    map.entries.sortedWith { o1, o2 ->
        val compare1 = o1.value.compareTo(o2.value) * -1
        return@sortedWith if (compare1 != 0) compare1 else o1.key.compareTo(o2.key)
    }.forEach { writer.print("${it.key}\n") }

    writer.close()
}