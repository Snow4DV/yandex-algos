package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private data class MutablePair<A, B>(
    public var first: A,
    public var second: B
)

fun main(args: Array<String>) { // diy stack rec fact

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val fact = reader.readLine().trim().toLong()

    val stack = LinkedList<MutablePair<Long, Int>>()
    stack.push(MutablePair(fact,  0))

    var returnValue: Long? = null

    while(stack.size > 0) {
        val curFunc = stack.peek()
        when(curFunc.second) { //opcode
            0 -> {
                if(curFunc.first == 1L) {
                    returnValue = 1
                    stack.pop()
                } else {
                    curFunc.second++
                    stack.push(MutablePair(curFunc.first - 1, 0))
                }
            }
            1 -> {
                returnValue = returnValue!! * curFunc.first
                stack.pop()
            }
        }
    }

    writer.println(returnValue)

    writer.close()
}


private fun <T> LinkedList<T>.peekFirstOrNull(): T? = if (this.size == 0) null else peekFirst()
private fun <T> LinkedList<T>.peekLastOrNull(): T? = if (this.size == 0) null else peekLast()
