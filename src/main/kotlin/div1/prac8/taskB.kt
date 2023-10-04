package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

private class BSTNode2() {
    var key: Int? = null
    var left: BSTNode2? = null
    var right: BSTNode2? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }

    fun insertRec(curNode: BSTNode2 = this, x: Int, level: Int = this.level): BSTNode2? {
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
                BSTNode2(x, level + 1).also { curNode.left = it }
            }
        } else {
            if(curNode.right != null) {
                insertRec(curNode.right!!, x, level + 1)
            } else {
                BSTNode2(x, level + 1).also { curNode.right = it }
            }
        }
    }
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val tree = BSTNode2()

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toMutableList()
    input.removeLast() // remove zero

    input.forEach {
        tree.insertRec(x = it)?.also { println(it.level + 1) }
    }


}