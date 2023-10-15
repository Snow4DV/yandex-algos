import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val writer = PrintWriter(System.out)


    val dict = HashMap<String, Int>()

    var line: String?


    while(reader.readLine().also { line = it } != null) {
        writer.println(line!!.split(" ").asSequence()
            .filter { it.isNotEmpty() }.map {
            dict[it] = (dict[it] ?: 0) + 1
            dict[it]!! - 1
        }.joinToString(" "))
    }
    writer.close()
}