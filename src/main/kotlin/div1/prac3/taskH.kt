import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val xCoords = HashSet<Int>()


    repeat(reader.readLine().toInt()) {
        xCoords.add(reader.readLine().split(" ")[0].toInt())
    }

    println(xCoords.size)

    writer.close()
}

