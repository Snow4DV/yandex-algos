package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import kotlin.math.max

private class BSTNode() {
    var key: Int? = null
    var left: BSTNode? = null
    var right: BSTNode? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }


    fun insert(x: Int): BSTNode {
        if(key == null) {
            key = x
            return this
        } else {
            return insertRec(this, x, level)
        }

    }

    fun insertRec(curNode: BSTNode = this, x: Int, level: Int = this.level): BSTNode {
        curNode.key?.let { key ->
            if (key == x) {
                return curNode
            } else if (x < key) {
                return curNode.left?.let { left->
                    insertRec(left, x, level + 1)
                } ?: run {
                    BSTNode(x, level + 1).also { curNode.left = it }
                }
            } else {
                return curNode.right?.let { right->
                    insertRec(right, x, level + 1)
                } ?: run {
                    BSTNode(x, level + 1).also { curNode.right = it }
                }
            }
        } ?: run {
            curNode.key = x
            return curNode
        }
    }
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val tree = BSTNode()

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toMutableList()
    input.removeLast() // remove zero

    var maxLevel = 0
    input.forEach {
        tree.insertRec(x = it).also { maxLevel = max(it.level, maxLevel) }
    }

    println(maxLevel + 1)

}