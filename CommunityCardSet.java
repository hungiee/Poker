import java.util.ArrayList;

public class CommunityCardSet
{
    private ArrayList<Card> communityCards;

    /**
     * This is the constructor -> constructs the Community Card Set
     */
    public CommunityCardSet()
    {
        this.communityCards = new ArrayList<Card>();
    }

    /**
     * Built-in toString method, representing the community card set
     * @return the string version of community card set
     */
    public String toString()
    {
        StringBuilder strVer = new StringBuilder();
        for(Card cards: this.communityCards) {
            strVer.append(cards);
        }
        return strVer.toString();
    }

    /**
     * Function to add card to the community card set
     * @param card the assigned card
     */
    public void addCard(Card card){
        this.communityCards.add(card);
    }


    /**
     * Function to remove card from the community card set
     * @param card the assigned card
     */
    public void removeCard(Card card){
        this.communityCards.remove(card);
    }

    /**
     * get the community card set
     * @return the community card set
     */
    public ArrayList<Card> getCommunity()
    {
        return this.communityCards;
    }
    /**
     * Check if this community set equals to other community set or not
     * @param other Other community set
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
        return this.communityCards == ((CommunityCardSet) other).communityCards;
    }
}
