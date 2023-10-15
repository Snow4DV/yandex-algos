import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    reader.readLine() // skip first line

    val mWord = reader.readLine()

    val chars = HashMap<Char, Int>()
    for(char in mWord) {
        chars[char] = (chars[char] ?: 0) + 1
    }
    val mText = reader.readLine()

    var counter = 0

    for(i in 0 until mWord.length) {
        val currentChar = mText[i]
        if(chars.contains(currentChar)) { // if letter is in word
            chars[currentChar] = chars[currentChar]!! - 1
        }

        if(i == mWord.length - 1 && chars.all { it.value == 0 }) counter++ // first inc
    }

    var firstWindowChar = mText[0]

    for(i in 1 until mText.length - (mWord.length - 1)) {
        val currentChar = mText[i]
        val windowLastChar = mText[i + mWord.length - 1]
        if(chars.contains(firstWindowChar)) {
            chars[firstWindowChar] = chars[firstWindowChar]!! + 1
        }
        firstWindowChar = currentChar
        if(chars.contains(windowLastChar)) { // if letter is in word
            chars[windowLastChar] = chars[windowLastChar]!! - 1
        }
        if(chars.all { it.value == 0 }) {
            counter++
        }
    }



    println(counter)


    writer.close()
}

