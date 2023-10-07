package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val aDistBetweenTrains1 = reader.getNum()
    val bDistBetweenTrains2 = reader.getNum()
    val nCountOfTrains1 = reader.getNum()
    val mCountOfTrains2 = reader.getNum()

    // В худшем случае 1000 поездов насчитает, а если у каждого интервал 1000 минут, то 1001000 - макс время ожидания
    // т.е. ограничение < 10^7 -> зайдет перебор

    var minTime = -1
    var maxTime = -1

    for(waitTime in 1..10000000) { // минимум минуту она прождет, ибо видела минимум 1 поезд по инварианту входных данных
        // считаем лучший случай - она пришла на станцию, и сразу приехал поезд
        val bestCaseT1Count = 1 + (waitTime - 1) / (aDistBetweenTrains1 + 1)
        val bestCaseT2Count = 1 + (waitTime - 1) / (bDistBetweenTrains2 + 1)
        // считаем худший случай - она пришла, а поезд только что отъехал
        val worstCaseT1Count = (waitTime) / (aDistBetweenTrains1 + 1)
        val worstCaseT2Count = (waitTime) / (bDistBetweenTrains2 + 1)

        if(nCountOfTrains1 in worstCaseT1Count..bestCaseT1Count && mCountOfTrains2 in worstCaseT2Count..bestCaseT2Count) {
            if(minTime == -1) minTime = waitTime
            maxTime = waitTime
        }

    }

    println(if (minTime != -1) "$minTime $maxTime" else "-1")

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

