package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.ceil

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (flatNo1, floorsCount, flatNo2, entranceNo2, floorNo2) =
        reader.readLine().trim().split(' ').map { it.toLong() }

    var entranceNo1 = 0L
    var floorNo1 = 0L

    // С этой оптимизацией придется рассматривать много краевых случаев
    // (из-за валидации, если б данные всегда позволяли однозначно определить квартиру, это бы всегда работало, и мы бы перебирали очень немного значений),
    // я решил в итоге просто перебрать, раз перебор заходит
    //val minPossibleCountOfFlats = ceil(flatNo2.toDouble() / floorNo2).toInt()
    //val maxPossibleCountOfFlats = if (floorNo2 != 1) ((ceil(flatNo2.toDouble() / (floorNo2 - 1))).toInt() - 1) else 1000000

    val minPossibleCountOfFlats = 1
    val maxPossibleCountOfFlats = 10000000

    val possibleEntrances = HashSet<Long>()
    val possibleFloors = HashSet<Long>()
    var existFlag = false

    for (curFlatsCount in minPossibleCountOfFlats..maxPossibleCountOfFlats) {
        val curCountOfFlatsInEntrance = floorsCount * curFlatsCount

        val flatNo2CanExist = flatCanExist(floorsCount, curFlatsCount, entranceNo2, floorNo2, flatNo2)

        if(!flatNo2CanExist) continue
        else existFlag = true

        val curEntranceNo1 = ceil(flatNo1.toDouble() / curCountOfFlatsInEntrance).toLong()
        val curFloorNo1 = ceil((flatNo1 - (curEntranceNo1 - 1) * curCountOfFlatsInEntrance).toDouble() / curFlatsCount).toLong()

        if(possibleEntrances.size < 2) {
            possibleEntrances.add(curEntranceNo1)
        }

        if(possibleFloors.size < 2) {
            possibleFloors.add(curFloorNo1)
        }

        if(possibleFloors.size > 1 && possibleEntrances.size > 1) break
    }


    if(possibleEntrances.size == 1) {
        entranceNo1 = possibleEntrances.first()
    }

    if(possibleFloors.size == 1) {
        floorNo1 = possibleFloors.first()
    }

    if(floorsCount == 1L) floorNo1 = 1

    if(flatNo2 == flatNo1) {
        floorNo1 = floorNo2
        entranceNo1 = entranceNo2
    }

    if(!existFlag) {
        entranceNo1 = -1
        floorNo1 = -1
    }

    writer.println("$entranceNo1 $floorNo1")



    writer.close()
}

private fun flatCanExist(floorsCount: Long, flatsPerFloorCount: Int, entranceNo: Long, floorNo: Long, flatNo: Long): Boolean {
    if(floorNo > floorsCount) return false
    val curCountOfFlatsInEntrance = floorsCount * flatsPerFloorCount
    val minFlatNoOnFloorOf2 =  (entranceNo - 1) * curCountOfFlatsInEntrance + (floorNo - 1) * flatsPerFloorCount + 1
    val maxFlatNoOnFloorOf2 =  (entranceNo - 1) * curCountOfFlatsInEntrance + floorNo * flatsPerFloorCount
    return flatNo in minFlatNoOnFloorOf2..maxFlatNoOnFloorOf2
}