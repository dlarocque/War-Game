/*
 * Deck.java
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

public class Deck {

    // Both players hands ( LinkedLists of cards )
    Hand cardsInDeck; // The cards held in this deck
    int getCardRuns = 0; // times getCard has been called for this deck

    public Deck() {
        cardsInDeck = new Hand(); // create an empty hand
    }

    public Deck(Hand cardsInDeck) {
        // Assign the given hand to this deck
        this.cardsInDeck = cardsInDeck;
    }

    public void addCard(Card newCard) {
        // add a card to the back of the deck
        cardsInDeck.add(newCard);
        /*
         * This method adds the given newCard to the top of the linked list of
         * cardsInDeck
         */
    }

    public Card getCard() {
        Node cardToGet = cardsInDeck.head;
        removeCardAt(0); // remove the card at the top of the deck
        return cardToGet.card; // return the card at the top of the deck
        /*
         * This method returns the card at the top of the deck and then removes it from
         * the deck.
         */
    }

    public void addDeck(Deck other) {
        if (other.cardsInDeck.head != null) { // if the deck is not empty
            while (other.cardsInDeck.head.next != null) { // until we reach the last card
                // Add the Deck other to the bottom of this deck
                cardsInDeck.findLastCard().next = other.cardsInDeck.head;
                other.cardsInDeck.head = other.cardsInDeck.head.next;
            }
        }
        /*
         * This method takes in another deck and adds all its cards to the bottom of
         * this deck by simply linking the last card and the first card of the other
         * Deck together.
         */
    }

    public boolean isEmpty() {
        boolean isEmpty = false;
        if (amountOfCards() == 0) {
            isEmpty = true;
        }
        return isEmpty;
        // Tells us if this deck contains cards
    }

    public Card getRandom() {
        Card randomCard = null;
        if (!isEmpty()) { // if the deck is not empty
            int randomIndex = randomInRange(amountOfCards()); // random integer in range of amountOfCards
            randomCard = getNodeAt(randomIndex).card; // Get the card at the random index
            removeCardAt(randomIndex); // remove the card from the deck
        }
        return randomCard; // return the random card
        /*
         * This method finds a random card in the deck using randomInRange(range),
         * removes it from the deck, then returns it using removeCardAt(index)
         */
    }

    private int randomInRange(int max) {
        int randomIndex = (int) (Math.random() * max); // random number from 0 to max
        return randomIndex; // return the random int
    }

    public int amountOfCards() {
        int amountOfCards = 0;
        if (cardsInDeck.head != null) {
            amountOfCards = 1;
            Node card = cardsInDeck.head;

            // go through the entire linked list of cards and count the amount of times we
            // go through the loop
            while (card.next != null) {
                amountOfCards++;
                card = card.next;
            }
        }
        return amountOfCards; // return the amount of cards we found in the linked list
        /*
         * This function returns an integer that tells us how many cards are in the deck
         * by using a loop that goes through the linked list until the last node and
         * counts the amount of nodes we go through, and that will be the amount of
         * cards we have in the deck.
         */
    }

    public Node getNodeAt(int indexOfCard) {
        Node toGet = cardsInDeck.head;
        // We assume the index of the card is correctly given since there is no user
        // input
        for (int i = 0; i < indexOfCard; i++) {
            toGet = toGet.next; // iterate through the cards until we get to the correct node
        }
        return toGet; // return the node we wanted to get
    }

    public void removeCardAt(int indexOfCard) {
        if (indexOfCard == 0) {
            if (amountOfCards() == 1) {
                cardsInDeck.head = null;
            } else {
                cardsInDeck.head = cardsInDeck.head.next;
            }
        } else if (indexOfCard > 0) {
            Node nodeBeforeIndex = getNodeAt(indexOfCard - 1);
            Node nodeAfterIndex = getNodeAt(indexOfCard + 1);
            nodeBeforeIndex.next = nodeAfterIndex;
        }
        /*
         * This method removes the node found at the given index, and arranges the
         * linked list so that the pointers before and after the removed node point to
         * each other to keep the list intact.
         */
    }

    public int count() {
        int length = 1; // amount of cards in the deck
        if (cardsInDeck.head == null) { // there are no cards in the deck, length 0
            length = 0;
        } else { // if there are cards in the deck
            length = countLength(cardsInDeck.head, length); // call the recursive function to count the amount of cards
                                                            // in the list
        }
        return length;
        /*
         * This function checks to see if there are cards in the deck, if there are
         * cards in the deck, we call the recursive function countLength() to find the
         * amount of cards in the list. We return this value.
         */
    }

    public int countLength(Node current, int length) {
        if (current.next != null) // if there is another card in the list
            length = countLength(current.next, length + 1); // call again, looking at the next card, increase length
        return length; // will return an amount based on how many times countLength ran
        /*
         * This function counts the amount of cards we find in the deck recursively by
         * calling itself everytime we see that there is a another card, and passing
         * that next card as the current node. This makes the length equal to the value
         * of length returned from the last time countLength was called for the last
         * card in the list.
         */
    }

    public int sum() {
        int sum = 0;
        if (cardsInDeck.head != null) {
            sum = sumOfCards(cardsInDeck.head, sum); // calculate sum with sumOfCards()
        }
        return sum;
    }

    public int sumOfCards(Node current, int sum) {
        sum += current.card.getValue(); // add the value of the current card to the sum
        if (current.next != null) // if there is another card in the deck
            sum = sumOfCards(current.next, sum); // calculate sum with new sum value
        return sum; // return the final value of sum after all the sumOfCards()
        /*
         * We calculate the sum of the value of cards in the same way we did it for the
         * count() function, except we are adding the value of the card instead of +1 to
         * find the sum.
         */
    }

    public void orderedInsert(Card in) {
        Node newNode = new Node(in);
        insertNode(newNode); // insert the new node we created
    }

    public void insertNode(Node newNode) {
        // ASSUMING THE LIST IS IN ORDER
        Node current;

        if (cardsInDeck.head == null || cardsInDeck.head.card.getValue() >= newNode.card.getValue()) {
            // Put the card at the front of the list, as it has the lowest value
            newNode.next = cardsInDeck.head;
            cardsInDeck.head = newNode;
        } else {

            current = cardsInDeck.head;

            // Go through the array until we find a card that is bigger then our new card
            while (current.next != null && current.next.card.getValue() < newNode.card.getValue()) {
                current = current.next;
            }
            // insest the node at that point
            newNode.next = current.next;
            current.next = newNode;
        }
        /*
         * We want this method to insert a node based on its cards value. Assuming that
         * the list of cards are already in ascending order of their card values, we
         * first check to see if there is a head, and if the first card is higher than
         * our new card. If it is, we add the card to the front of the list. If not, we
         * use a while loop to iterate through the list until we find a card that's
         * value is larger then the value of our card. After we find that position, the
         * loop ends and we insert our node at that position.
         */
    }

    public String toString() {
        return deckString("", 0, cardsInDeck.head);
    }

    public String deckString(String deck, int nodeIndex, Node node) {
        if (!isEmpty()) {
            if (nodeIndex == 0) { // if were adding the first card
                deck = deckString("[" + node.card, nodeIndex + 1, node.next);
            } else if (nodeIndex == amountOfCards() - 1) { // last card
                deck = deckString(deck + ", " + node.card + "]", nodeIndex + 1, node.next);
            } else if (nodeIndex < amountOfCards() - 1) { // neither first or last card
                deck = deckString(deck + ", " + node.card, nodeIndex + 1, node.next);
            }
        }
        return deck;
        /*
         * We create the deckstring by adding a card to the string, and passing the next
         * node as a parameter for the next time it runs so that it can then do the same
         * thing. Once nodeIndex reaches the end, the deck string will be filled with
         * all the cards and it will be returned to the toString method.
         */
    }
}