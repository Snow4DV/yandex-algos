package div1.prac8

import java.io.BufferedReader
import java.io.InputStreamReader

private class BTNode2() {
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

 }


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().trim().toInt()

    val map = HashMap<String, BTNode2>()

    reader.readLines().forEach { line ->
        val (child, parent) = line.trim().split(' ')

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

    map.keys.sorted().forEach { it ->
        map[it]?.let {
            println("${it.key} ${it.level}")
        }
    }
}