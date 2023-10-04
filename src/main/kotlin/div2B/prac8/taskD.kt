package div2B.prac8

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max

private class Tree() {
    var parent: Tree? = null
    var maxL: Int = 0
    var key: Int? = null
    var children: MutableSet<Tree> = mutableSetOf()

    constructor(key: Int, parent: Tree? = null) : this() {
        this.key = key
        this.parent = parent
    }


    fun recCountMaxL(): Int {
        val maxHeightOfChildren = children.map { it.recCountMaxL() }.maxOrNull() ?: 0
        return (maxHeightOfChildren + 1).also { maxL = it }
    }

    fun findMaxChain(): Int {
        val maxChainOfChildren = children.map { it.findMaxChain() }.maxOrNull() ?: 0
        var maxPair = 0 to 0
        children.forEach {
            if(it.maxL > maxPair.first) {
                maxPair = it.maxL to maxPair.first
            } else if(it.maxL> maxPair.second) {
                maxPair = maxPair.first to it.maxL
            }
        }
        return max(maxPair.first + maxPair.second + 1, maxChainOfChildren)
    }


}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val map = HashMap<Int, Tree>()

    var head: Tree? = null


    repeat(n - 1) {
        var (parent, child) = reader.readLine().trim().split(' ').map { it.toInt() }
        if (map.containsKey(child) && !map.containsKey(parent)) {
            parent = child.also { child = parent }
        }
        if(map.containsKey(child) && map.containsKey(parent) && map[parent]!!.parent == null) {
            parent = child.also { child = parent }
        }
        map[parent] = map[parent] ?: Tree(parent).also { head = head ?: it }
        map[parent]?.let { existingParent ->
            map[child] = (map[child] ?: Tree(child, existingParent)).also { existingParent.children.add(it) }
                .also { it.parent = existingParent }
        } ?: throw IllegalStateException()
    }



    head?.recCountMaxL()
    head?.let { writer.println(it.findMaxChain()) }

    writer.close()




}