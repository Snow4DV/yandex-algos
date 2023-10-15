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

    val n = reader.readLine().toInt()

    val dict = HashMap<String, String>()

    repeat(n) {
        val words = reader.readLine().split(" ")
        dict[words[0]] = words[1]
        dict[words[1]] = words[0]
    }

    writer.println(dict[reader.readLine()])
    writer.close()
}