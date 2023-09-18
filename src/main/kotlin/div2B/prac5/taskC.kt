package div2B.prac5

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (groupsCount, audCount) = reader.readLine().trim().split(' ').map { it.toInt() }

    val groups = reader.readLine().trim().split(' ').mapIndexed{ index, s -> index to s.toInt() + 1 }.sortedBy { it.second }
    val auds = reader.readLine().trim().split(' ').mapIndexed{ index, s -> index to s.toInt() }.sortedBy { it.second }



    val hashMap = HashMap<Int, Int>() // <aud, group>


    // 1 2 2 3 4 5 5 6 - аудитории
    // 2 3 3 4 - люди

    var curAud = 0

    for(group in groups) {
        val (index, studentsCount) = group
        while(curAud < auds.size && auds[curAud].second < studentsCount) {
            curAud++
        }

        if(curAud < auds.size && auds[curAud].second >= studentsCount) {
            hashMap[index + 1] = auds[curAud].first + 1// store it with correct indexes starting with 1
            curAud++
        }
    }

    writer.println(hashMap.size)

    for(n in 1..groupsCount) {
        writer.println(hashMap[n] ?: 0)
    }

    writer.close()
}

