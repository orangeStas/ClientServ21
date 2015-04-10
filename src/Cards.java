

public class Cards {

	private String face;
    private String suite;
	
	public Cards(String suite, String face){
		this.face = face;
        this.suite = suite;
	}

	public String toString(){
		return face + " " + suite;
	}
}
