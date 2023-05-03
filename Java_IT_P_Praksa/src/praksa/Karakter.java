package praksa;

public class Karakter {

	private String ime, kuca, fajl;
	private int srecan, tuzan, zaljubljen;
	
	Karakter(){}
	
	public Karakter(String ime, String kuca, String fajl) {
		this.ime = ime;
		this.kuca = kuca;
		this.fajl = fajl;
	}
	
	public String getIme() {
		return ime;
	}
	
	public String getKuca() {
		return kuca;
	}
	public String getFajl() {
		return fajl;
	}
	public void postaviSrecu(int srecan) {
		this.srecan = srecan;
	}
	public void postaviTugu(int tuzan) {
		this.tuzan = tuzan;
	}
	public void postaviZaljubljen(int zaljubljen) {
		this.zaljubljen = zaljubljen;
	}
	public int kolikoZaljubljen() {
		return zaljubljen;
	}

	public int kolikoSrecan() {
		return srecan;
	}

	public int kolikoTuzan() {
		return tuzan;
	}

}
