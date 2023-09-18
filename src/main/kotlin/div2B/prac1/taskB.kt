package div2B.prac1

import java.util.*
import kotlin.math.abs
import kotlin.math.min

fun main() {

    val scanner = Scanner(System.`in`)

    val (stationsCount, from, dest) = listOf(scanner.nextInt(), scanner.nextInt() - 1, scanner.nextInt() - 1)
    // stations 0,1,2,3,4,5  ; len(st) =6

    if(from == dest) {
        print(0)
        return
    }

    val edgeStation = if (from > (stationsCount - 1) - from) stationsCount - 1 else 0
    val edgeStation2 = if (from > (stationsCount - 1) - from) 0 else stationsCount - 1

    val straightLen = abs(dest - from)
    val withEdgeLen = abs(from - edgeStation) + abs(dest - edgeStation2) + 1

    //print("$straightLen $withEdgeLen $edgeStation \n")

    print(min(straightLen, withEdgeLen) - 1)
}