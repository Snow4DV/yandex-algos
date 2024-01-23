package stuff

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.sin

fun main2() {

    val a = intArrayOf(3,5,2,6,1,4)

    var i = 2
    var c = 0
    for(u in 0 until 100) {
        var j = a[i-1]
        a[i-1] = i
        i = j
        c++
    }

    println(c)

}

fun main3() {
    var x1 = 1.5
    var y1 = 0.5
    var d1 = 0.0
    if(y1 < x1 * -1 + 3 || x1 > 1.5) {
        d1 = x1 - y1
    } else {
        d1 -= x1
    }

    println(d1)
}
fun main5() {
    val a = intArrayOf(-3,4,-1,-5,1,0)

    var s = 0
    var m = 6
    var j = 0
    for(i in 1..m) {
        if(a[i-1] < 0) {
            a[i-1] = 0
            println(a[i-1])
        } else {
            j++
            println(a[i-1])
        }
    }
}

fun main() {
    var n = 31.0
    var xs = 1.0
    var x = 0.0
    while(abs(x - xs) > 0.00000001) {
        xs = x
        var y = 0.57/n
        x = 3.2 * sin(y)/(4 * (1-y) * (4- (1+y) * (1+y))  - 12)
        n += 0.7
    }

    println(x)

}