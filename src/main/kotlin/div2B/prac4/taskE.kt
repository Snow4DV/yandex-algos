package div2B.prac4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val writer = PrintWriter(System.out)

    val n = reader.readLine().toInt()

    // funcs:
    // 1) get thread name by message number   {key - messageNo, value - threadName} - HashMap
    // 2) count amount of messages for each thread [key - threadName, value - count] - LinkedHashMap

    var msgsCount = 0
    val messages = HashMap<Int, String>() // messageNo - threadName
    val threadsMsgCounts = LinkedHashMap<String, Int>() // threadname - count


    var line: String?

    while(reader.readLine().also { line = it } != null) {
        val msgNo = line!!.toInt()
        if(msgNo == 0) { // new thread
            val threadName = reader.readLine()
            threadsMsgCounts[threadName] = 1 // starting msg is msg too
            messages[msgsCount++] = threadName

        } else { // new message to existing thread
            messages[msgsCount++] = messages[msgNo - 1 /*msgs count from 1*/]!!
            threadsMsgCounts[messages[msgNo - 1 /*msgs count from 1*/]!!] = threadsMsgCounts[messages[msgNo - 1 /*msgs count from 1*/]!!]!! + 1
        }
        reader.readLine() // skip message
    }

    threadsMsgCounts.maxBy { it.value }.let { writer.println(it!!.key) }

    writer.close()
}