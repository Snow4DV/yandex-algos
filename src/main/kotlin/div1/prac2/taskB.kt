package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val list = reader.readLines().map { it.trim().toInt() }.dropLast(1)

    var hasSameNums = false
    var hasAscNums = false
    var hasDescNums = false

    for(i in 1 until list.size) {

        if(list[i] > list[i - 1]) {
            hasAscNums = true
        } else if(list[i] == list[i - 1]) {
            hasSameNums = true
        } else {
            hasDescNums = true
        }
    }


    val result = when {
        hasSameNums && !hasAscNums && !hasDescNums -> "CONSTANT"
        hasAscNums && !hasDescNums && !hasSameNums -> "ASCENDING"
        hasAscNums && hasSameNums && !hasDescNums -> "WEAKLY ASCENDING"
        hasDescNums && !hasAscNums && !hasSameNums -> "DESCENDING"
        hasDescNums && !hasAscNums && hasSameNums -> "WEAKLY DESCENDING"
        else -> "RANDOM"
    }

    writer.println(result)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

