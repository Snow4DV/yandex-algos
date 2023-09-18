package div2B.prac3

import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val s = Scanner(System.`in`)

    val witnesses = mutableListOf<String>()

    val nWitnesses = s.nextLine().toInt()

    repeat(nWitnesses) {
        witnesses.add(s.nextLine())
    }

    val nLicensePlates = s.nextLine().toInt()

    var maxCount = -1
    var maxPlates = mutableListOf<String>()

    repeat(nLicensePlates) {
        val plate = HashSet<Char>()
        val plateStr = s.nextLine()
        plateStr.toCharArray().forEach { plate.add(it) }

        var count = 0


        witnesses.forEach outer@{
            var fail = false
            it.toCharArray().forEach inner@{
                if(!plate.contains(it)) {
                    fail = true
                    return@inner
                }
            }
            if(!fail) count++
        }

        if(count > maxCount) {
            maxCount = count
            maxPlates.clear()
            maxPlates.add(plateStr)
        } else if(count == maxCount) {
            maxPlates.add(plateStr)
        }
    }

    maxPlates.forEach { println(it) }
}