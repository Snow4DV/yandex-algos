import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val set = HashSet<Int>()

    val line = reader.readLine()

    for(numStr in line.split(" ")) {
        val num = numStr.toInt()
        if(!set.contains(num)) {
            set.add(num)
        }
    }

    println(set.size)

    writer.close()
}

