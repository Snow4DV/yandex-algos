import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val set = HashSet<Pair<Int, Int>>()

    val n = reader.readLine().toInt()

    for(i in 1..n) {
        val onLeft = i - 1
        val onRight = n - i
        set.add(onLeft to onRight)
    }

    var counter = 0

    repeat(n) {
        val split = reader.readLine().split(" ")
        val pair = split[0].toInt() to split[1].toInt()
        if(set.remove(pair)) { // remove succeeded
            counter++
        }
    }

    println(counter)

    writer.close()
}

