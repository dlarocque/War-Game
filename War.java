/*
 * War.java
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

public class War {

    Deck deck1; // player ones deck
    Deck deck2; // player twos deck
    private int roundsPlayed = 0;

    public static void main(String[] args) {

        // create the players' decks
        Deck playerOne = new Deck();
        Deck playerTwo = new Deck();

        War game = new War(playerOne, playerTwo); // new game of war

        game.fillDecks(); // shuffle and fill the decks

        while (!(game.deck1.isEmpty()) && !(game.deck2.isEmpty())) { // while players have cards
            game.playRound(); // play a round
        }

        // player 2 wins
        if (game.deck1.isEmpty())
            System.out.println(game.endGame("player2"));

        // player 1 wins
        if (game.deck2.isEmpty())
            System.out.println(game.endGame("player1"));

        System.out.println("Rounds played :  " + game.roundsPlayed);
        /*
         * The main method in this class runs an entire game of war and prints out a
         * message at the end of the game telling us which player won the game. We use
         * isEmpty to tell which player has lost, and playRound while both players still
         * have cards to play.
         */
    }

    public War(Deck playerOne, Deck playerTwo) {
        // Assign players their decks
        deck1 = playerOne;
        deck2 = playerTwo;
    }

    public void fillDecks() {
        Deck entireDeck = createCards(); // contains the cards in the entire deck
        // now we want to split the deck and fill the players decks
        for (int i = 0; i < 26; i++) { // runs 26 times, giving both players a card each time, splitting 52 cards.
            deck1.addCard(entireDeck.getRandom()); // add a random card to player ones hand
            deck2.addCard(entireDeck.getRandom()); // add a random card to player twos hand
        }
        /*
         * This is the method we use at the start of the game to split the all of the
         * cards in the game into both players decks randomly. We create the initial
         * deck with the createCards function then we give a random card from that deck
         * to each player n/2 times.
         */
    }

    public Deck createCards() {
        Hand allCards = new Hand();
        for (int suit = 0; suit < 4; suit++) { // 4 suits
            for (int value = 2; value <= 14; value++) { // 13 cards for each suit
                if (suit == 0)
                    allCards.add(new Card(value, "Spades"));
                if (suit == 1)
                    allCards.add(new Card(value, "Clubs"));
                if (suit == 2)
                    allCards.add(new Card(value, "Hearts"));
                if (suit == 3)
                    allCards.add(new Card(value, "Diamonds"));
            }
        }
        Deck entireDeck = new Deck(allCards);
        return entireDeck;
        /*
         * This function creates the initial deck that contains all of the cards that
         * will be played in a game. We use a nested loop that runs 4 (suits) * 13
         * (cards per suit) times to put every possible card in the deck. We use if
         * statements to determine which suit we're adding to the deck.
         */
    }

    public void playRound() {
        roundsPlayed++;
        drawCards(); // Draw cards for the round
        // This method plays a round of war between both players.
    }

    public void drawCards() {
        // Draw a card for each player
        Card player1Card = deck1.getCard();
        Card player2Card = deck2.getCard();

        // Print out their draws
        System.out.println("Player 1 draws a " + player1Card + ", Player 2 draws a " + player2Card);

        analyzeOutcome(player1Card, player2Card);
        // This method draws, prints then uses the analyzeOutcome to compare the cards
        // and find the winner.
    }

    public void analyzeOutcome(Card player1Card, Card player2Card) {
        if (player1Card.getValue() > player2Card.getValue()) // player 1 wins
            analyzeWin("player1", player1Card, player2Card); // analyze the win of the winner
        if (player1Card.getValue() < player2Card.getValue()) // player 2 wins
            analyzeWin("player2", player1Card, player2Card);
        if (player1Card.getValue() == player2Card.getValue()) { // cards are equal, war occurs
            if (canWar()) { // if both players have enough cards to war,
                Deck pot = new Deck(); // create the pot
                // analyze the war using the war() function
                pot.addCard(player1Card);
                pot.addCard(player2Card);
                war(pot);
            } else {
                // Players cant war, return their cards
                deck1.addCard(player1Card);
                deck2.addCard(player2Card);
                System.out
                        .println("A player did not have enough cards for war, the cards were returned to the players.");
            }
        }
        /*
         * We want to analyze which player wins, if there is a war, then call the
         * appropriate functions to deal with the outcome of the draw. If a war occurs,
         * we check if both players can war, then analyze play out the war in the war()
         * function.
         */
    }

    public void analyzeWin(String winner, Card player1Card, Card player2Card) {
        Deck pot = new Deck();
        pot.addCard(player1Card);
        pot.addCard(player2Card);
        if (winner.equals("player1")) { // player 1 won the round
            distributePot(deck1, pot); // give the winner the winnings
            System.out.println("Player 1 wins the round");
        } else { // player 2 won the round
            distributePot(deck2, pot);
            System.out.println("Player 2 wins the round");
        }
        /*
         * This method simply adds cards to the pot, checks to see who won, then gives
         * the winner their winnings through the distributePot() function
         */
    }

    public void war(Deck pot) {
        pot = drawPot(pot); // draw more cards into the pot

        // Draw the cards to be played
        Card player1Draw = deck1.getCard();
        System.out.println("Player 1 drew a " + player1Draw + " for the war!");
        Card player2Draw = deck2.getCard();
        System.out.println("Player 2 drew a " + player2Draw + " for the war!");

        // add the cards to the pot
        pot.addCard(player1Draw);
        pot.addCard(player2Draw);

        // determine the winner, distribute the pot
        if (player1Draw.getValue() > player2Draw.getValue()) { // player 1 wins the war
            System.out.println("Player 1 wins the war and collects the pot!");
            distributePot(deck1, pot);
        } else if (player1Draw.getValue() < player2Draw.getValue()) { // player 2 wins the war
            System.out.println("Player 2 wins the war and collects the pot!");
            distributePot(deck2, pot);
        } else {
            war(pot); // if theres another war, go again with a bigger pot
        }
        /*
         * When two players draws are the same value, we call this function to analyze
         * the following war, we draw cards to the pot, draw two new cards, the winner
         * of the battle will be distributed the entire pot. If there is another war,
         * just call the function again and pass it the new pot
         */
    }

    public boolean canWar() {
        boolean canWar = true;
        if (deck1.amountOfCards() < 4 || deck2.amountOfCards() < 4) // if a player does not have enough cards
            canWar = false; // they can not war
        return canWar;
        // Function tells us if the players have enough cards for a war
    }

    public void distributePot(Deck winner, Deck pot) {
        for (int i = 0; i <= pot.amountOfCards(); i++) { // through the entire pot
            winner.addCard(pot.getRandom()); // randomly insert a card from the pot
        }
        /*
         * We use a loop that runs amountOfCards times, every run we add a random card
         * from the pot to the winning players deck. This is used when a player wins the
         * pot and the cards are awarded to him.
         */
    }

    public Deck drawPot(Deck pot) {

        // Add 2 cards from the top of each players deck to the pot
        Card added = deck1.getCard();
        System.out.println("Player 1 added a " + added + " to the pot");
        pot.addCard(added);

        added = deck1.getCard();
        System.out.println("Player 1 added a " + added + " to the pot");
        pot.addCard(added);

        added = deck2.getCard();
        System.out.println("Player 2 added a " + added + " to the pot");
        pot.addCard(added);

        added = deck2.getCard();
        System.out.println("Player 2 added a " + added + " to the pot");
        pot.addCard(added);

        return pot;
        // This function fills the pot when there is a war
    }

    public String endGame(String winner) {
        String message;
        if (winner.equals("player1")) { // if player 1 won
            message = "Player one wins the game";
        } else { // player 2 won
            message = "Player two wins the game";
        }
        return message;
        // Prints the winner of the game when endGame is called.
        // Function is only called when the game is over.
    }
}