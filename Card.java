public class Card
{
    private String rank;
    private String suit;

    private static final int ACE = 40;
    private static final int KING = 30;
    private static final int QUEEN = 20;
    private static final int JACK = 11;

    /**
     * This is the constructor to construct a single card
     * @param rank strings representing the rank
     * @param suit strings representing the suit
     */
    public Card(String rank, String suit)
    {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Get the rank of the card
     * @return integer values represent each rank
     */
    public int getRank()
    {
        if (rank.equals("A")){
            return ACE;
        }
        else if (rank.equals("K")){
            return KING;
        }
        else if (rank.equals("Q") ){
            return QUEEN;
        }
        else  if (rank.equals("J")){
            return JACK;
        }
        else {
            return Integer.parseInt(rank);
        }
    }

    /**
     * A function to represent a card
     * @return string representing a card
     */
    public String toString()
    {
        return rank + "|" + suit + "  ";
    }

    /**
     * A function to get the suit of the card
     * @return the suit of the card
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * Check if this card equals to other card or not
     * @param other Other card
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
        return this.getRank() == ((Card) other).getRank() && this.getSuit().equals(((Card) other).getSuit());
    }
}
