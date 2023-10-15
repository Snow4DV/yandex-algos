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
        line!!.split(" ").forEach { w ->
            val word = w
            ".,!?:-;'~`".forEach { word.replace(it.toString(), "") }
            dict[word] = (dict[word] ?: 0) + 1
        }
    }

    dict.values.max().let { max -> println(dict.filter { it.value == max }.keys.min()) }
    writer.close()
}