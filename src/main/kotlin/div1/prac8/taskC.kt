package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader

private class BSTNode3() {
    var key: Int? = null
    var left: BSTNode3? = null
    var right: BSTNode3? = null
    var level = 0

    constructor(key: Int, level: Int = 0) : this() {
        this.key = key
        this.level = level
    }

    fun insertRec(curNode: BSTNode3 = this, x: Int, level: Int = this.level): BSTNode3? {
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
                BSTNode3(x, level + 1).also { curNode.left = it }
            }
        } else {
            if(curNode.right != null) {
                insertRec(curNode.right!!, x, level + 1)
            } else {
                BSTNode3(x, level + 1).also { curNode.right = it }
            }
        }
    }

    fun secondMax(node: BSTNode3 = this, curMax: Pair<Int, Int> = Pair(Int.MIN_VALUE, Int.MIN_VALUE)): Pair<Int, Int> {
        val newPair = mergeDescPairAndNum(node.key!!, curMax)
        if(node.right == null && node == this && node.left != this) { // only root is allowed to go left
            return mergeDescPairs(secondMax(node.left!!, curMax), newPair)
        } else if(node.right != null) {
            return mergeDescPairs(secondMax(node.right!!, curMax), newPair)
        } else if(node.right == null && node != this && node.left != null) {
            return mergeDescPairs(secondMax(node.left!!, curMax), newPair)
        }
        return newPair
    }
 }

private fun mergeDescPairAndNum(key: Int, pair: Pair<Int, Int>):Pair<Int, Int> {
    return if(key < pair.first && key < pair.second) pair
    else if(key < pair.first) pair.first to key
    else key to pair.first
}

private fun mergeDescPairs(pair1: Pair<Int, Int>, pair2: Pair<Int, Int>):Pair<Int, Int> {
    val listSorted = listOf(pair1.first, pair1.second, pair2.first, pair2.second).sortedDescending()
    return listSorted[0] to listSorted[1]
}




fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val tree = BSTNode3()

    val input = reader.readLine().trim().split(' ').map { it.toInt() }.toMutableList()
    input.removeLast() // remove zero

    input.forEach {tree.insertRec(x=it)}

    println(tree.secondMax().second)


}