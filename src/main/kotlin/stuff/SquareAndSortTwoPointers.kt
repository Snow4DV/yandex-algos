package stuff

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toIntArray()

    var startOfNotNegNumbersIndex = input.size

    for (i in input.indices) {
        if(startOfNotNegNumbersIndex == input.size && input[i] >= 0) startOfNotNegNumbersIndex = i
        input[i] *= input[i]
    }

    var pointer1 = startOfNotNegNumbersIndex - 1
    var pointer2 = startOfNotNegNumbersIndex

    while(!(pointer1 < 0 && pointer2 >= input.size)) {
        if(pointer2 >= input.size || pointer1 >= 0 && input[pointer1] <= input[pointer2]) {
            writer.println(input[pointer1])
            pointer1--
        } else if(pointer1 < 0 || input[pointer2] <= input[pointer1]) {
            writer.println(input[pointer2])
            pointer2++
        }
    }


    writer.close()
}