package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)
    val list = reader.readLine().trim().split(' ').map { it.toInt() }



    for(i in 1 until list.size) {
        if(list[i-1] >= list[i]) {
            writer.println("NO")
            break
        }

        if(i == list.indices.last) {
            writer.println("YES")
        }
    }

    if(list.size <= 1) writer.println("YES")

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

