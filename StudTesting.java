import java.util.ArrayList;

public class StudTesting {
    public static void main(String[] args)
    {
        Tester test = new Tester(true);

        Test1(test);
        Test2(test);
        Test3(test);
        Test4(test);
        Test5(test);
        Test6(test);
        Test7(test);
        Test8(test);
        Test9(test);
        Test10(test);
        Test11(test);
        Test12(test);
        Test13(test);

        test.finishTests();
    }

    /**
     * convert list to object StudPokerHand
     * @param listCard the assigned list
     * @param communityCardSet the assigned community set
     * @return the desired stud set
     */
    private static StudPokerHand convertStudToOb(String[][] listCard, CommunityCardSet communityCardSet)
    {
        StudPokerHand studSet = new StudPokerHand(communityCardSet);

        int indexRank = 0;

        int indexSuit = 1;

        for (String[] cards: listCard) {
            String rank = cards[indexRank];
            String suit = cards[indexSuit];
            Card obCard = new Card(rank,suit);
            studSet.addCard(obCard);
        }
        return studSet;
    }
    /**
     * convert list to object CommunityCardSet
     * @param listCard the assigned list
     * @return the desired community set
     */
    private static CommunityCardSet convertCommunityToOb(String[][] listCard)
    {
        CommunityCardSet communityCardSet = new CommunityCardSet();

        int indexRank = 0;

        int indexSuit = 1;

        for (String[] cards: listCard) {
            String rank = cards[indexRank];
            String suit = cards[indexSuit];
            Card obCard = new Card(rank,suit);
            communityCardSet.addCard(obCard);
        }
        return communityCardSet;
    }


    private static void Test1(Tester test)
    {
        // Testing 2 pairs in one hand
        String[][] community = new String[][] {{"2", "D"}, {"9", "S"}, {"5", "C"}, {"J", "S"}, {"6", "S"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"J", "S"}, {"2", "H"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"3", "S"}, {"K", "D"}}, obCommunity);

        ArrayList<Card> sevenCards = obStudSet1.collectSevenCards();

        test.assertEquals("Test 1: stud 1 wins - stud 1 has 2 pairs",
                1, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test2(Tester test)
    {
        // Testing 1 pair
        String[][] community = new String[][] {{"2", "D"}, {"9", "S"}, {"5", "C"}, {"J", "S"}, {"6", "S"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"9", "S"}, {"7", "H"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"K", "D"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 2: stud 1 wins - both has 1 pair but stud 1 higher rank",
                1, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test3(Tester test)
    {
        // Testing 2 pairs in two hands but different rank
        String[][] community = new String[][] {{"2", "D"}, {"9", "S"}, {"5", "C"}, {"J", "S"}, {"4", "S"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"2", "S"}, {"5", "H"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"4", "D"}}, obCommunity);

        test.assertEquals("Test 3: stud 1 wins - stud 2 and stud 1 both 2 pairs but different rank in stud 1",
                1, obStudSet1.compareTo(obStudSet2));

    }
    private static void Test4(Tester test)
    {
        // Test Flush
        String[][] community = new String[][] {{"2", "S"}, {"3", "S"}, {"4", "S"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"2", "S"}, {"3", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"4", "D"}}, obCommunity);

        test.assertEquals("Test 4: stud 1 wins - because it is flush",
                1, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test5(Tester test)
    {
        // Test 2 flushes but different ranks
        String[][] community = new String[][] {{"2", "S"}, {"3", "S"}, {"4", "S"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"2", "S"}, {"2", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"K", "S"}}, obCommunity);

        test.assertEquals("Test 5: both flushes but stud 2 higher rank",
                -1, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test6(Tester test)
    {
        // Test equal 1 pair case
        String[][] community = new String[][] {{"3", "S"}, {"A", "D"}, {"4", "C"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"3", "S"}, {"2", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"3", "S"}, {"K", "S"}}, obCommunity);

        test.assertEquals("Test 6: equal 1 pair case, but stud 2 higher rank on the other card",
                -1, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test7(Tester test)
    {
        // Test equal 2 pairs case
        String[][] community = new String[][] {{"3", "S"}, {"A", "D"}, {"5", "C"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"3", "S"}, {"2", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"3", "S"}, {"K", "S"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 7: equal two pairs and equal other card. Although it looks to be not equal",
                0, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test8(Tester test)
    {
        // Test high card
        String[][] community = new String[][] {{"3", "S"}, {"A", "D"}, {"4", "C"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"8", "S"}, {"9", "D"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"7", "C"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 8: high card but different ranks",
                1, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test9(Tester test)
    {
        // Test high card but equal ranks
        String[][] community = new String[][] {{"3", "S"}, {"A", "D"}, {"4", "C"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"2", "S"}, {"7", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"7", "S"}, {"2", "S"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 9: equal high card",
                0, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test10(Tester test)
    {
        // Test tie 1 pair
        String[][] community = new String[][] {{"3", "S"}, {"A", "D"}, {"4", "C"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"3", "S"}, {"4", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"4", "S"}, {"3", "S"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 10: Tie one pair",
                0, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test11(Tester test)
    {
        // Test tie 2 pairs
        String[][] community = new String[][] {{"3", "S"}, {"A", "D"}, {"5", "C"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"3", "S"}, {"2", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"3", "S"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 11: tie 2 pairs",
                0, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test12(Tester test)
    {
        // Test tie FLush
        String[][] community = new String[][] {{"3", "S"}, {"A", "S"}, {"4", "S"}, {"5", "D"}, {"6", "C"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"3", "S"}, {"2", "S"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"2", "S"}, {"3", "S"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 12: Tie flushes",
                0, obStudSet1.compareTo(obStudSet2));
    }
    private static void Test13(Tester test)
    {
        // Test tie FLush
        String[][] community = new String[][] {{"4", "C"}, {"8", "S"}, {"7", "D"}, {"10", "H"}, {"J", "S"}};
        CommunityCardSet obCommunity = convertCommunityToOb(community);

        StudPokerHand obStudSet1 = convertStudToOb(new String[][] {{"K", "S"}, {"8", "H"}}, obCommunity);
        StudPokerHand obStudSet2 = convertStudToOb(new String[][] {{"Q", "C"}, {"4", "D"}}, obCommunity);

        System.out.println(obStudSet1.getBestFiveCardHand());
        System.out.println(obStudSet2.getBestFiveCardHand());

        test.assertEquals("Test 13: Stud 1 wins -> 1 pair",
                1, obStudSet1.compareTo(obStudSet2));
    }

}
