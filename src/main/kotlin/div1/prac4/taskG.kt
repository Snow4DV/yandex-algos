import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val map = HashMap<String, Long>() // <Username, Balance>


    var line: String?


    while(reader.readLine().also { line = it } != null) {
        val split = line!!.split(" ")

        when(val command = split[0]) {
            "DEPOSIT", "WITHDRAW" -> {
                map[split[1]] = (map[split[1]] ?: 0) + split[2].toLong() * (if (command == "DEPOSIT") 1 else -1)
            }
            "BALANCE" -> {
                writer.println(map[split[1]] ?: "ERROR")
            }
            "TRANSFER" -> {
                map[split[1]] = (map[split[1]] ?: 0) - split[3].toLong() // from
                map[split[2]] = (map[split[2]] ?: 0) + split[3].toLong() // to
            }
            "INCOME" -> {
                map.keys.forEach {
                    if ((map[it] ?: 0) > 0) {
                        map[it] = (map[it]!! * (1 + split[1].toLong() / 100.0)).toLong()
                    }
                }
            }
        }
    }


    writer.close()
}

