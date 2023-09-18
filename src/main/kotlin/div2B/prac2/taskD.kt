package div2B.prac2

import java.util.*

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)

    val length = s.nextInt()

    val benchCount = s.nextInt()

    val benchCenter = length/2.0 //

    val benches = Array(benchCount) {s.nextInt()}

    if(benchCount < 2) {
        return
    }

    var rightestOnLeft = -1

    var leftestOnRight = -1

    for(i in 0 until benchCount) {
        if(benches[i] < benchCenter) {
            rightestOnLeft = benches[i]
        }
        if(benches[i] + 1 > benchCenter) {
            leftestOnRight = benches[i]
            break
        }
    }

    if(rightestOnLeft == leftestOnRight) {
        println(rightestOnLeft)
    } else {
        println("$rightestOnLeft $leftestOnRight")
    }
}