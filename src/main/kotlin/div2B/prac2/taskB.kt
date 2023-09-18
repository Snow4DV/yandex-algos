package div2B.prac2

import java.lang.Math.pow
import java.util.*
import kotlin.math.*

fun goLeft(distances: Array<Int>, buildings: List<Int>, buildingIndex: Int) {
    for(i in buildingIndex downTo 0) {
        val distance = buildingIndex - i
        if(distance < distances[i]) distances[i] = distance
        else return
    }
}
fun goRight(distances: Array<Int>, buildings: List<Int>, buildingIndex: Int) : Int {
    for(i in buildingIndex + 1 until buildings.size) {
        if(buildings[i] == 2) { // Got to the shop
            return i
        }
        val distance = i - buildingIndex
        if(distance < distances[i]) distances[i] = distance
    }
    return -1 // got to the end and no shop was met on our way
}
fun main() {

    val s = Scanner(System.`in`)

    val buildings = mutableListOf<Int>()

    repeat(10) {
        buildings.add(s.nextInt())
    }

    val distances = Array(buildings.size) {buildings.size}


    var currentBuildingIndex: Int = buildings.indexOfFirst { it == 2 }

    while(currentBuildingIndex != -1) {
        goLeft(distances, buildings, currentBuildingIndex)
        currentBuildingIndex = goRight(distances, buildings, currentBuildingIndex)
    }


    println(distances.filterIndexed { index, i -> buildings[index] == 1 }.max())


    return


}