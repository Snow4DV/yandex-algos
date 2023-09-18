package div2B.prac3

import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    var lineScanner = Scanner(s.nextLine())

    val set = HashSet<Int>()

    while(lineScanner.hasNextInt()) {
        val newInt = lineScanner.nextInt()
        println(if (set.contains(newInt)) "YES" else "NO")
        set.add(newInt)
    }
}