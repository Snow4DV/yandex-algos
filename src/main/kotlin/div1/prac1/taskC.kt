package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.IllegalArgumentException

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)
    var pivot = normalize(reader.readLine())


    reader.readLines().forEach { line ->
        val phoneNo = normalize(line)
        writer.println(if (phoneNo == pivot) "YES" else "NO")
    }

    writer.close()
}

private fun normalize(phoneNumber: String): String {
    val filteredPhoneNumber = phoneNumber.trim().filter { it.isDigit() }
    return when (filteredPhoneNumber.length) {
        11 -> filteredPhoneNumber.substring(1)
        7 -> "495$filteredPhoneNumber"
        else -> throw IllegalArgumentException()
    }
}