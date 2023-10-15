import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val childrenCount = reader.getNum()

    val languagesKnownBySomeone = HashMap<String, Int>()
    val languagesThatAreKnownByEveryone = HashSet<String>()

    repeat(childrenCount) { childIndex ->
        val languagesCount = reader.getNum()
        repeat(languagesCount) {
            val language = reader.readLine().trim()
            languagesKnownBySomeone[language] = (languagesKnownBySomeone[language] ?: 0) + 1

            if(childIndex == childrenCount - 1 && languagesKnownBySomeone[language] == childrenCount) {
                languagesThatAreKnownByEveryone.add(language)
            }
        }
    }

    writer.println(languagesThatAreKnownByEveryone.size)
    languagesThatAreKnownByEveryone.forEach(writer::println)

    writer.println(languagesKnownBySomeone.size)
    languagesKnownBySomeone.keys.forEach(writer::println)


    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()