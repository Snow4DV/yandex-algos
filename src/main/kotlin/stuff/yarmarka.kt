package stuff

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.round


fun main100() {
    val array = Array(8) { IntArray(8) }

    val reader = BufferedReader(InputStreamReader(System.`in`))
    repeat(8) { i ->
        reader.readLine().trim().forEachIndexed { j, char ->
            array[i][j] = if (char == '*') 1 else 0
        }
    }

    val init = arrayOf(intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(1, 0))


    fun check(i: Int, j: Int, cur: Array<IntArray>): Boolean {
        if (i + cur.size - 1 !in array.indices || j + cur[0].size - 1 !in array[0].indices) return false
        for (k in cur.indices) {
            for (m in cur[0].indices) {
                if (cur[k][m] == 1 && array[i + k][j + m] == 1) return false
            }
        }
        return true
    }


    var counter = 0
    for (i in array.indices) {
        for (j in array[i].indices) {
            var cur = init.clone()
            repeat(8) {
                if (check(i, j, cur)) counter++
                cur = if (it == 3) mirrorMatrix(cur) else rotateMatrix(cur)
            }
        }
    }

    println(counter / 2)
}


private fun rotateMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val rowSize = matrix.size
    val colSize = matrix[0].size

    val rotated = Array(colSize) { IntArray(rowSize) }

    for (i in 0 until rowSize) {
        for (j in 0 until colSize) {
            rotated[j][rowSize - i - 1] = matrix[i][j]
        }
    }

    return rotated
}

private fun mirrorMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val rowSize = matrix.size
    val colSize = matrix[0].size

    val mirrored = Array(rowSize) { IntArray(colSize) }

    for (i in 0 until rowSize) {
        for (j in 0 until colSize) {
            mirrored[i][j] = matrix[i][colSize - j - 1]
        }
    }

    return mirrored
}


fun mainBCars() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (nCars, time, sLen) = reader.readLine().trim().split(' ').map { it.toInt() }

    val speeds = reader.readLine().trim().split(" ").map { it.toInt() }


    val epsilon = 0.0000001
    var sum = 0
    for (i in 1 until speeds.size) {
        val speed = speeds[i]
        val delta = speeds[0] - speed
        if (delta > 0) {
            val countOfBypasses = time.toDouble() / (sLen.toDouble() / delta)
            val countOfBypassesInt = countOfBypasses.toInt()
            sum += countOfBypassesInt
            if (abs(countOfBypasses - countOfBypassesInt) < epsilon && countOfBypassesInt > 0) {
                sum--
            }
        }
    }

    println(sum)
}


fun mainC() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val n = reader.readLine().trim().toInt()
    val array = reader.readLine().trim().split(" ").map { it.toInt() }.toIntArray()

    var counter = 0

    for (i in array.indices) {
        for (j in i + 1 until array.size) {
            val iNum = array[i]
            val jNum = array[j]
            array[i] = jNum
            array[j] = iNum


            val cloned = array.clone()
            val mergeSorter = mergeSorter(cloned, 0, array.size - 1)
            counter += mergeSorter

            array[i] = iNum
            array[j] = jNum
        }
    }

    val a = counter
    val b = (n * (n - 1) * 0.5).toInt()

    val divider = findGreatestCommonDivisor(a, b)
    println("${a/divider}/${b/divider}")
}

fun findGreatestCommonDivisor(a: Int, b: Int): Int {
    var num1 = a
    var num2 = b

    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }

    return num1
}
fun dumbCountInversions(array: IntArray): Int {
    var counter = 0
    for (i in array.indices) {
        for (j in i + 1 until array.size) {
            if (array[i] > array[j]) counter++
        }
    }
    return counter
}

private fun mergeCount(array: IntArray, l: Int, m: Int, r: Int): Int {
    val left = Arrays.copyOfRange(array, l, m + 1)
    val right = Arrays.copyOfRange(array, m + 1, r + 1)
    var i = 0
    var j = 0
    var k = l
    var swaps = 0
    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            array[k++] = left[i++]
        } else {
            array[k++] = right[j++]
            swaps += (m + 1) - (l + i)
        }
    }
    while (i < left.size) {
        array[k++] = left[i++]
    }
    while (j < right.size) {
        array[k++] = right[j++]
    }
    return swaps
}

private fun mergeSorter(array: IntArray, left: Int, right: Int): Int {

    var counter = 0
    if (left < right) {
        val mid = (left + right) / 2
        counter += mergeSorter(array, left, mid)
        counter += mergeSorter(array, mid + 1, right)
        counter += mergeCount(array, left, mid, right)
    }
    return counter
}


// last



fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, x) = reader.readLine().trim().split(" ").map { it.toInt() }

    val inputArray = reader.readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val inputArraySum = inputArray.sum()

    val doubleArray = DoubleArray(n) {inputArray[it] * (x.toDouble()/inputArraySum)}

    val roundedArray = IntArray(n) { round(doubleArray[it]).toInt() }
    var roundedSum = roundedArray.sum()

    val needToLower = (x - roundedSum) < 0

    val bestHeap = PriorityQueue<Pair<Double, Int>> { p1, p2 ->
        val p1Delta = if(needToLower) (p1.first - p1.first.toInt()) else (ceil(p1.first) - p1.first)
        val p2Delta = if(needToLower) (p2.first - p2.first.toInt()) else (ceil(p2.first) - p2.first)
        p1Delta.compareTo(p2Delta)
    }

    doubleArray.forEachIndexed { index, it ->
        bestHeap.add(it to index)
    }

    while(bestHeap.size > 0 && roundedSum != x) {
        val best = bestHeap.remove()
        if(needToLower) {
            roundedArray[best.second]--
            roundedSum--
        } else {
            roundedArray[best.second]++
            roundedSum++
        }
    }

    var deltaLeft = abs(roundedSum - x)
    for(i in roundedArray.indices) {
        if(deltaLeft <= 0) break
        if(roundedArray[i] > 0 && needToLower) {
            var temp = roundedArray[i]
            temp -= deltaLeft
            if(temp < 0) {
                deltaLeft -= roundedArray[i]
                roundedArray[i]
            } else {
                roundedArray[i] -= deltaLeft
                deltaLeft = 0
            }
        } else {
            roundedArray[i] += deltaLeft
            deltaLeft = 0
        }
    }

    println(roundedArray.joinToString(" "))


}