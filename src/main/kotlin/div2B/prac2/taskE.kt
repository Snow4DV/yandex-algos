package div2B.prac2

import java.util.*

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)

    val n = s.nextInt()


    val folders = MutableList(n) {s.nextInt()}

    if(n == 0) {
        println(0)
        return
    }
    val maxIndex = folders.indices.maxBy { folders[it] } ?: -1
    folders.removeAt(maxIndex)
    println(folders.sum())



}