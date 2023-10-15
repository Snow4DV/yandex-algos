import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val set1 = HashSet<Int>()

    for(numStr in reader.readLine().split(" ")) {
        val num = numStr.toInt()
        set1.add(num)
    }

    val treeSet = TreeSet<Int>()

    for(numStr in reader.readLine().split(" ")) {
        val num = numStr.toInt()
        if(set1.contains(num)) {
            treeSet.add(num)
        }
    }

    treeSet.forEach { writer.println(it) }

    writer.close()
}

