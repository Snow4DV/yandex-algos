package div1.prac7

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.time.LocalDate


fun main() { // решение в два прохода
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val events = mutableListOf<Pair<Long, Int>>() // day/type + index

    for(i in 1..n) {
        val dates = reader.readLine().trim().split(' ').map {it.toInt()}
        getDays(dates[0], dates[1], dates[2], dates[3], dates[4], dates[5])?.let {
            var (daysAbleToVote, daysUnableToVote) = it
            events.add(daysAbleToVote to -i)
            events.add(daysUnableToVote to i) // including this day
        }
    }

    events.sortWith(compareBy({ it.first }, {it.second}))


    val finishedSets = mutableListOf<HashSet<Int>>()

    var set = HashSet<Int>()

    var pplLeft = n


    // idea: while ppl are increasing, add 'em to the array. When they started decreasing - another arr is coming
    for(i in 0 until events.size) {
        val event = events[i]
        if(event.second < 0) { // started being able to vote
            set.add(event.second * -1)
            pplLeft--
        } else if(event.second > 0 && set.contains(event.second)) {
            if(pplLeft >= 0 && events[i-1].second < 0) {
                finishedSets.add(set.clone() as HashSet<Int>)
            }
            set.remove(event.second)
            if(pplLeft == 0) pplLeft--
        }
    }

    finishedSets.forEach {set ->
        var firstFlag = true
        set.forEach {
            if(!firstFlag) writer.print(' ')
            else firstFlag = false
            writer.print(it)
        }
        writer.println()
    }

    if(finishedSets.isEmpty()) {
        writer.println(0)
    }

    writer.close()
}


private fun getDays(dayBirth: Int, monthBirth: Int, yearBirth: Int, dayDeath: Int, monthDeath: Int, yearDeath: Int): Pair<Long, Long>? {
    val birthDate = LocalDate.of(yearBirth, monthBirth, dayBirth)
    val deathDate = LocalDate.of(yearDeath, monthDeath, dayDeath)

    val dateAbleToVote = birthDate.plusYears(18)
    var dateUnableToVote = birthDate.plusYears(80)

    if(!dateAbleToVote.isBefore(deathDate)) {
        return null
    }

    if(deathDate.isBefore(dateUnableToVote)) {
        dateUnableToVote = deathDate
    }

    return dateAbleToVote.toEpochDay() to dateUnableToVote.toEpochDay() - 1
}
