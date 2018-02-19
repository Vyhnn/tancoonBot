public class pokemon {
	
	String name;
	String ndex;
	String evo;
	String type;
	String ability;
	String ha;
	String gender;
	String egggroup;
	String eggsteps;
	String hp;
	String at;
	String df;
	String sa;
	String sd;
	String sp;
	String moves;
	String TM;
	String eggmoves;
	String img;
	String url;
	
	public pokemon(String name, String ndex, String evo, String type, String ability, String ha, String gender, String egggroup,
			String eggsteps, String hp, String at, String df, String sa, String sd, String sp, String moves, String TM, String eggmoves, String img, String url) {
		super();
		this.name = name;
		this.ndex = ndex;
		this.evo = evo;
		this.type = type;
		this.ability = ability;
		this.ha = ha;
		this.gender = gender;
		this.egggroup = egggroup;
		this.eggsteps = eggsteps;
		this.hp = hp;
		this.at = at;
		this.df = df;
		this.sa = sa;
		this.sd = sd;
		this.sp = sp;
		this.moves = moves;
		this.TM = TM;
		this.eggmoves = eggmoves;
		this.img = img;
		this.url = url;
	}

	public pokemon(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNdex() {
		return ndex;
	}

	public void setNdex(String ndex) {
		this.ndex = ndex;
	}
	
	public String getEvo() {
		return evo;
	}

	public void setEvo(String evo) {
		this.evo = evo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}
	
	public String getHa() {
		return ha;
	}

	public void setHa(String ha) {
		this.ha = ha;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEgggroup() {
		return egggroup;
	}

	public void setEgggroup(String egggroup) {
		this.egggroup = egggroup;
	}

	public String getEggsteps() {
		return eggsteps;
	}

	public void setEggsteps(String eggsteps) {
		this.eggsteps = eggsteps;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	public String getDf() {
		return df;
	}

	public void setDf(String df) {
		this.df = df;
	}

	public String getSa() {
		return sa;
	}

	public void setSa(String sa) {
		this.sa = sa;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}
	
	public String getMoves() {
		return moves;
	}

	public void setMoves(String moves) {
		this.moves = moves;
	}
	
	public String getTM() {
		return TM;
	}

	public void setTM(String TM) {
		this.TM = TM;
	}

	public String getEggmoves() {
		return eggmoves;
	}

	public void setEggmoves(String eggmoves) {
		this.eggmoves = eggmoves;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "pokemon [name=" + name + ", ndex=" + ndex + ", evo=" + evo + ", type=" + type + ", Ability=" + ability + ", Ha =" + ha + ", Gender =" + gender +", egggroup=" + egggroup + ", eggsteps=" + eggsteps + ", hp=" + hp + ", at=" + at + ", df="
				+ df + ", sa=" + sa + ", sd=" + sd + ", sp=" + sp + ", moves =" + moves + ", TM =" + TM + ", eggmoves =" + eggmoves + ", img=" + img + ", url=" + url + "]";
	}

	
	
}
