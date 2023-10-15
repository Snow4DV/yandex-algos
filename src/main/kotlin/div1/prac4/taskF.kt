import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)



    val map = HashMap<String, TreeMap<String, Long>>()  // <Name, <Product, Amount>>



    var line: String?

    while(reader.readLine().also { line = it } != null) {
        val split = line!!.split(" ")
        val name = split[0]
        val product = split[1]
        val amount = split[2].toLong()

        if(!map.containsKey(name)) { // create nested hash map if it doesn't exist
            map[name] = TreeMap()
        }
        map[name]!![product] = (map[name]?.get(product) ?: 0) + amount
    }


    map.keys.sorted().forEach { name ->
        writer.println("$name:")
        map[name]?.keys?.forEach {product ->
            writer.println("$product ${map[name]!![product]}")
        }
    }


    writer.close()
}