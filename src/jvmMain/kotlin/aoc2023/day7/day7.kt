package aoc2023.day7

import java.io.File
import java.util.*
enum class Card(val label: Char, val rank: Int) {
    ACE('A', 13),
    KING('K', 12),
    QUEEN('Q', 11),
    TEN('T', 9),
    NINE('9', 8),
    EIGHT('8', 7),
    SEVEN('7', 6),
    SIX('6', 5),
    FIVE('5', 4),
    FOUR('4', 3),
    THREE('3', 2),
    TWO('2', 1),
    JOKER('J', 0);
    companion object {
        fun from(c: Char): Card {
            return when (c) {
                'A' -> ACE
                'K' -> KING
                'Q' -> QUEEN
                'T' -> TEN
                '9' -> NINE
                '8' -> EIGHT
                '7' -> SEVEN
                '6' -> SIX
                '5' -> FIVE
                '4' -> FOUR
                '3' -> THREE
                '2' -> TWO
                'J' -> JOKER
                else -> TWO
            }
        }
    }
}

enum class HandRank(val precedence: Int) {
    HIGH_CARD(0), ONE_PAIR(1), TWO_PAIR(2), THREE_OF_KIND(3),
    FULL_HOUSE(4), FOUR_OF_KIND(5), FIVE_OF_KIND(6)
}

data class Hand(val str: String, val bid: Int) : Comparable<Hand> {
    val cards = ArrayList<Card>(5)
    var rank: HandRank = HandRank.HIGH_CARD

    init {
        for (c in str.toCharArray()) {
            cards.add(Card.from(c))
        }
        countRank()
    }

    private fun countRank() {
        val map = mutableMapOf<Card, Int>()
        var jokers = 0
        for (card in cards) {
            if (card == Card.JOKER)
                jokers++
            else
                map[card] = map.getOrDefault(card, 0) + 1
        }
        rank = when {
            map.size == 5 -> HandRank.HIGH_CARD
            map.size == 4 -> HandRank.ONE_PAIR
            map.size == 3 -> {
                if ( map.values.maxOrNull()!! + jokers == 3)
                    HandRank.THREE_OF_KIND else HandRank.TWO_PAIR
            }
            map.size == 2 -> {
                if ( map.values.maxOrNull()!! + jokers == 4)
                    HandRank.FOUR_OF_KIND else HandRank.FULL_HOUSE
            }
            map.size == 1 -> HandRank.FIVE_OF_KIND
            else -> HandRank.FIVE_OF_KIND
        }
    }

    override fun compareTo(other: Hand): Int {
        if (rank != other.rank)
            return rank.precedence.compareTo(other.rank.precedence)

        for (i in 0 until cards.size)
            if (cards[i] != other.cards[i])
                return other.cards[i].compareTo(cards[i])

        return 0
    }

}

fun main() {
    val hands = ArrayList<Hand>()
    File("C:\\Users\\mikew\\IdeaProjects\\aoc\\src\\jvmMain\\resources\\2023\\day7.txt").bufferedReader()
        .readLines()
        .filter { it.isNotBlank() }
        .map {
            val split = it.split(" ")
            Hand(split[0], split[1].toInt())
        }.forEach { hands.add(it)}

//    hands.add(Hand("32T3K", 765))
//    hands.add(Hand("T55J5", 684))
//    hands.add(Hand("KK677", 28))
//    hands.add(Hand("KTJJT", 220))
//    hands.add(Hand("QQQJA", 483))

    hands.sort()

    var winnings = 0L
    for ( (i, hand) in hands.withIndex()) {
        val value = hand.bid * (i + 1)
        winnings += value
        println("$hand $i $value $winnings")
    }
    println(winnings)
}
