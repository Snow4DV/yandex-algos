package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.max

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val studentsCount = reader.getNum()

    val firstPlaceIndex = 0
    val lastPlaceIndex = studentsCount - 1 /* mistake: desksCount * 2 - 1*/

    val vars = reader.getNum()
    val petyaDesk = reader.getNum() - 1
    val petyaDeskPlace = reader.getNum() - 1
    val petyaPlace = petyaDesk * 2 + petyaDeskPlace


    val possiblePlaceRight = petyaPlace + vars
    val rightDesk = possiblePlaceRight/2


    val possiblePlaceLeft = petyaPlace - vars
    val leftDesk = possiblePlaceLeft/2



    val result = when {
        possiblePlaceRight <= lastPlaceIndex && ((rightDesk - petyaDesk) <= (petyaDesk - leftDesk) || possiblePlaceLeft < firstPlaceIndex)  -> "${possiblePlaceRight/2 + 1} ${possiblePlaceRight%2 + 1}"
        possiblePlaceLeft >= firstPlaceIndex && ((rightDesk - petyaDesk) > (petyaDesk - leftDesk) || possiblePlaceRight > lastPlaceIndex) -> "${possiblePlaceLeft/2 + 1} ${possiblePlaceLeft%2 + 1}"
        else -> "-1"
    }

    writer.println(result)

    writer.close()
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()