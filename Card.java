/*
 * Card.java
 *
 * COMP 1020 
 * SECTION          A03 
 * INSTRUCTOR       Bryan Wodi
 * ASSIGNMENT       4
 * 
 * AUTHOR           Daniel La Rocque
 * STUDENT NUMBER   7890680
 * COMPLETED        April 4th 2020
 *
 * PURPOSE          Game of War
 */

public class Card {
    private int value; // The value the card holds
    private String suit; // The suit of the card

    public Card() {
    }

    public Card(int value, String suit) {
        // Assign the values
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String toString() {
        if (value == 11) // jack
            return "Jack " + suit;
        if (value == 12) // queen
            return "Queen " + suit;
        if (value == 13) // king
            return "King " + suit;
        if (value == 14 || value == 1) // ace
            return "Ace " + suit;
        return value + " " + suit;
        /*
         * This method returns the data held in the card into a readable string
         */
    }
}