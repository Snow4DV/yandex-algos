package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.getNum()
    val list = reader.readLine().trim().split(' ').map { it.toInt() }

    var winnerThrowDist = list.max()

    var winnerAlreadyThrew = false

    var maxDistanceOfVasily = -1

    for (i in 0 until list.indices.last) {
        val conditions = listOf(
            list[i] % 10 == 5, // оканчивается на 5
            winnerAlreadyThrew, // победитель уже бросал до Василия
            i < list.indices.last && list[i+1] < list[i] // следующий участник бросил хуже
        )

        if(conditions.all { it }) maxDistanceOfVasily = max(maxDistanceOfVasily, list[i])

        if(list[i] == winnerThrowDist) winnerAlreadyThrew = true
    }

    writer.println(if (maxDistanceOfVasily != -1) (list.count { it > maxDistanceOfVasily } + 1) else 0)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

