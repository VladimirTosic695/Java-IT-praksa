package praksa;

import java.util.List;

public class Dispozicija {

	public static void najNajDispozicija(List<Karakter> osobe) {
		int max = 0, min = 0;
		String najsrecnija = "", najtuznija = "";
		for (int i = 0; i < osobe.size(); i++) {
			if (max < osobe.get(i).kolikoSrecan()) {
				max = osobe.get(i).kolikoSrecan();
				najsrecnija = osobe.get(i).getIme();
			}
			if (min < osobe.get(i).kolikoTuzan()) {
				min = osobe.get(i).kolikoTuzan();
				najtuznija = osobe.get(i).getIme();
			}
		}
		System.out.println(najsrecnija + " je najsrecnija osoba.(" + max + ") emodzija.");
		System.out.println(najtuznija + " je najtuznija osoba.(" + min + ") emodzija.\n");
	}

	public static void kakvaJeDispozicija(List<Karakter> osobe, String name) {
		for (int i = 0; i < osobe.size(); i++) {
			if (osobe.get(i).getIme().contains(name)) {
				if (osobe.get(i).kolikoSrecan() > osobe.get(i).kolikoTuzan())
					System.out.println(
							osobe.get(i).getIme() + " je vise srecna nego tuzna osoba i dispozicija joj je pozitivna.");
				else if (osobe.get(i).kolikoTuzan() > osobe.get(i).kolikoSrecan())
					System.out.println(osobe.get(i).getIme()
							+ " je vise tuzna nego srecna osoba i dispozicija joj je negativna.\n");
				else if (osobe.get(i).kolikoSrecan() == osobe.get(i).kolikoTuzan()
						&& osobe.get(i).kolikoSrecan() + osobe.get(i).kolikoZaljubljen() > osobe.get(i).kolikoTuzan())
					System.out.println(
							osobe.get(i).getIme() + " je vise srecna nego tuzna osoba i dispozicija joj je pozitivna.");
				else
					System.out.println(osobe.get(i).getIme() + " je srecna koliko i tuzna osoba.");
			}

		}
	}

	public static void koViseVoli(Karakter osoba1, Karakter osoba2) {

		if (osoba1.kolikoZaljubljen() > osoba2.kolikoZaljubljen())
			System.out.println(osoba1.getIme() + " vise voli " + osoba2.getIme());
		else
			System.out.println(osoba2.getIme() + " vise voli " + osoba1.getIme());
	}

}
