import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)



    val map = HashMap<Int, Int>() // <WidthInt, MaxHeightInt>



    repeat(reader.readLine().toInt()) {
        val split = reader.readLine().split(" ")
        val width = split[0].toInt()
        val height = split[1].toInt()
        map[width] = max((map[width] ?: 0), height)
    }

    var sum = 0L

    map.keys.sortedDescending().forEach {
        sum += map[it]!!
    }


    println(sum)

}