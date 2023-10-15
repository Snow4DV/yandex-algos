package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.getNum()


    // 0 - do not check, "1" - closer than prev, "-1" - further than prev
    var facts = mutableListOf(reader.readLine().trim().toDouble() to 0)

    repeat(n - 1) {
        val (num, fact) = reader.readLine().trim().split(' ')
        facts.add(num.toDouble() to if (fact == "closer") 1 else -1)
    }

    var left = 30.0
    var right = 4000.0

    for(i in 1 until facts.size) {
        val prevFact = facts[i-1]
        val fact = facts[i]

        var whichSideToChange = if(fact.second == 1 && prevFact.first > fact.first || fact.second == -1 && prevFact.first < fact.first) {
            1 // right
        } else {
            -1 // left
        }

        when(whichSideToChange) {
            1 -> right = min((prevFact.first + fact.first) / 2, right)
             -1 -> left = max((prevFact.first + fact.first) / 2, left)
        }
    }

    println("$left $right")

    writer.close()
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()

