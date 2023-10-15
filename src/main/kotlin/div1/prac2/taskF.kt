package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.getNum()
    val list = reader.readLine().trim().split(' ').map { it.toInt() }


    var minCountOfNumbers = list.size - 1
    var centerPoints = list.indices.last to list.indices.last

    for(center in (list.indices.first + list.indices.last)/2 - 1..list.indices.last) { // решение за квадрат
        if (list.indices.last - center <= center) {
            val newNumsCount = checkCenter(center, list, true)
            if(newNumsCount < minCountOfNumbers) {
                centerPoints = center to center
                minCountOfNumbers = newNumsCount
            }
        }
        if (list.indices.last - center - 1 <= center) {
            val newNumsCount = checkCenter(center, list, false)
            if(newNumsCount < minCountOfNumbers) {
                centerPoints = center to center + 1
                minCountOfNumbers = newNumsCount
            }
        }
    }

    println(minCountOfNumbers)
    val countOfNumbersOnTheRight = list.indices.last - centerPoints.second
    for(i in (centerPoints.first - 1 - countOfNumbersOnTheRight) downTo 0) {
        if(i != (centerPoints.first - 1 - countOfNumbersOnTheRight)) print(' ')
        print(list[i])
    }

    /*
    val centerIndexes: Pair<Int, Int> = if((right - left + 1) % 2 == 1) (right + left)/2 to -1 else (right + left)/2 to (right + left)/2 + 1

    var count = if(centerIndexes.second == -1) { // only one center
        val countOfElementsOnTheLeft = centerIndexes.first
        val countOfElementsOnTheRight = list.size - countOfElementsOnTheLeft - 1
        countOfElementsOnTheLeft - countOfElementsOnTheRight // req elements
    } else {
        val countOfElementsOnTheLeft = centerIndexes.first
        val countOfElementsOnTheRight = list.size - countOfElementsOnTheLeft - 2
        countOfElementsOnTheLeft - countOfElementsOnTheRight // req elements
    }

    println(count)

     */
    writer.close()
}

private fun checkCenter(center: Int, list: List<Int>, oneCenter: Boolean): Int {
    if(!oneCenter && center + 1 > list.indices.last || !oneCenter && list[center] != list[center+1]) return Integer.MAX_VALUE
    var left = center - 1
    var right = center + (if (oneCenter) 1 else 2)
    while(right <= list.indices.last) {
        if(list[right] != list[left]) return Integer.MAX_VALUE
        left--
        right++
    }
    val countOfNumbersOnTheRight = list.indices.last - (if (oneCenter) center else (center+1))
    return center - countOfNumbersOnTheRight
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()

