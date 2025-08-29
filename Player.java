import java.util.ArrayList;

public class Player {
    
    private ArrayList<String> hand = new ArrayList<>();
    private String playerName = "";

    public Player(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }

    public ArrayList<String> getHand() {
        return hand;
    }

    public void addCard(String card) {
        hand.add(card);
        System.out.println(hand);
    }

    public ArrayList<String> clearHand() {
        ArrayList<String> tempHand = new ArrayList<>();
        tempHand.addAll(hand);
        hand.clear();
        return tempHand;
    }
}
