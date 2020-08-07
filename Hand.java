/*
 * Hand.java
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

public class Hand {

    protected Node head;

    public Hand() { // constructor
    }

    public Hand(Node head) { // constructor
        this.head = head;
    }

    public void add(Card toAdd) {
        Node newCard = new Node(toAdd); // create a new node with the card in it
        Node lastCard = findLastCard(); // find the last card
        if (lastCard == null) { // if there are no cards
            head = newCard; // make the newCard the head
        } else {
            lastCard.next = newCard; // add the newCard to the end of the list
        }
        /*
         * This method adds a card to the bottom of the linked list of cards if there
         * are already cards in the deck, we find the last card in the hand and then
         * make that last card node point to our new card to attach it to the end of the
         * linked list.
         */
    }

    public Node findLastCard() {
        if (head == null)
            return null;
        Node lastCard = head;
        // Go through the list to the end
        while (lastCard.next != null) {
            lastCard = lastCard.next;
        }
        return lastCard; // return the last card in the linked list
        /*
         * This method returns the last card in the Hand linked list by finding the
         * first card that does not point to anything.
         */
    }
}