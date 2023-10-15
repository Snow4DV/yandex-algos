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

    val wordSet = HashSet<String>()

    var counter = 0L

    for(line in reader.readLines()) {
        for(word in line.split(Pattern.compile("""\s+"""))) {
            if(!wordSet.contains(word) && word.length > 0) {
                counter++
            }
            wordSet.add(word)
        }
    }
    writer.println(counter)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()