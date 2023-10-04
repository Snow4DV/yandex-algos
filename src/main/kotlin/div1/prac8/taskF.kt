package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader

private class BSTNode6() {
    var key: Int? = null
    var left: BSTNode6? = null
    var right: BSTNode6? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }

    fun insertRec(curNode: BSTNode6 = this, x: Int, level: Int = this.level): BSTNode6? {
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
                BSTNode6(x, level + 1).also { curNode.left = it }
            }
        } else {
            if(curNode.right != null) {
                insertRec(curNode.right!!, x, level + 1)
            } else {
                BSTNode6(x, level + 1).also { curNode.right = it }
            }
        }
    }

    fun forEachAsc(forEachFun: (Int) -> Unit, node: BSTNode6 = this) {
        node.left?.let { forEachAsc(forEachFun, it) }
        forEachFun(node.key ?: throw NullPointerException("Null key in $this"))
        node.right?.let { forEachAsc(forEachFun, it) }
    }

    fun forEachAscNode(forEachFun: (BSTNode6) -> Unit, node: BSTNode6 = this) {
        node.left?.let { forEachAscNode(forEachFun, it) }
        forEachFun(node)
        node.right?.let { forEachAscNode(forEachFun, it) }
    }
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val tree = BSTNode6()

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toMutableList()
    input.removeLast() // remove zero

    input.forEach {tree.insertRec(x=it)}

    tree.forEachAscNode ({node: BSTNode6 ->
        if(node.left != null && node.right != null) println(node.key)
    })
}