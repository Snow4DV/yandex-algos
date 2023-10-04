package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader

private class BSTNode4() {
    var key: Int? = null
    var left: BSTNode4? = null
    var right: BSTNode4? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }

    fun insertRec(curNode: BSTNode4 = this, x: Int, level: Int = this.level): BSTNode4? {
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
                BSTNode4(x, level + 1).also { curNode.left = it }
            }
        } else {
            if(curNode.right != null) {
                insertRec(curNode.right!!, x, level + 1)
            } else {
                BSTNode4(x, level + 1).also { curNode.right = it }
            }
        }
    }

    fun forEachAsc(forEachFun: (Int) -> Unit, node: BSTNode4 = this) {
        node.left?.let { forEachAsc(forEachFun, it) }
        forEachFun(node.key ?: throw NullPointerException("Null key in $this"))
        node.right?.let { forEachAsc(forEachFun, it) }
    }
 }


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val tree = BSTNode4()

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toMutableList()
    input.removeLast() // remove zero

    input.forEach {tree.insertRec(x=it)}

    tree.forEachAsc(System.out::println)
}