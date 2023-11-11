package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    fun readInput() = ArrayDeque(reader.readLine().trim().split(' ').filter { it.isNotEmpty() }.map { it.trim().toInt() })

    val player1 = readInput()
    val player2 = readInput()

    var step = 0

    while(++step <= 1_000_000) {
        val card1 = player1.removeFirst()
        val card2 = player2.removeFirst()

        if(card1 == 0 && card2 == 9 || (card1 > card2 && !(card2 == 0 && card1 == 9))) {
            player1.addLast(card1)
            player1.addLast(card2)
        } else if(card2 == 0 && card1 == 9 || card2 > card1){
            player2.addLast(card1)
            player2.addLast(card2)
        }

        if(step == 1_000_000) {
            writer.println("botva")
            break
        } else if(player1.size == 0) {
            writer.println("second $step")
            break
        } else if(player2.size == 0) {
            writer.println("first $step")
            break
        }
    }




    writer.close()
}
