import java.util.Random;


public class Deck {

	private Cards[] deck;
	private int counter;
	private static final int NUMBER_OF_CARDS = 36;
	private static final Random rand = new Random();

	public Deck(){
		String[] faces = {"Ace","6","7","8","9","10","Jack","Queen","King"};
        String[] suits = {"Diamond", "Heart", "Spade", "Club"};
		deck = new Cards[NUMBER_OF_CARDS];
		counter = 0;

        int k = 0;
        for (int i = 0 ; i < suits.length; i++){
            for (int j = 0 ; j < faces.length ; j++){
                deck[k] = new Cards(suits[i], faces[j]);
                k++;
            }
        }

	}

	public void shuffle(){
		counter=0;

		for(int i=0; i<deck.length; i++){
			int random = rand.nextInt(NUMBER_OF_CARDS);
			Cards t = deck[i];
			deck[i] = deck[random];
			deck[random]=t;
		}
	}
	
	public String dealCard(){
		
		if(counter<deck.length){
			return deck[counter++].toString();
		}
		else{
			return null;
		}
	}



}