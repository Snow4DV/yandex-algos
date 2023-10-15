import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.collections.HashSet

fun main(args: Array<String>) { // TLs by some reason. Honestly i can't think of any solution faster than that: O((m+n)logn + (m+n))
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (nAnya, mBorya) = reader.readLine().trim().split(' ').map { it.toInt() }

    val anyaSet = HashSet<Int>()
    val boryaSet = HashSet<Int>()


    repeat(nAnya) {
        anyaSet.add(reader.getNum())
    }

    repeat(mBorya) {
        boryaSet.add(reader.getNum())
    }


    val both = anyaSet.intersect(boryaSet)
    val onlyAnya = anyaSet - boryaSet
    val onlyBorya = boryaSet - anyaSet

    fun taskPrint(set: Set<Int>) {
        writer.println(set.size)
        writer.println(set.sorted().joinToString(" "))
    }


    taskPrint(both)
    taskPrint(onlyAnya)
    taskPrint(onlyBorya)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()