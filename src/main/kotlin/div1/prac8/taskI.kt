package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader

private class BTNode() {
    var parent: BTNode? = null
    var key: String? = null

    var ancCount = 0

    constructor(key: String, parent: BTNode? = null) : this() {
        this.key = key
        this.parent = parent
    }


    fun recUpdateAncCount(addedAnc: Int) {
        ancCount += addedAnc
        parent?.recUpdateAncCount(addedAnc)
    }

 }


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().trim().toInt()

    val map = HashMap<String, BTNode>()

    reader.readLines().forEach { line ->
        val (child, parent) = line.trim().split(' ')

        map[parent] = map[parent] ?: BTNode(parent)



        if(!map.containsKey(child)) {
            map[parent]?.let { existingParent ->
                BTNode(child, existingParent).also {
                    it.parent?.recUpdateAncCount(1)
                }.also { map[child] = it }
            }
        } else {
            map[child]?.parent = map[parent]
            map[parent]?.recUpdateAncCount(map[child]!!.ancCount + 1)
        }
    }

    map.keys.sorted().forEach { it ->
        map[it]?.let {
            println("${it.key} ${it.ancCount}")
        }
    }
}