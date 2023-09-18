import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    var curLevel = 0


    val text = reader.readLine()

    text.forEach {
        if(it == '(') curLevel++
        if(it == ')') {
            curLevel--
            if(curLevel < 0) {
                println("NO")
                return
            }
        }
    }

     // )(

    if(curLevel==0) {
        print("YES")
    } else {
        print("NO")
    }
    writer.close()
}

