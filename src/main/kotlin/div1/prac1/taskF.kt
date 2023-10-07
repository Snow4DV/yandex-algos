package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.ceil
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    var (laptopWidth1, laptopHeight1, laptopWidth2, laptopHeight2) =
        reader.readLine().trim().split(' ').map { it.toInt() }

    val tries = mutableListOf<Pair<Int, Pair<Int, Int>>>()

    for(i in 0..3) {
        val swap1 = i % 2 == 0
        val swap2 = i shr 1 == 0
        val lapH1 = if (swap1) laptopWidth1 else laptopHeight1
        val lapW1 = if (swap1) laptopHeight1 else laptopWidth1
        val lapH2 = if (swap2) laptopWidth2 else laptopHeight2
        val lapW2 = if (swap2) laptopHeight2 else laptopWidth2
        tries.add(getSquareToDims(lapH1, lapW1, lapH2, lapW2) )
    }


    val best = tries.minWith(compareBy { it.first })!! // fix for Kotlin 1.4.30
    writer.println("${best.second.first} ${best.second.second}")




    writer.close()
}


private fun getSquareToDims(laptopWidth1: Int, laptopHeight1: Int, laptopWidth2: Int, laptopHeight2: Int) : Pair<Int, Pair<Int, Int>> {
    val width1 = max(laptopWidth1, laptopWidth2)
    val height1 = (laptopHeight1 + laptopHeight2)
    val s1 = width1 * height1
    val width2 = max(laptopHeight1, laptopHeight2)
    val height2 = (laptopWidth1 + laptopWidth2)
    val s2 = width2 * height2
    return if(s1 < s2) s1 to Pair(width1, height1) else s2 to Pair(width2, height2)
}
