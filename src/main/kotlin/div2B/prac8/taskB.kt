package div2B.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

private class BTNode2() : Comparable<BTNode2> {
    var parent: BTNode2? = null
    var key: String? = null
    var level = 0
    var children: MutableList<BTNode2> = mutableListOf()

    constructor(key: String, parent: BTNode2? = null, level: Int = 0) : this() {
        this.key = key
        this.parent = parent
        this.level = level
    }

    fun recAddLevel(delta: Int) {
        level += delta
        children.forEach {it.recAddLevel(delta)}
    }

    override fun compareTo(other: BTNode2): Int {
        return level - other.level
    }


}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val map = HashMap<String, BTNode2>()

    repeat(n - 1) {
        val (child, parent) = reader.readLine().trim().split(' ')
        map[parent] = map[parent] ?: BTNode2(parent)
        if(!map.containsKey(child)) {
            map[parent]?.let { existingParent ->
                BTNode2(child, existingParent, existingParent.level + 1).also { map[child] = it }
                    .also { existingParent.children.add(it) }
            }
        } else {
            map[child]?.let { child ->
                child.parent = map[parent]?.also { it.children.add(child) }
                child.recAddLevel(map[parent]?.level?.plus(1) ?: throw IllegalArgumentException("err"))
            }
        }
    }

    reader.readLines().forEach { line ->
        val (node1, node2) = line.trim().split(' ')
            .map { map[it] ?: throw IllegalArgumentException() }

        val parent = if (node1 < node2) node1 else node2
        val child = if (node1 >= node2) node1 else node2

        var curNode: BTNode2? = child
        while (curNode != null && curNode != parent) {
            curNode = curNode.parent
        }

        if(curNode != null) {
            writer.println(if (parent == node1) 1 else 2)
        } else {
            writer.println(0)
        }
    }

    writer.close()




}