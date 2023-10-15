import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val numSet = reader.readLine().trim().split(' ').map { it.toCharArray()[0] }.toHashSet()

    reader.readLine().trim().forEach { num ->
        numSet.add(num)
    }

    writer.println(numSet.size - 3)


    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()