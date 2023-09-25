package div2B.prac7

import java.io.*
import java.util.*

/*
Решение с помощью сортировки событий - заходит
 */
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, m) = reader.readLine().trim().split(' ').map {it.toInt()}

    val events = ArrayList<List<Int>>() // события: -1 = сегмент начался, 0 = посадили котенка, 1 = сегмент закончился

    reader.readLine().trim().split(' ').map { it.toInt() }.forEach {
        events.add(listOf(it, -1, 0))
    }

    for(i in 0 until m) {
        val (left, right) = reader.readLine().trim().split(' ').map(String::toInt)
        events.add(listOf(left, i, -1))
        events.add(listOf(right, i, 1))
    }

    events.sortWith { o1, o2 -> // [x=0,index=1,type=2]
        val comp1 = o1[0].compareTo(o2[0])
        if (comp1 != 0) comp1 else o1[2].compareTo(o2[2])
    }


    var kittensCount = 0
    var array = Array(m) {0}
    // события: -1 = сегмент начался, 0 = посадили котенка, 1 = сегмент закончился
    for(event in events) { // [x=0,index=1,type=2]
        when(event[2]) {
            -1 -> array[event[1]] = kittensCount
            0 -> {
                kittensCount++
            }
            1 -> {
                array[event[1]] = kittensCount - array[event[1]]
            }
        }
    }

    for(i in 0 until m) {
        writer.println(array[i])
    }

    writer.close()
}
