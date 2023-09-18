package div2B.prac3

import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)

    var n = s.nextLine().toInt()

    val set = HashSet<Int>()

    repeat(n) {
        set.add(it+1)
    }


    while(true) {
        val digitsOrHelpLine = s.nextLine()
        if(digitsOrHelpLine != "HELP") { // digits

            val checkNums = HashSet<Int>()
            digitsOrHelpLine.split(" ").forEach {
                checkNums.add(it.toInt())
            }

            if(s.nextLine() == "YES") {
                val setIterator = set.iterator()
                while(setIterator.hasNext()) {
                    if(!checkNums.contains(setIterator.next())) {
                        setIterator.remove()
                    }
                }
            } else { // NO
                for(num in checkNums) {
                    set.remove(num)
                }
            }
        } else { //help
            break
        }
    }

    set.forEach { println(it) }

}