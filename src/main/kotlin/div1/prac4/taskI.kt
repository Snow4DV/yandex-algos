import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val dict = HashMap<String, HashSet<String>>() // <LowercaseWord, Set<UdarIndex>>

    val n = reader.readLine().toInt()
    repeat(n) {
        val word = reader.readLine()
        if(!dict.contains(word.toLowerCase())) { // create set if it doesn't exist
            dict[word.toLowerCase()] = HashSet()
        }
        dict[word.toLowerCase()]!!.add(word)
    }



    val textWords = reader.readLine().split(" ")


    var mistakes = 0
    for(word in textWords) {
        if(word.length == 0) continue
        if(dict[word.toLowerCase()]?.contains(word) == true) { // word with correct stress is in dict
            continue
        } else if(!dict.contains(word.toLowerCase())) { // word is not in dict
            val upperCount = word.count { it.isUpperCase() }
            if(upperCount != 1) {
                mistakes++
            }
        } else {
            mistakes++
        }
    }

    println(mistakes)
    writer.close()
}

