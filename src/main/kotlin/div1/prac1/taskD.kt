package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.IllegalArgumentException
import kotlin.math.sqrt

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)
    val a = reader.readLine().trim().toInt()
    val b = reader.readLine().trim().toInt()
    val c = reader.readLine().trim().toInt()

    if(a == 0 && b >= 0 && c >= 0 && c*c == b) {
        writer.println("MANY SOLUTIONS")
    } else if(c < 0 || a == 0) {
        writer.println("NO SOLUTION")
    } else {
        val x = (c*c-b)/a
        if(a*x + b == c*c) {
            writer.println(x)
        } else {
            writer.println("NO SOLUTION")
        }
    }

    writer.close()
}