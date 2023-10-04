package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.max

private class BSTNode2132() {
    var key: Int? = null
    var left: BSTNode2132? = null
    var right: BSTNode2132? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }

    fun insertRec(curNode: BSTNode2132 = this, x: Int, level: Int = this.level): BSTNode2132? {
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
                BSTNode2132(x, level + 1).also { curNode.left = it }
            }
        } else {
            if(curNode.right != null) {
                insertRec(curNode.right!!, x, level + 1)
            } else {
                BSTNode2132(x, level + 1).also { curNode.right = it }
            }
        }
    }

    val balanced: Boolean
        get() {
            return getHeight().second
        }

    fun getHeight(node: BSTNode2132 = this): Pair<Int, Boolean> {
        var goodFlag = true
        val leftHeight = node.left?.let {
            val (height, lFlag) = getHeight(it)
            goodFlag = goodFlag && lFlag
            height
        } ?: 0
        val rightHeight = node.right?.let {
            val (height, rFlag) = getHeight(it)
            goodFlag = goodFlag && rFlag
            height
        } ?: 0
        goodFlag = goodFlag && (abs(leftHeight - rightHeight) <= 1)
        return max(leftHeight, rightHeight) + 1 to goodFlag
    }
 }


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val tree = BSTNode2132()

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toMutableList()
    input.removeLast() // remove zero

    input.forEach {tree.insertRec(x=it)}

    println(if (tree.balanced) "YES" else "NO")
}