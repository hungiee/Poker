import java.util.ArrayList;
import java.util.Collections;

public class PokerHand
{
    private static final int SELF_WINS = 1;
    private static final int SELF_LOSES = -1;
    private static final int TIE = 0;

    private static final int FLUSH = 5;
    private static final int TWO_PAIRS = 4;
    private static final int PAIRS = 2;
    private static final int HIGH_CARD = 1;

    private ArrayList<Card> cardHand;

    /**
     * This is the constructor -> constructing the card hand using list
     */
    public PokerHand()
    {
        this.cardHand = new ArrayList<Card>();
    }

    /**
     * Constructs a string version of Card Hand by getting cards from
     * card hand and add to the string
     * @return the constructed string
     */
    public String toString()
    {
        StringBuilder strVer = new StringBuilder();
        for(Card cards: this.cardHand) {
            strVer.append(cards);
        }
        return strVer.toString();
    }

    /**
     * Method that adds card to the hand
     * @param card: a single card
     */
    public void addCard(Card card)
    {
        this.cardHand.add(card);
    }

    public void addCardOrder(Card card){
        int index = 0;
        while (index < this.cardHand.size()){
            if(card.getRank() < this.cardHand.get(index).getRank()){
                index++;
            }
            else{
                break;
            }
        }
        this.cardHand.add(index, card);
    }

    /**
     * Check if this poker hand equals to other poker hand or not
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
        PokerHand otherHand = (PokerHand) other;
        return otherHand.getPairRankOfHand() == this.getPairRankOfHand() &&
                this.getRankOfHand() == ((PokerHand) other).getRankOfHand();
    }

    /**
     * A function to count the pairs in a hand
     * @return the count of pairs
     */
    private int countPair()
    {
        int pairCount = 0;

        int numCards = this.cardHand.size();

        for (int i = 0; i < numCards; i++) {
            for (int j = 0; j < i; j++) {
                if (this.cardHand.get(i).getRank() == this.cardHand.get(j).getRank()){
                    pairCount = pairCount + 1;
                }
            }
        }
        return pairCount;
    }

    /**
     * A function to check if there is a flush in the hand
     * @return a boolean that checks if a hand has a flush or not
     */
    private boolean isFlush()
    {
        int firstIndex = 0;

        String suit = this.cardHand.get(firstIndex).getSuit();

        boolean isFlush = true;

        int numCards = this.cardHand.size();

        int index = 1;

        while (isFlush && index < numCards) {
            isFlush = isFlush && this.cardHand.get(index).getSuit().equals(suit);
            index++;
        }
        return isFlush;

    }

    /**
     * A function to get the ranks of hand 1
     * @return the list containing integers that represent the ranks of a hand 1
     */
    private ArrayList<Integer> getRankOfHand()
    {
        ArrayList<Integer> rankOfHand = new ArrayList<Integer>();
        for(Card cards: this.cardHand) {
            rankOfHand.add(cards.getRank());
        }
        rankOfHand.sort(Collections.reverseOrder());
        return rankOfHand;
    }

    /**
     * A function to get the ranks of pairs in hand 1
     * @return the list containing integers that represent the ranks of pairs in hand 1
     */
    private ArrayList<Integer> getPairRankOfHand()
    {
        ArrayList<Integer> pairRankOfHand = new ArrayList<Integer>();

        int numCards = this.cardHand.size();

        for(int i = 0; i < numCards; i++){
            for(int j = 0; j < i; j++){
                if (this.cardHand.get(i).getRank() == this.cardHand.get(j).getRank()) {
                    pairRankOfHand.add(this.cardHand.get(i).getRank());
                }
            }
        }
        pairRankOfHand.sort(Collections.reverseOrder());
        return pairRankOfHand;
    }

    /**
     * Compare the sorted ranks in the hand by index
     * @param thisHand (the assigned first card hand)
     * @param otherHand (the assigned second card hand)
     * @return a negative number if this is worth LESS than other, zero
     *  if they are worth the SAME, and a positive number if this is worth
     *  MORE than other
     */
    private int compareRank (ArrayList<Integer> thisHand, ArrayList<Integer> otherHand)
    {
        int index = 0;

        int numCards = thisHand.size();

        while(index < numCards - 1 && thisHand.get(index).equals(otherHand.get(index))) {
            index++;
        }
        if(thisHand.get(index) > otherHand.get(index)) {
            return SELF_WINS;
        }
        else if (thisHand.get(index) < otherHand.get(index)) {
            return SELF_LOSES;
        }
        else{
            return TIE;
        }
    }

    /**
     * Some pairs are equal to each other -> remove the equal pairs and compare
     * other cards
     *
     * @param other: second card hand
     * @return the function compareRank and assign the removed hands
     */
    private int equalPairCase(PokerHand other)
    {
        ArrayList<Integer> thisHand = this.getRankOfHand();
        ArrayList<Integer> otherHand = other.getRankOfHand();

        ArrayList<Integer> thisPairRank = this.getPairRankOfHand();
        ArrayList<Integer> otherPairRank = other.getPairRankOfHand();

        for(int ranks: thisPairRank) {
            thisHand.remove(Integer.valueOf(ranks));
            thisHand.remove(Integer.valueOf(ranks));
        }
        for(int ranks: otherPairRank) {
            otherHand.remove(Integer.valueOf(ranks));
            otherHand.remove(Integer.valueOf(ranks));
        }
        thisHand.sort(Collections.reverseOrder());
        otherHand.sort(Collections.reverseOrder());

        return this.compareRank(thisHand, otherHand);
    }

    /**
     * A function to compare pair rank in card hands that
     * calling function equalPairCase if pairs in two hands are equal to each other
     *
     * @param other: second card hand
     * @return function compareRank
     */
    private int comparePairRank(PokerHand other)
    {
        ArrayList<Integer> thisPairRank = this.getPairRankOfHand();
        ArrayList<Integer> otherPairRank = other.getPairRankOfHand();

        if (thisPairRank.equals(otherPairRank))
        {
          return this.equalPairCase(other);
        }
        else{
            return this.compareRank(thisPairRank, otherPairRank);

        }
    }

    /**
     * A function to get the types of card hands representing by an integer
     * @return integers representing the types
     */
    private int findTypes()
    {
        if(this.isFlush()) {
            return FLUSH;
        }
        else if(this.countPair() == 1) {
            return PAIRS;
        }
        else if(this.countPair() == 3) {
            return PAIRS;
        }
        else if(this.countPair() == 2) {
            return TWO_PAIRS;
        }
        else if(this.countPair() == 4) {
            return TWO_PAIRS;
        }
        else{
            return HIGH_CARD;
        }
    }

    /**
     *  Determines how this hand compares to another hand, returns
     *  positive, negative, or zero depending on the comparison.
     *
     *  @param other The hand to compare this hand to
     *  @return a negative number if this is worth LESS than other, zero
     *  if they are worth the SAME, and a positive number if this is worth
     *  MORE than other
     */
    public int compareTo(PokerHand other)
    {
        ArrayList<Integer> thisRank = this.getRankOfHand();
        ArrayList<Integer> otherRank = other.getRankOfHand();

        int thisType = this.findTypes();
        int otherType = other.findTypes();

        if(thisType > otherType) {
            return SELF_WINS;
        }
        else if(thisType < otherType) {
            return SELF_LOSES;
        }
        else{
            if (thisType == TWO_PAIRS || thisType == PAIRS) {
                return this.comparePairRank(other);
            }
            else if(thisType == FLUSH || thisType == HIGH_CARD) {
                return this.compareRank(thisRank,otherRank);
            }
        }
        return 0;
    }
}