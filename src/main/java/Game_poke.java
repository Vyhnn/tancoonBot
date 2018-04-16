
public class Game_poke {

	String species;
	int ability;
	int exp;
	boolean shiny;
	String item;
	int[] iv;
	

	public Game_poke(String species, int ability, int exp, boolean shiny, String item, int[] iv) {

		this.species = species;
		this.ability = ability;
		this.exp = exp;
		this.shiny = shiny;
		this.item = item;
		this.iv = iv;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getAbility() {
		return ability;
	}

	public void setAbility(int ability) {
		this.ability = ability;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isShiny() {
		return shiny;
	}

	public void setShiny(boolean shiny) {
		this.shiny = shiny;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int[] getIv() {
		return iv;
	}

	public void setIv(int[] iv) {
		this.iv = iv;
	}

}
