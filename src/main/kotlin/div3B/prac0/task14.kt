package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.IllegalArgumentException
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)


    val way2 = LinkedList<Int>()
    val deadEnd = LinkedList<Int>()


    var fail = false

    val n = reader.getNum()
    val way1 = LinkedList<Int>(reader.readLine().trim().split(' ').map { it.toInt() }.toList())

    while(true) {
        val nextExpectedCarriageInWay2 = (way2.peekFirst() ?: 0) + 1
        var stepFlag = false

        deadEnd.peekFirstOrNull()?.let { // check dead end first
            if(nextExpectedCarriageInWay2 == it) { // we need it!
                way2.push(deadEnd.pop())
                stepFlag = true
            }
        }

        if(!stepFlag) way1.peekFirstOrNull()?.let {
            deadEnd.push(way1.pop())
            stepFlag = true
        }

        if(!stepFlag && way1.size + deadEnd.size > 0) {
            fail = true
        }

        if(fail || way1.size + deadEnd.size == 0) {
            break
        }
    }

    writer.println(if (fail) "NO" else "YES")

    writer.close()
}

private fun LinkedList<Int>.peekFirstOrNull(): Int? = if (this.size == 0) null else peekFirst()
private fun LinkedList<Int>.peekLastOrNull(): Int? = if (this.size == 0) null else peekLast()


private fun BufferedReader.getNum() = this.readLine().trim().toInt()