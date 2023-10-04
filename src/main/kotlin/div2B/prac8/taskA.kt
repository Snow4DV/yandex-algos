package div2B.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

private class BinSTNode() {
    var key: Int? = null
    var left: BinSTNode? = null
    var right: BinSTNode? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }

    fun insertRec(curNode: BinSTNode = this, x: Int, level: Int = this.level): BinSTNode? {
        val curKey = curNode.key

        if(curKey == null) {
            curNode.key = x
            return curNode
        }

        return if (curKey == x) {
            null
        } else if (x < curKey) {
            if(curNode.left != null) {
                insertRec(curNode.left!!, x, level + 1)
            } else {
                BinSTNode(x, level + 1).also { curNode.left = it }
            }
        } else {
            if(curNode.right != null) {
                insertRec(curNode.right!!, x, level + 1)
            } else {
                BinSTNode(x, level + 1).also { curNode.right = it }
            }
        }
    }

    fun contains(node: BinSTNode? = this, value: Int): Boolean {
        return if(node?.key == null) {
            false
        } else if(node.key == value) {
            true
        } else if(value < node.key!!) {
            node.contains(node.left, value)
        } else {
            node.contains(node.right, value)
        }
    }

    fun forEachAsc(forEachFun: (BinSTNode) -> Unit, node: BinSTNode = this) {
        node.left?.let { forEachAsc(forEachFun, it) }
        forEachFun(node)
        node.right?.let { forEachAsc(forEachFun, it) }
    }


}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val tree = BinSTNode()

    reader.readLines().map { it.trim() }.forEach { command ->
        val cmdSplit = command.split(' ')
        when(cmdSplit[0]) {
            "ADD" -> tree.insertRec(x = cmdSplit[1].toInt())?.let { writer.println("DONE") } ?: writer.println("ALREADY")
            "SEARCH" -> if (tree.contains(value = cmdSplit[1].toInt())) writer.println("YES") else writer.println("NO")
            "PRINTTREE" -> tree.forEachAsc({ writer.println("${".".repeat(it.level)}${it.key}") })
        }
    }

    writer.close()

}