import java.util.ArrayList;


public class Playbj	{

	private boolean bust=false;
	private int CardTotal=0;

	private ArrayList<String> Cards;
	private ArrayList<String> Aces;


	public Playbj(String c1, String c2) {
		Cards = new ArrayList();
		Aces = new ArrayList();

		if (c1 == "Ace") {
			Aces.add(c1);
		}
		else{
			Cards.add(c1);
		}

		if (c2 == "Ace") {
			Aces.add(c2);
		}
		else {
			Cards.add(c2);
		}

		SetTotal();

	}


public int GetCardTotal() {
	return CardTotal;
}

public void CardHit(String ca){

	if (ca == "Ace") {
		Aces.add("Ace");
	}
	else{
		Cards.add(ca);
	}

	if(Aces.size() != 0){
		SetTotal();
	}

	else if (ca == "Jack"){

		CardTotal += 2;
	}
    else if (ca =="Queen")
        CardTotal += 3;


    else if (ca == "King")
        CardTotal += 4;

	else {
		CardTotal += Integer.parseInt(ca);
	}


	CheckBust();


}

private void SetTotal() {

	CardTotal = 0;
	for(String c : Cards){
		if (c == "Jack")
			CardTotal += 2;

        else if (c =="Queen")
            CardTotal += 3;


        else if (c == "King")
            CardTotal += 4;

		else
			CardTotal += Integer.parseInt(c);


	}

	for(String a : Aces){
		if (CardTotal <= 10){
			CardTotal += 11;
		}
		else { 
			CardTotal += 1;
		}

	}
}//end ace total


public boolean CheckBust(){
	if(CardTotal > 21){
		bust = true;
	}

	else {
		bust = false;
	}

	return bust;
}





}
