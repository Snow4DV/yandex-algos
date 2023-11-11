package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.max

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val k = reader.getNum()
    val line = reader.readLine()

    var left = 0
    var right = 0

    val map = HashMap<Char, Int>()
    map[line[0]] = 1


    var bestLength = 0




    var curValuesSum = 1
    while(right <= line.indices.last) {
        //find current maxCharCount and otherCharCount
        val (maxChar, maxCharCount) = map.maxBy { it.value } // O(26)
        val otherCharCount = curValuesSum - maxCharCount // O(26)
        // check current substr and move ptr
        if(otherCharCount <= k) { // good string. compare to prev best and move right ptr
            val curSubstrLength = right - left + 1
            bestLength = max(bestLength, curSubstrLength)

            right++
            if(right < line.length) {
                map[line[right]] = (map[line[right]] ?: 0) + 1
                curValuesSum++
            }
        } else { // bad string. move left ptr
            map[line[left]] = map[line[left]]!! - 1
            curValuesSum--
            if(map[line[left]] == 0) map.remove(line[left])
            left++
        }
    }

    writer.println(bestLength)





    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()