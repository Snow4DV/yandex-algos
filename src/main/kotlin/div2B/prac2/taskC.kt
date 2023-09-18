package div2B.prac2

import java.util.*
import kotlin.math.*

fun main() {

    val s = Scanner(System.`in`)

    val str = s.nextLine()

    if(str.length == 1) {
        print(0)
        return
    }

    var leftPrice = 0

    val leftPartLastIndex: Int = if (str.length % 2 == 0) floor(((str.length - 1) / 2.0)).toInt() else ((str.length - 1)/2 - 1)
    val rightPartFirstIndex: Int = if (str.length % 2 == 0) ceil(((str.length - 1) / 2.0)).toInt() else ((str.length - 1)/2 + 1)

    for(i in 0..leftPartLastIndex) {// left part
        val mirroringIndex = str.length - 1 - i

        if(str[i] != str[mirroringIndex]) {
            leftPrice++
        }
    }

    var rightPrice = 0

    for(i in str.length - 1 downTo rightPartFirstIndex) {// right part
        val mirroringIndex = str.length - i - 1
        if(str[i] != str[mirroringIndex]) {
            rightPrice++
        }
    }

    print(min(leftPrice, rightPrice))


    return
}