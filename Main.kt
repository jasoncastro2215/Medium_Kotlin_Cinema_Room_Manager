fun main() {
    // write your code here
    val cinema = Cinema()
    cinema.chooseAction()
}

class Cinema {
    val row: Int
    val seat: Int
    val totalSeat: Int
    val multiList: MutableList<MutableList<Char>> = mutableListOf()
    var ticketSold = 0
    var income = 0

    constructor() {
        println("Enter the number of rows:")
        row = readln().toInt()
        println("Enter the number of seats in each row:")
        seat = readln().toInt()
        totalSeat = row * seat

        //initialize row list
        repeat(row + 1) {
            multiList.add(mutableListOf())
        }
        multiList[0].add(' ')

        //fill seats identifier
        repeat(seat) {
            multiList[0].add((it + 1).toString().single())
        }

        //fill row identifier
        repeat(row) {
            multiList[it + 1].add((it + 1).toString().single())
        }
        //fill all seats with S
        for (r in 1..row) {
            for (s in 1..seat) {
                multiList[r].add('S')
            }
        }
    }

    fun showSeatingArrangement() {
        println("\nCinema:")
        repeat(row + 1) {
            println(multiList[it].joinToString(" "))
        }

    }

    fun buyTicket() {
        while (true) {
            try {
                println("\nEnter a row number:")
                val preferredRow = readln().toInt()
                println("Enter a seat number in that row:")
                val preferredSeat = readln().toInt()
                if (multiList[preferredRow][preferredSeat] == 'B')
                    println("\nThat ticket has already been purchased!")
                else {
                    println()
                    if (totalSeat < 60 || preferredRow <= row / 2) {
                        println("Ticket price: \$10")
                        income += 10
                    } else {
                        println("Ticket price: \$8")
                        income += 8
                    }

                    ticketSold++
                    reserveSeat(preferredRow, preferredSeat)
                    break
                }
            } catch (e: Exception) {
                println("\nWrong input!")
            }
        }
    }

    private fun reserveSeat(preferredRow: Int, preferredSeat: Int) {
        multiList[preferredRow][preferredSeat] = 'B'
    }

    fun chooseAction() {
        while (true) {
            println()
            println(
                """1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit"""
            )
            when (readln().toInt()) {
                1 -> showSeatingArrangement()
                2 -> buyTicket()
                3 -> statistics()
                0 -> return
            }
        }
    }

    private fun statistics() {
        println()
        print(
            """Number of purchased tickets: $ticketSold
Percentage: ${"%.2f".format(ticketSold.toDouble() / totalSeat * 100)}%
Current income: $$income
Total income: $${
                if (totalSeat < 60) totalSeat * 10
                else row / 2 * 10 * 9 + (row - row / 2) * 8 * 9
            }"""
        )
        println()
    }
}