package div2B.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

private class GenTree() : Comparable<GenTree> {
    var parent: GenTree? = null
    var key: String? = null
    var level = 0
    var children: MutableList<GenTree> = mutableListOf()

    constructor(key: String, parent: GenTree? = null, level: Int = 0) : this() {
        this.key = key
        this.parent = parent
        this.level = level
    }

    fun recAddLevel(delta: Int) {
        level += delta
        children.forEach {it.recAddLevel(delta)}
    }

    override fun compareTo(other: GenTree): Int {
        return level - other.level
    }


}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val map = HashMap<String, GenTree>()

    repeat(n - 1) {
        val (child, parent) = reader.readLine().trim().split(' ')
        map[parent] = map[parent] ?: GenTree(parent)
        if(!map.containsKey(child)) {
            map[parent]?.let { existingParent ->
                GenTree(child, existingParent, existingParent.level + 1).also { map[child] = it }
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

        var lNode = node1
        var rNode = node2
        while(max(lNode.level, rNode.level) > 0 && lNode != rNode) {
            if(lNode.level > rNode.level) {
                lNode = lNode.parent ?: break
            } else {
                rNode = rNode.parent ?: break
            }
        }

        writer.println(lNode.key)
    }

    writer.close()




}