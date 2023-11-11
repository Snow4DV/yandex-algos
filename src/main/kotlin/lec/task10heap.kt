package lec

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val heap = ArrayList<Int>()

    fun push(x: Int) {
        heap.add(x)
        var pos = heap.indices.last
        while(pos > 0 && heap[(pos - 1)/2] > heap[pos]) { // просеивание вверх
            swap(heap, (pos - 1)/ 2, pos)
            pos = (pos - 1)/2
        }
    }

    fun remove() {
        heap[0] = heap.last()
        heap.removeLast()

        var pos = 0

        while (true) {
            val child1 = if(heap.indices.last >= (2 * pos + 1)) (2 * pos + 1) else -1
            val child2 = if(heap.indices.last >= (2 * pos + 2)) (2 * pos + 2) else -1

            val minChildIndex = when {
                child1 == -1 && child2 == -1 -> -1
                child2 == -1 || heap[child1] <= heap[child2] -> child1
                else -> child2
            }

            if(minChildIndex == -1 || heap[minChildIndex] >= heap[pos]) {
                break
            } else {
                swap(heap, pos, minChildIndex)
                pos = minChildIndex
            }
        }
    }

    listOf(2, 5, 11, 4, 6, 25, 8, 12, 20).forEach {
        push(it)
    }

    remove()

    writer.println(heap)



    writer.close()
}


private fun <T> swap(list: MutableList<T>, pos1: Int, pos2: Int) {
    val temp = list[pos1]
    list[pos1] = list[pos2]
    list[pos2] = temp
}