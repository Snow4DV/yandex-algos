package div3B.prac0

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
        while(pos > 0 && heap[(pos - 1)/2] < heap[pos]) { // просеивание вверх
            swap(heap, (pos - 1)/ 2, pos)
            pos = (pos - 1)/2
        }
    }

    fun remove(): Int {
        val returnVal = heap[0]
        heap[0] = heap.last()
        heap.removeLast()

        var pos = 0

        while (true) {
            val child1 = if(heap.indices.last >= (2 * pos + 1)) (2 * pos + 1) else -1
            val child2 = if(heap.indices.last >= (2 * pos + 2)) (2 * pos + 2) else -1

            val maxChildIndex = when {
                child1 == -1 && child2 == -1 -> -1
                child2 == -1 || heap[child1] >= heap[child2] -> child1
                else -> child2
            }

            if(maxChildIndex == -1 || heap[maxChildIndex] <= heap[pos]) {
                break
            } else {
                swap(heap, pos, maxChildIndex)
                pos = maxChildIndex
            }
        }
        return returnVal
    }

    repeat(reader.readLine().trim().toInt()) {
        val input = reader.readLine().trim().split(' ').map { it.toInt() }
        when (input[0]) {
            0 -> push(input[1])
            1 -> writer.println(remove())
        }
        val bkdfd = 1
    }


    writer.close()
}


private fun <T> swap(list: MutableList<T>, pos1: Int, pos2: Int) {
    val temp = list[pos1]
    list[pos1] = list[pos2]
    list[pos2] = temp
}