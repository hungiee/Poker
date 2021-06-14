import java.util.ArrayList;

public class StudPokerHand
{
    private ArrayList<Card> studSet;
    private CommunityCardSet communitySet;

    /**
     * The constructor. Constructing the stud sets
     * @param community the community set
     */
    public StudPokerHand(CommunityCardSet community)
    {
        this.communitySet = community;
        this.studSet = new ArrayList<Card>();
    }

    /**
     * Built-in function to represent the stud sets
     * @return string version of stud set
     */
    public String toString()
    {
        StringBuilder strVer = new StringBuilder();
        for(Card cards: this.studSet) {
            strVer.append(cards);
        }
        return strVer.toString();
    }

    /**
     * Function to add card to the stud set
     * @param card the assigned card
     */
    public void addCard(Card card)
    {
        this.studSet.add(card);
    }

    /**
     * Function to remove card from the stud set
     * @param card the assigned card
     */
    public void removeCard(Card card)
    {
        this.studSet.remove(card);
    }

    /**
     * Collect seven cards including community and stud
     * @return the seven cards( both cards )
     */
    public ArrayList<Card> collectSevenCards()
    {
        ArrayList<Card> bothCards = new ArrayList<>();
        bothCards.addAll(communitySet.getCommunity());
        bothCards.addAll(studSet);
        return bothCards;
    }

    /**
     * Get all possibilities of five card hands - loopy version - Version 1
     * @return all possible five card hands
     */
    private ArrayList<PokerHand> getAllFiveCardHands()
    {
        ArrayList<PokerHand> allFiveCardHands = new ArrayList<PokerHand>();

        ArrayList<Card> sevenCards = collectSevenCards();

        for(int a = 0; a < sevenCards.size(); a ++){
            for(int b = a + 1; b < sevenCards.size(); b ++){
                for(int c = b + 1; c < sevenCards.size(); c ++){
                    for(int d = c + 1; d < sevenCards.size(); d ++){
                        for(int e = d + 1; e < sevenCards.size(); e ++){
                            PokerHand pokerHand = new PokerHand();
                            pokerHand.addCard(sevenCards.get(a));
                            pokerHand.addCard(sevenCards.get(b));
                            pokerHand.addCard(sevenCards.get(c));
                            pokerHand.addCard(sevenCards.get(d));
                            pokerHand.addCard(sevenCards.get(e));
                            allFiveCardHands.add(pokerHand);
                        }
                    }
                }
            }
        }
        return allFiveCardHands;
    }
    /**
     * Get the best five card hand out of all possibilities
     * @return a best five card hand
     */
    public PokerHand getBestFiveCardHand()
    {
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);

        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
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
    public int compareTo(StudPokerHand other)
    {
        PokerHand thisBest = this.getBestFiveCardHand();
        PokerHand otherBest = other.getBestFiveCardHand();
        return thisBest.compareTo(otherBest);

    }

    /**
     * Check if this stud set equals to other stud set or not
     * @param other Other stud set
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
        return this.studSet == ((StudPokerHand) other).studSet;
    }


}
