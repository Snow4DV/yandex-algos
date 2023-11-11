package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val heap = PriorityQueue<Int>()

    val n = reader.readLine().trim().toInt()
    val arr = reader.readLine().trim().split(' ').map { it.toInt() }
    val sortedArr = IntArray(n)
    var curIndex = 0

    arr.forEach {
        heap.add(it)
    }

    while(heap.size > 0) {
        sortedArr[curIndex++] = heap.poll()
    }

    writer.println(sortedArr.joinToString(" "))



    writer.close()
}


private fun <T> swap(list: MutableList<T>, pos1: Int, pos2: Int) {
    val temp = list[pos1]
    list[pos1] = list[pos2]
    list[pos2] = temp
}