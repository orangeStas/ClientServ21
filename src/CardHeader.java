import java.util.ArrayList;


public class CardHeader {

    private boolean bust = false;
    private int CardTotal = 0;

    private ArrayList<String> Cards;
    private ArrayList<String> Aces;


    public CardHeader(String c1, String c2) {
        Cards = new ArrayList();
        Aces = new ArrayList();

        if (c1.split(" ")[0].equals("Ace")) {
            Aces.add(c1);
        } else {
            Cards.add(c1);
        }

        if (c2.split(" ")[0].equals("Ace")) {
            Aces.add(c2);
        } else {
            Cards.add(c2);
        }

        SetTotal();

    }


    public int GetCardTotal() {
        return CardTotal;
    }

    public void CardHit(String ca) {

        if (ca.split(" ")[0].equals("Ace")) {
            Aces.add("Ace");
        } else {
            Cards.add(ca);
        }

        if (Aces.size() != 0) {
            SetTotal();
        } else if (ca.split(" ")[0].equals("Jack")) {

            CardTotal += 2;
        } else if (ca.split(" ")[0].equals("Queen"))
            CardTotal += 3;


        else if (ca.split(" ")[0].equals("King"))
            CardTotal += 4;

        else {
            CardTotal += Integer.parseInt(ca.split(" ")[0]);
        }


        CheckBust();


    }

    private void SetTotal() {

        CardTotal = 0;
        for (String c : Cards) {
            if (c.split(" ")[0].equals("Jack"))
                CardTotal += 2;

            else if (c.split(" ")[0].equals("Queen"))
                CardTotal += 3;


            else if (c.split(" ")[0].equals("King"))
                CardTotal += 4;

            else
                CardTotal += Integer.parseInt(c.split(" ")[0]);

        }

        for (String a : Aces) {
            if (CardTotal <= 10) {
                CardTotal += 11;
            } else {
                CardTotal += 1;
            }

        }
    }//end ace total


    public boolean CheckBust() {
        if (CardTotal > 21) {
            bust = true;
        } else {
            bust = false;
        }

        return bust;
    }


}
