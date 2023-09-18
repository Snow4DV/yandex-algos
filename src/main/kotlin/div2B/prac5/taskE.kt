package div2B.prac5

import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val s = reader.readLine().trim().toLong()

    val arr1 = getListWithoutFirstFromReader(reader) // sorry
    val arr2 = getListWithoutFirstFromReader(reader)
    val arr3 = getListWithoutFirstFromReader(reader)



    var curK: Int

    var suitableI = -1
    var suitableJ = -1
    var suitableK = -1

    for(i in arr1.indices) {
        curK = arr3.indices.last
        val elemI = arr1[i].second
        for(j in arr2.indices) {
            val elemJ = arr2[j].second
            while(curK > 0 && arr3[curK - 1].second >= (s - elemI - elemJ)) { // find suitable
                curK--
            }


            if(arr3[curK].second.toLong() == (s - elemI - elemJ)) {
//                println("${arr1[i].first} ${arr2[j].first} ${arr3[curK].first}")
                if(compareIndexes(arr1[i].first, arr2[j].first, arr3[curK].first, suitableI, suitableJ, suitableK) < 0 || suitableI == -1) {
                    suitableI = arr1[i].first
                    suitableJ = arr2[j].first
                    suitableK = arr3[curK].first
                }
            }
        }
    }


    if(suitableI == -1) {
        println(-1)
    } else {
        println("$suitableI $suitableJ $suitableK")
    }
    writer.close()
}


fun compareIndexes(i1: Int, j1: Int, k1: Int, i2: Int, j2: Int, k2: Int): Int {
    val comp1 = i1.compareTo(i2)
    if(comp1 != 0) return comp1
    else {
        val comp2 = j1.compareTo(j2)
        if(comp2 != 0) return comp2
        else return k1.compareTo(k2)
    }
}


fun getListWithoutFirstFromReader(reader: BufferedReader): List<Pair<Int, Int>> {
    val list = reader.readLine().trim().split(' ').toMutableList()
    list.removeFirst()

    return list.mapIndexed() { index, item -> index to item.toInt() }.sortedBy { it.second }
}
