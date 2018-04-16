import java.util.ArrayList;

public class Game_user {

	String userID;
	Egg egg;
	ArrayList<Game_poke> collection;
	ArrayList<Game_poke> party;
	boolean hatching = false;
	int steps = 0;

	
	public Game_user(String userID, ArrayList<Game_poke> party, ArrayList<Game_poke> collection, boolean hatching, int steps, Egg egg) {
		super();
		this.userID = userID;
		this.collection = collection;
		this.party = party;
		this.hatching = hatching;
		this.steps = steps;
		this.egg = egg;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ArrayList<Game_poke> getCollection() {
		return collection;
	}

	public void setCollection(ArrayList<Game_poke> collection) {
		this.collection = collection;
	}
	
	public ArrayList<Game_poke> getParty() {
		return party;
	}

	public void setParty(ArrayList<Game_poke> party) {
		this.party = party;
	}
	
	public boolean getHatching() {
		return hatching;
	}

	public void setHatching(boolean hatching) {
		this.hatching = hatching;
	}


	public int getSteps() {
		return steps;
	}
	
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public Egg getEgg() {
		return egg;
	}

	public void setEgg(Egg egg) {
		this.egg = egg;
	}


	@Override
	public String toString() {
		return "Game_user [userID=" + userID + ", collection=" + collection + "hatching?"+  hatching + "]" ;
	}
	
	

	
}
