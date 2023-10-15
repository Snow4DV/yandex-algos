import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val map = HashMap<String, Int>()

    val genome2 = reader.readLine()
    val genome1 = reader.readLine()

    for(i in 0 until genome1.length - 1) {
        val genome = genome1.substring(i, i + 2)
        map[genome] = (map[genome] ?: 0) + 1
    }

    var counter = 0

    // AB BB BA AC CA AB
    // BC CA AB BB

    for(i in 0 until genome2.length - 1) {
        val genome = genome2.substring(i, i + 2)
        if(map.contains(genome)) {

            counter++
        }
    }


    println(counter)
    writer.close()
}

