public class PokerComparisonTests
{
    public static void main(String[] args)
    {
        Tester test = new Tester(true);

        testHighCard(test);
        testPair(test);
        testTwoPairs(test);
        testFlush(test);

        test.finishTests();
    }

    /**
     * Take a self customized list of cards, append to the object
     * @param listCard: self customized list of cards
     * @return object PokerHand
     */
    private static PokerHand convertToOb(String[][] listCard)
    {
        PokerHand obHand = new PokerHand();

        int indexRank = 0;

        int indexSuit = 1;

        for (String[] cards: listCard) {
            String rank = cards[indexRank];
            String suit = cards[indexSuit];
            Card obCard = new Card(rank,suit);
            obHand.addCard(obCard);
        }
        return obHand;
    }
    private static void testHighCard(Tester test)
    {
        //High card vs High card(example from project 2 -> fixed)
        PokerHand hand1Model = convertToOb(new String[][] {{"A", "D"}, {"2", "S"}, {"6", "C"}, {"3", "S"}, {"9", "S"}});
        PokerHand hand2Model = convertToOb(new String[][] {{"7", "H"}, {"9", "S"}, {"K", "C"}, {"2", "H"}, {"Q", "S"}});

        test.assertEquals("'Hand 1 wins 2; Different ranks high cards",
                1, hand1Model.compareTo(hand2Model));

        //High card vs High card
        hand1Model = convertToOb(new String[][] {{"Q", "D"}, {"9", "S"}, {"5", "C"}, {"J", "S"}, {"6", "S"}});
        hand2Model = convertToOb(new String[][] {{"9", "H"}, {"J", "S"}, {"6", "C"}, {"5", "H"}, {"Q", "S"}});

        test.assertEquals("Tie high card",
                0, hand1Model.compareTo(hand2Model));

    }
    private static void testPair(Tester test)
    {
        //1 Pair vs 2 pairs
        PokerHand hand1Model = convertToOb(new String[][] {{"J", "S"}, {"J", "H"}, {"2", "S"}, {"A", "H"}, {"5", "H"}});
        PokerHand hand2Model = convertToOb(new String[][] {{"K", "D"}, {"K", "C"}, {"A", "C"}, {"A", "S"}, {"2", "H"}});

        test.assertEquals("Hand 1 loses Hand 2; Hand 1 has 1 pair J J, Hand 2 has two pairs K K and A A",
                -1, hand1Model.compareTo(hand2Model));

        // 1 Pair vs 1 Pair -> testing equal pair case but different ranks in high cards
        hand1Model = convertToOb(new String[][] {{"4", "S"}, {"3", "H"}, {"A", "S"}, {"A", "H"}, {"10", "S"}});
        hand2Model = convertToOb(new String[][] {{"3", "D"}, {"4", "C"}, {"A", "D"}, {"A", "C"}, {"9", "H"}});

        test.assertEquals("Hand 1 wins Hand 2; Both 1 pair but hand 1 higher rank in the last card",
                1, hand1Model.compareTo(hand2Model));

        // 1 Pair vs 1 Pair testing 3 cards having the same rank
        hand1Model = convertToOb(new String[][] {{"K", "S"}, {"K", "H"}, {"K", "D"}, {"2", "H"}, {"4", "S"}});
        hand2Model = convertToOb(new String[][] {{"A", "D"}, {"A", "C"}, {"A", "D"}, {"2", "C"}, {"5", "H"}});

        test.assertEquals("Hand 1 loses Hand 2; Both 1 pair but hand 2 higher rank ",
                -1, hand1Model.compareTo(hand2Model));

        //1 pair vs 1 pair -> testing while loop index
        hand1Model = convertToOb(new String[][] {{"8", "S"}, {"K", "H"}, {"10", "S"}, {"3", "H"}, {"3", "S"}});
        hand2Model = convertToOb(new String[][] {{"Q", "D"}, {"2", "C"}, {"Q", "D"}, {"8", "C"}, {"K", "H"}});


        test.assertEquals("Hand 1 loses Hand 2; Hand 1 pair, Hand 2 pair but higher ranks",
                -1, hand1Model.compareTo(hand2Model));

        // 1 pair vs Flush
        hand1Model = convertToOb(new String[][] {{"4", "S"}, {"5", "S"}, {"3", "S"}, {"2", "S"}, {"6", "S"}});
        hand2Model = convertToOb(new String[][] {{"A", "D"}, {"A", "C"}, {"A", "D"}, {"2", "C"}, {"5", "H"}});

        test.assertEquals("Hand 1 wins Hand 2; Flush vs 1 pair ",
                1, hand1Model.compareTo(hand2Model));

        // Tie 1 pair
        hand1Model = convertToOb(new String[][] {{"A", "S"}, {"3", "H"}, {"A", "D"}, {"2", "H"}, {"5", "S"}});
        hand2Model = convertToOb(new String[][] {{"3", "D"}, {"A", "C"}, {"5", "D"}, {"A", "C"}, {"2", "D"}});

        test.assertEquals("Hand 1 tie Hand 2; Both 1 pair tie rank ",
                0, hand1Model.compareTo(hand2Model));

    }

    private static void testTwoPairs(Tester test)
    {
        // 2 Pairs vs 2 Pairs
        PokerHand hand1Model = convertToOb(new String[][] {{"K", "S"}, {"K", "H"}, {"A", "S"}, {"A", "H"}, {"2", "S"}});
        PokerHand hand2Model = convertToOb(new String[][] {{"5", "D"}, {"5", "C"}, {"A", "D"}, {"A", "C"}, {"2", "H"}});

        test.assertEquals("Hand 1 wins Hand 2; Hand 1 has two pairs K K and A A, Hand 2 has two pairs but lower rank",
                1, hand1Model.compareTo(hand2Model));

        // 2 Pairs vs Flush
        hand1Model = convertToOb(new String[][] {{"K", "S"}, {"K", "H"}, {"3", "D"}, {"3", "H"}, {"6", "S"}});
        hand2Model = convertToOb(new String[][] {{"2", "D"}, {"3", "D"}, {"4", "D"}, {"5", "D"}, {"6", "D"}});

        test.assertEquals("Hand 1 loses Hand 2; 2 pairs vs flush ",
                -1, hand1Model.compareTo(hand2Model));

        // Tie 2 Pairs
        hand1Model = convertToOb(new String[][] {{"A", "S"}, {"5", "H"}, {"A", "D"}, {"2", "H"}, {"5", "S"}});
        hand2Model = convertToOb(new String[][] {{"5", "D"}, {"A", "C"}, {"5", "D"}, {"A", "C"}, {"2", "D"}});

        test.assertEquals("Hand 1 tie Hand 2; Both 2 pairs tie rank ",
                0, hand1Model.compareTo(hand2Model));
    }

    private static void testFlush(Tester test)
    {
        // Flush vs Flush
        PokerHand hand1Model = convertToOb(new String[][] {{"K", "H"}, {"A", "H"}, {"J", "H"}, {"3", "H"}, {"5", "H"}});
        PokerHand hand2Model = convertToOb(new String[][] {{"K", "D"}, {"A", "D"}, {"2", "D"}, {"3", "D"}, {"5", "D"}});

        test.assertEquals("Hand 1 wins Hand 2; FLUSH vs FLUSH",
                1, hand1Model.compareTo(hand2Model));

        // Tie flushes
        hand1Model = convertToOb(new String[][] {{"A", "H"}, {"K", "H"}, {"J", "H"}, {"5", "H"}, {"3", "H"}});
        hand2Model = convertToOb(new String[][] {{"K", "D"}, {"A", "D"}, {"J", "D"}, {"3", "D"}, {"5", "D"}});

        test.assertEquals("Hand 1 tie Hand 2; TIE FLUSHES",
                0, hand1Model.compareTo(hand2Model));

        // Flush vs high card
        hand1Model = convertToOb(new String[][] {{"A", "H"}, {"K", "H"}, {"J", "H"}, {"5", "H"}, {"3", "H"}});
        hand2Model = convertToOb(new String[][] {{"K", "S"}, {"A", "C"}, {"J", "H"}, {"3", "D"}, {"5", "D"}});

        test.assertEquals("Hand 1 wins Hand 2; Hand 2 is a highcard and hand 1 is a flush" ,
                1, hand1Model.compareTo(hand2Model));

        // Flush vs pair
        hand1Model = convertToOb(new String[][] {{"A", "3"}, {"A", "H"}, {"J", "C"}, {"K", "D"}, {"Q", "S"}});
        hand2Model = convertToOb(new String[][] {{"A", "D"}, {"A", "D"}, {"J", "D"}, {"3", "D"}, {"5", "D"}});

        test.assertEquals("Hand 1 loses Hand 2; hand 2 is a flush and hand 1 is a pair",
                -1, hand1Model.compareTo(hand2Model));
    }
}
