package div2B.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

private class HofTree() {
    var parent: HofTree? = null
    var left: HofTree? = null
    var right: HofTree? = null
    var key = ""

    constructor(parent: HofTree? = null, key: String = "") : this() {
        this.parent = parent
        this.key = key
    }

    fun forEachLeafInOrder(printFun: (String) -> Unit) {
        left?.forEachLeafInOrder(printFun)
        if(left == null && right == null) {
            printFun(key)
        }
        right?.forEachLeafInOrder(printFun)
    }


}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    repeat(n) {
        val head = HofTree()
        var curNode = head
        var leafCount = 1
        reader.readLine().trim().forEach { command ->
            when(command) {
                'D' -> {
                    if(curNode.left == null) {
                        val newHofTree = HofTree(curNode, curNode.key + "0")
                        curNode.left = newHofTree
                        curNode = newHofTree
                    } else if(curNode.right == null) {
                        val newHofTree = HofTree(curNode, curNode.key + "1")
                        curNode.right = newHofTree
                        curNode = newHofTree
                        leafCount++
                    } else throw IllegalStateException()
                }
                'U' -> {
                    while (curNode.parent != null && curNode.parent!!.right == curNode) {
                        curNode = curNode.parent!!
                    }
                    if(curNode.parent != null && curNode.parent!!.left == curNode) {
                        curNode = curNode.parent!!
                        val newHofTree = HofTree(curNode, curNode.key + "1")
                        curNode.right = newHofTree
                        curNode = newHofTree
                        leafCount++
                    }
                }
            }
        }
        writer.println(leafCount)
        head.forEachLeafInOrder(writer::println)
    }



    writer.close()




}