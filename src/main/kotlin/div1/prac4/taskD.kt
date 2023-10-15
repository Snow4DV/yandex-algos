import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet

fun main(args: Array<String>) {
    //val reader = BufferedReader(InputStreamReader(System.`in`))
    //val writer = PrintWriter(System.out)

    val scanner = Scanner(System.`in`)



    val arr = Array(scanner.nextLine().toInt()) {scanner.nextInt()}

    scanner.nextLine()

    repeat(scanner.nextLine().toInt()) {
        val ind = scanner.nextInt() - 1
        arr[ind] -= 1
    }

    arr.forEach { println(if (it < 0) "YES" else "NO") }
}