package praksa;

import java.util.List;

public class Poruke {

	private static List<Karakter> lik = FileHelper.loadMetaDataKarakter();

	public static void ispisPorukaZadateOsobe(String name) {
		for (int i = 0; i < lik.size(); i++) {
			if (lik.get(i).getIme().contains(name)) {
				String file = lik.get(i).getFajl();
				System.out.println("Ispisujemo sve poruke osobe " + name + ": \n");
				for (int j = 1; j < FileHelper.loadMessages(file).size(); j++) {
					System.out.println(FileHelper.loadMessages(file).get(j));
				}
			}
		}
	}

	public static void ukupanBrojPoruka() {
		// ispisivanje broja poruka za svaku osobu i ukupnog broja poruka
		int ukupanBrojPoruka = 0;
		System.out.println("\nIspisivanje koliko je koja osoba poslala poruka i ukupnog broja svih poruka! \n");
		for (int i = 0; i < lik.size(); i++) {
			int brojRedova = 0;
			String file = lik.get(i).getFajl();
			for (int j = 1; j < FileHelper.loadMessages(file).size(); j++) {
				if (!FileHelper.loadMessages(file).get(j).isBlank())
					brojRedova++;
			}
			System.out.println("Broj poruka osobe " + lik.get(i).getIme() + " je: " + brojRedova);
			ukupanBrojPoruka += brojRedova;

		}
		System.out.println("Ukupan broj svih poruka: " + ukupanBrojPoruka);
	}

}
