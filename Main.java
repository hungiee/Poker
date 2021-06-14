import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        new Main().runMain();
    }
    private void runMain()
    {
        Deck deck = new Deck();
        int point = 0;

        int sizeDeck = 52;

        int sizeHand = 5;

        int limit = 10;

        boolean notLose = true;

        System.out.println("WELCOME TO POKER COMPARING HANDS!");

        while(deck.getLenDeck() > ((sizeDeck) % (sizeHand)) && notLose){
            System.out.println("Current cards in deck: " + deck);
            System.out.println("---------------");

            PokerHand obHand1 = new PokerHand();
            PokerHand obHand2 = new PokerHand();

            for (int i = 0; i < sizeHand; i++){
                obHand1.addCard(deck.getCard());
            }
            for (int i = 0; i < sizeHand; i++){
                obHand2.addCard(deck.getCard());
            }

            System.out.println("Hand 1: " + obHand1);
            System.out.println("Hand 2: " + obHand2);

            Scanner input = new Scanner(System.in);
            System.out.println("Which one is worth more? Type 1 if Hand 1 wins, -1 if Hand 2 wins, 0 if Tie:");
            int user = input.nextInt();

            if(user == obHand1.compareTo(obHand2)) {
                System.out.println("CORRECT, you get 1 more point");
                point++;
            }
            else{
                System.out.println("Wrong! \n");
                point = point + 0;
                System.out.println("The correct answer is: " + obHand1.compareTo(obHand2));
                System.out.println("You lose! Your total points: " + point);
                notLose = false;
            }
            if (deck.getLenDeck() < limit){
                System.out.println("\n");
                System.out.println("Congratulations, you have won the game");
                System.out.println("\n");
                System.out.println("---------------");
            }
        }
    }
}
