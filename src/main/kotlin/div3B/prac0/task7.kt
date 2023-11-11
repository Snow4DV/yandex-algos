package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.roundToInt

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val aSeconds = reader.readLine().trim().split(':').map { it.toInt() }.toSeconds()
    val bSeconds = reader.readLine().trim().split(':').map { it.toInt() }.toSeconds()
    var cSeconds = reader.readLine().trim().split(':').map { it.toInt() }.toSeconds()

    if(cSeconds < aSeconds) {
        cSeconds += 24 * 3600
    }

    val bServerTimeSeconds = bSeconds
    val bClientTimeSeconds = (aSeconds + cSeconds)/2.0 // est

    val deltaBetweenBAndC = cSeconds - bClientTimeSeconds

    var newTime = bServerTimeSeconds + deltaBetweenBAndC

    if(newTime > 24 * 3600) {
        newTime -= 24 * 3600
    } else if(newTime < 0) {
        newTime += 24 * 3600
    }

    var newTimeRounded = newTime.roundToInt()

    val newHours = newTimeRounded / 3600
    newTimeRounded %= 3600
    val newMinutes = newTimeRounded / 60
    newTimeRounded %= 60
    val newSeconds = newTimeRounded

    writer.println(String.format("%02d:%02d:%02d", newHours, newMinutes, newSeconds))


    writer.close()
}

private fun List<Int>.toSeconds(): Int {
    if(this.size != 3) throw IllegalArgumentException()
    return this[0] * 3600 + this[1] * 60 + this[2]
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()