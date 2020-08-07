/*
 * Node.java
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

public class Node {
    // Node for the linkedList Hand
    protected Card card;
    protected Node next;

    // Three constructors for all possible inputs

    public Node() {
    }

    public Node(Card card) {
        this.card = card;
    }

    public Node(Card card, Node next) {
        this.card = card;
        this.next = next;
    }
}