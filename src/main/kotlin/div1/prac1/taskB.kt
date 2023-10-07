package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (a, b, c) = MutableList(3) {reader.readLine().trim().toInt()}

    if(a+b<=c || a+c<=b || b+c<=a) {
        println("NO")
    } else {
        println("YES")
    }
}