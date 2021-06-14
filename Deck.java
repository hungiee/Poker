import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    static String[] SUITS = {"C", "D", "H", "S"};
    static String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    private ArrayList<Card> deck;

    private static int firstIndex = 0;


    /**
     * This is the constructor
     * Constructs the deck of cards
     */
    public Deck()
    {
        deck = new ArrayList<Card>();

        for (String suits: SUITS){
            for (String ranks: RANKS){
                Card singleCard = new Card(ranks,suits);
                this.deck.add(singleCard);
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Built-in function to represent the Deck
     * @return string version of Deck
     */
    public String toString()
    {
        ArrayList<Card> deckToPrint = new ArrayList<Card>();
        for (int i = firstIndex; i < getLenDeck(); i ++){
            deckToPrint.add(deck.get(i));
        }
        return deckToPrint.toString();
    }

    /**
     * Get the card on the top of the deck and increment the index by 1
     * @return the dealt card
     */
    public Card getCard()
    {
        Card dealCard = deck.get(firstIndex);
        firstIndex++;
        return dealCard;
    }

    /**
     * Get the number of cards in the deck
     * @return size of the deck( number of cards )
     */
    public int getLenDeck()
    {
        return deck.size() - firstIndex;
    }

    /**
     * Check if this two decks are equal
     * @param other Other poker hand
     * @return true if they are equal, else false
     */
    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        else if (other == this){
            return true;
        }
        else if (!(other instanceof PokerHand)){
            return false;
        }
        return this.deck == ((Deck) other).deck;
    }
}
