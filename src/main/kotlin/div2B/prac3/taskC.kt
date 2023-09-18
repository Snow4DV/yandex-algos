package div2B.prac3

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)
    var lineScanner = Scanner(s.nextLine())

    val set = HashSet<Int>()
    val unique = HashSet<Int>()
    val ordered = LinkedList<Int>()

    while(lineScanner.hasNextInt()) {
        val num = lineScanner.nextInt()
        val setContains = set.contains(num)
        val uniqueContains = unique.contains(num)
        ordered.add(num)
        if(!setContains && !uniqueContains) {
            set.add(num)
            unique.add(num)
        } else if(setContains && uniqueContains) {
            unique.remove(num)
        }
    }

    for(num in ordered) {
        if(unique.contains(num)) {
            println(num)
        }
    }

}