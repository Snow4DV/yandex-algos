package div2B.prac2

import java.lang.Math.pow
import java.util.*
import kotlin.math.*

fun main() {

    val s = Scanner(System.`in`)

    val nums = mutableListOf<Int>()

    var num = s.nextInt()

    while(num != 0) {
        nums.add(num)
        num = s.nextInt()
    }

    if(nums.size == 0) {
        println(0)
        return
    }

    var max = nums[0]
    var count = 1

    for(i in 1 until nums.size) {
        if(nums[i] > max) {
            max = nums[i]
            count = 1
        } else if(nums[i] == max) {
            count++
        }
    }

    print(count)

}