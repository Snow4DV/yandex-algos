package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (lines, columns, minesCount) = reader.readLine().trim().split(' ').map { it.toInt() }


    var mine: String?

    val field = Array(lines) { IntArray(columns) }

    fun increaseIfExistsAndNotMine(line: Int, column: Int) {
         if(line in 0 until lines && column in 0 until columns && field[line][column] != -1) {
             field[line][column]++
         }
    }

    fun increaseIfExistsAndNotMine(pair: Pair<Int, Int>) = increaseIfExistsAndNotMine(pair.first, pair.second)

    while(reader.readLine().also { mine = it } != null) {
        val (line, column) = mine!!.trim().split(' ').map { it.toInt() - 1}
        field[line][column] = -1
        for(i in line -1..line + 1) {
            for(j in column - 1..column + 1) {
                if(!(i == line && j == column)) {
                    increaseIfExistsAndNotMine(i, j)
                }
            }
        }
    }


    for(line in 0 until lines) {
        writer.print(field[line].joinToString(" ") { if (it != -1) "$it" else "*" })
        if(line != lines - 1) writer.println()
    }
    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

