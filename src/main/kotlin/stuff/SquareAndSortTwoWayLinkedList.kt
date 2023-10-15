package stuff

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

class LNode(var key: Int? = null, var left: LNode? = null, var right: LNode? = null)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val input = reader.readLine().trim().split(' ').map { it.toInt() }

    var curNode = LNode()

    input.forEach { // 1 9 ->16
        if (it < 0) {
            curNode.left = LNode(it * it).apply { right = curNode }
            curNode = curNode.left!!
        } else {
            while (curNode.key != null && curNode.key!! < it*it && curNode.right != null) {
                curNode = curNode.right!!
            }


            if (curNode.key == null || curNode.key!! < it * it) {
                curNode.right = LNode(it * it, left=curNode, right=curNode.right)
                curNode = curNode.right!!
            } else {
                curNode.left = LNode(it * it, right = curNode, left = curNode.left).also {
                    curNode.left?.let { left -> left.right = it }
                    curNode.left = it
                }
                curNode = curNode.left!!

            }
        }

    }
    var loopNode: LNode? = curNode
    while(loopNode?.left != null) {
        loopNode = loopNode.left!!
    }
    while(loopNode != null) {
        loopNode.key?.let {
            writer.println(it)
        }
        loopNode = loopNode.right
    }
    writer.close()
}