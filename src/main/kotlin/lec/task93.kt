package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val nums = reader.readLine().trim().split(' ').map{ Pair<Int, Int?>(it.toInt(), null) }.toMutableList()


    val curStack = LinkedList<Pair<Int, Int>>() // value to index
    nums.forEachIndexed { index, num ->
        while(curStack.peekFirstOrNull()?.let { it.first > num.first } == true) {
            val stackNum = curStack.pop()
            nums[stackNum.second] = Pair(stackNum.first, index)
        }
        curStack.push(num.first to index)
    }

    nums.forEach {
        writer.println("for ${it.first} smaller index is ${it.second}")
    }

    writer.close()
}


private fun <T> LinkedList<T>.peekFirstOrNull(): T? = if (this.size == 0) null else peekFirst()
private fun <T> LinkedList<T>.peekLastOrNull(): T? = if (this.size == 0) null else peekLast()
