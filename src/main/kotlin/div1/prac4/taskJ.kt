import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val availableChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789_".toHashSet()

    val keywordsSet = HashSet<String>()

    val splitFirstLine = reader.readLine().split(" ")

    val keywordCount = splitFirstLine[0].toInt()
    val idsCaseSensitive = splitFirstLine[1] == "yes"
    val idsCanStartWithDigit = splitFirstLine[2] == "yes"

    repeat(keywordCount) {
        var line = reader.readLine()
        if(!idsCaseSensitive) line = line.toLowerCase()
        keywordsSet.add(line)
    }

    fun validateId(id: String): Boolean { // should have at least 1 symbol beside digits
        if(!idsCanStartWithDigit && id[0].isDigit()) return false // validate rule 'ids can or cannot start with digits'
        if(id.any { !availableChars.contains(it) }) return false // illegal char
        if(!id.any { it.isLetter() || it=='_' }) return false // digits only!
        if(keywordsSet.contains(if (idsCaseSensitive) id else id.toLowerCase())) return false // that's a keyword
        return true
    }


    val dict = LinkedHashMap<String, Int>() // count of each keyword


    var line: String?

    while(reader.readLine().also {line = it} != null) {
        line!!.split(Pattern.compile("""[^a-zA-Z0-9_]+""")).forEach {
            if(it.isNotEmpty() && validateId(it)) {
                val word = if (idsCaseSensitive) it else it.toLowerCase()
                dict[word] = (dict[word] ?: 0) + 1 // increase counter
            }
        }
    }

    val max = dict.values.max()


    dict.filter { it.value == max }.keys.first().let {
        println(it)
    }

    writer.close()
}

