package praksa;

public class Karakter {

	private String ime;
	private String fajl;
	private String kuca;
	private int srecan, tuzan, zaljubljen;

	Karakter(String ime, String kuca, String fajl) {
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

	// seteri za tugu, srecu i zaljubljenost
	public void postaviSrecu(int srecan) {
		this.srecan = srecan;
	}

	public void postaviTugu(int tuzan) {
		this.tuzan = tuzan;
	}

	public void postaviZaljubljenost(int zaljubljen) {
		this.zaljubljen = zaljubljen;
	}

	// geteri za tugu, srecu i zaljubljenost
	public int kolikoZaljubljen() {
		return zaljubljen;
	}

	public int kolikoSrecan() {
		return srecan;
	}

	public int kolikoTuzan() {
		return tuzan;
	}

	public void kakvaJeDispozicija() {
		if (srecan > tuzan)
			System.out.println(ime + " je vise srecna nego tuzna osoba i dispozicija joj je pozitivna.");
		else if (tuzan > srecan)
			System.out.println(ime + " je vise tuzna nego srecna osoba i dispozicija joj je negativna.\n");
		else if (srecan == tuzan && srecan + zaljubljen > tuzan)
			System.out.println(ime + " je vise srecna nego tuzna osoba i dispozicija joj je pozitivna.");
		else
			System.out.println(ime + " je srecna koliko i tuzna osoba.");
	}

	public void koViseVoli(Karakter osoba) {
		if (this.zaljubljen > osoba.zaljubljen)
			System.out.println(ime + " vise voli " + osoba.getIme());
		else
			System.out.println(osoba.getIme() + " vise voli " + ime);
	}

	public String ispisKaraktera() {
		return "Ime: " + ime + "\nKuca: " + kuca + "\nPoruke su u dokumentu: " + fajl;
	}

}
