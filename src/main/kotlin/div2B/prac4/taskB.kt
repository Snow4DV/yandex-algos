package div2B.prac4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val writer = PrintWriter(System.out)

    val map = HashMap<String, Int>()

    var line: String?

    while(reader.readLine().also { line = it } != null) {
        if(line == null) break
        val splitLine = line!!.split(" ")
        val name = splitLine[0]
        val votes = splitLine[1].toInt()
        map[name] = (map[name] ?: 0) + votes
    }

    map.keys.sorted().forEach {
        writer.print("$it ${map[it]}\n")
    }

    writer.close()
}