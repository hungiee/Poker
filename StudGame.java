import java.util.Scanner;

public class StudGame
{
    private static final int STUD_SIZE = 2;
    private static final int COMMUNITY_SIZE = 5;
    private static final int LIMIT = 4;

    public static void main(String[] args)
    {
        new StudGame().runStudGame();
    }

    /**
     * Create the stud hand
     * @param communitySet the community set consists of 5 cards
     * @param deck the deck consists of 52 cards
     * @return the desired stud
     */
    private StudPokerHand getStud(CommunityCardSet communitySet, Deck deck)
    {
        StudPokerHand studSet = new StudPokerHand(communitySet);
        for (int i = 0; i < STUD_SIZE; i++) {
            studSet.addCard(deck.getCard());
        }
        return studSet;
    }

    /**
     * Create the community set
     * @param deck the deck consists of 52 cards
     * @return the desired community set
     */
    private CommunityCardSet getCommunity(Deck deck)
    {
        CommunityCardSet communitySet = new CommunityCardSet();
        for (int i = 0; i < COMMUNITY_SIZE; i++) {
            communitySet.addCard(deck.getCard());
        }
        return communitySet;
    }

    private void runStudGame()
    {
        int point = 0;

        boolean notLose = true;

        Deck deck = new Deck();

        CommunityCardSet communitySet = getCommunity(deck);

        int sizeDeck = deck.getLenDeck();

        while (sizeDeck >= LIMIT && notLose){
            System.out.println("Current cards in deck: " + deck);
            StudPokerHand studSet1 = getStud(communitySet, deck);
            StudPokerHand studSet2 = getStud(communitySet, deck);

            sizeDeck = deck.getLenDeck();

            System.out.println("The community cards are: \n" + communitySet);
            System.out.println("Hand 1: \n" + studSet1);
            System.out.println("Hand 2: \n" + studSet2);

            Scanner input = new Scanner(System.in);
            System.out.println("Which of the following hands is worth more?");
            int correct = input.nextInt();

            if (correct == studSet1.compareTo(studSet2)){
                System.out.println("CORRECT, you get 1 more point");
                point++;
            }
            else{
                System.out.println("Wrong! \n");
                point = point + 0;
                System.out.println("The correct answer is: " + studSet1.compareTo(studSet2));
                System.out.println("You lose! Your total points: " + point);
                notLose = false;
            }
            if (sizeDeck < LIMIT){
                System.out.println("\n");
                System.out.println("Congratulations, you have won the game");
                System.out.println("\n");
                System.out.println("---------------");
                notLose = false;
            }
        }


    }
}
