
public class Egg {

	String pokemon;
	int steps;
	boolean shiny;
	
	public Egg(String pokemon, int steps, boolean shiny) {
		this.pokemon = pokemon;
		this.steps = steps;
		this.shiny = shiny;
	}

	public String getPokemon() {
		return pokemon;
	}

	public void setPokemon(String pokemon) {
		this.pokemon = pokemon;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public boolean getShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	}

	@Override
	public String toString() {
		return "Egg [pokemon=" + pokemon + ", steps=" + steps + ", shiny=" + shiny + "]";
	}

	
}
