import java.util.ArrayList;
import java.util.Scanner;

public class CheatEngine {
    public static void main(String[] args) {
        //initialize arrays

        ArrayList<String> deck = new ArrayList<>();
        ArrayList<String> discard = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner keyboard = new Scanner(System.in);
        boolean activeGame = true;

        //add number cards
        deck.add("0");
        for (int i = 1; i <= 12; i++) {
            for (int n = 1; n <= (13 - (13 - i)); n++) {
                deck.add(String.valueOf(i));
            }
        }

        //add freeze
        for (int i = 1; i <= 3; i++) {
            deck.add("fr");
        }
        //add flip three
        for (int i = 1; i <= 3; i++) {
            deck.add("f3");
        }
        //add second chance
        for (int i = 1; i <= 3; i++) {
            deck.add("sc");
        }
        //add modifiers
        for (int i = 1; i <= 6; i++) {
            deck.add("mod");
        }

        System.out.println("How many players are there?");
        int playerCount = keyboard.nextInt();
        keyboard.nextLine();

        for (int i = 1; i <= playerCount; i++) {
            System.out.println("Player " + i + " name:");
            String playerName = keyboard.nextLine();
            Player player = new Player(playerName);
            players.add(player);
        }

        ArrayList<String> tempDeck = new ArrayList<>();
        tempDeck.addAll(deck);

        //run game loop
        while (activeGame) {
            System.out.println("[D]raw a card, [E]nd round, [S]huffle discard, [Q]uit:");
            String nextAction = keyboard.nextLine();

            if (nextAction.equals("d")) {
                Player player;

                System.out.println("Whomst is drawing:");
                String playerName = keyboard.nextLine();
                
                for (int i = 0; i < playerCount; i++) {
                    if (players.get(i).getName().equals(playerName)) {
                        player = players.get(i);

                        System.out.println("Probability of living is " + calculateProbability(player.getHand(), tempDeck));

                        System.out.println("Card:");
                        String card = keyboard.nextLine();

                        if (!card.equals("")) {
                            player.addCard(card);

                            for (int c = 0; c < tempDeck.size(); c++) {
                                if (tempDeck.get(c).equals(card)) {
                                    tempDeck.remove(c);
                                    c = tempDeck.size();
                                }
                            }
                        }
                    }
                }
            } else if (nextAction.equals("e")) {
                for (int i = 0; i < playerCount; i++) {
                    discard.addAll(players.get(i).clearHand());
                }
            } else if (nextAction.equals("s")) {
                tempDeck.addAll(discard);
                discard.clear();
            } else if (nextAction.equals("q")) {
                activeGame = false;
            }
        }

        keyboard.close();
    }

    public static String calculateProbability(ArrayList<String> hand, ArrayList<String> deck) {
        int totalCards = deck.size();
        ArrayList<String> safeCards = new ArrayList<>();

        for (int i = 0; i < deck.size(); i++) {
            if (hand.indexOf(deck.get(i)) == -1) {
                safeCards.add(deck.get(i));
            }
        } 

        double chanceToLive = ((double) safeCards.size() / totalCards) * 100;
        return Double.toString(chanceToLive) + "%";
    }
}
