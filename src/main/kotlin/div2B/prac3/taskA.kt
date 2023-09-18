package div2B.prac3

import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    var lineScanner = Scanner(s.nextLine())

    val set1: HashSet<Int> = HashSet()
    while(lineScanner.hasNextInt()) {
        set1.add(lineScanner.nextInt())
    }

    val set2: HashSet<Int> = HashSet()
    lineScanner = Scanner(s.nextLine())


    var counter = 0
    while(lineScanner.hasNextInt()) {
        val newInt = lineScanner.nextInt()
        if(set1.contains(newInt)) {
            counter++
        }
    }

    println(counter)






}