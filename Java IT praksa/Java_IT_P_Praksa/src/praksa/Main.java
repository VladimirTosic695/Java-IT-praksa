package praksa;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void ukupanBrojPoruka(String nizFajlova[], Karakter likovi[]) {
		// ispisivanje broja poruka za svaku osobu i ukupnog broja poruka
		try {
			int ukupanBrojPoruka = 0;
			for (int i = 0; i < nizFajlova.length; i++) {
				FileReader fr = new FileReader("resource/message_logs/" + nizFajlova[i]);
				LineNumberReader lnr = new LineNumberReader(fr);
				// Krecemo od -1 jer se prvi red u fajlu odnosi na osobu ciji je cet a ne na
				// samu poruku
				int brojRedova = -1;
				String line;
				while ((line = lnr.readLine()) != null) {
					if (!line.isBlank())
						brojRedova++;
				}
				System.out.println("Broj poruka osobe " + likovi[i].getIme() + " je: " + brojRedova);
				lnr.close();
				ukupanBrojPoruka += brojRedova;
			}
			System.out.println("Ukupan broj svih poruka: " + ukupanBrojPoruka);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void najNajDispozicija(Karakter osoba[]) {
		int max = 0, min = 0;
		String najsrecnija = "", najtuznija = "";
		for (int i = 0; i < osoba.length; i++) {
			if (max < osoba[i].kolikoSrecan()) {
				max = osoba[i].kolikoSrecan();
				najsrecnija = osoba[i].getIme();
			}
			if (min < osoba[i].kolikoTuzan()) {
				min = osoba[i].kolikoTuzan();
				najtuznija = osoba[i].getIme();
			}
		}
		System.out.println(najsrecnija + " je najsrecnija osoba.(" + max + ") emodzija.");
		System.out.println(najtuznija + " je najtuznija osoba.(" + min + ") emodzija.\n");
	}

	public static void main(String[] args) {

		String metaData = FileHelper.loadMetaData();

		String poruke = "[a-z]+[0-9]+\\.txt";
		Pattern pat = Pattern.compile(poruke);
		Matcher matcher = pat.matcher(metaData);

		// Smestanje svih poruka u niz
		String fajloviPoruka[] = new String[4];
		int brojac = 0;

		while (matcher.find()) {
			fajloviPoruka[brojac] = matcher.group();
			brojac++;
		}

		String Imena = "[A-Z]+[a-z]+ [A-Z]+[a-z]+";
		pat = Pattern.compile(Imena);
		matcher = pat.matcher(metaData);

		List<String> imenaIKuce = new ArrayList<String>();
		List<String> kuce = new ArrayList<String>();
		List<String> imena = new ArrayList<String>();

		while (matcher.find()) {
			imenaIKuce.add(matcher.group());
		}
		// Razdvajanje podataka o imenima i kucama iz jedne liste u dve nove
		for (int i = 0; i < imenaIKuce.size(); i += 2) {
			kuce.add(imenaIKuce.get(i + 1));
			imena.add(imenaIKuce.get(i));
		}
		// brisanje liste
		imenaIKuce.clear();

		// Kreiranje niza karaktera
		Karakter likovi[] = new Karakter[4];

		for (int i = 0; i < kuce.size(); i++) {
			String ime = imena.get(i);
			String kuca = kuce.get(i);
			String poruka = fajloviPoruka[i];
			likovi[i] = new Karakter(ime, kuca, poruka);
		}

		System.out.println("Sve poruke Daenerys: ");
		System.out.println(FileHelper.loadMessages(likovi[0].getFajl()).toString());

		System.out.println();
		// Pozivanje staticke metode za ispisivanje koliko poruka je koja osoba poslala
		ukupanBrojPoruka(fajloviPoruka, likovi);

		System.out.println();
		// sabloni za pretragu srece, tuge i zaljubljenosti
		String emoji[] = { "😄", "🙂", "😊", "😍", "😢", "😭", "😞", "👿", "😘" };

		// Liste za poredjenje sa dodatim vrednostima koje sluze za poredjenje sa
		// sablonima
		List<String> happy = new ArrayList<String>();
		List<String> sad = new ArrayList<String>();
		List<String> loving = new ArrayList<String>();
		// dodavanje u listu
		for (int i = 0; i < 4; i++) {
			happy.add(emoji[i]);
			sad.add(emoji[i + 4]);
		}
		// dodavanje u listu
		loving.add(emoji[3]);
		loving.add(emoji[8]);

		// provera upotrebe smajlija za svaku osobu
		for (int j = 0; j < likovi.length; j++) {
			int srecan = 0, tuzan = 0, zaljubljen = 0;
			for (int i = 0; i < emoji.length; i++) {
				// sablon je niz stringova
				Pattern pattern = Pattern.compile(emoji[i]);
				// poredjenje sa tekstovima svake osobe dobijenim preko metode getFajl() klase
				// Karakter
				Matcher matcher2 = pattern.matcher(FileHelper.loadMessages(likovi[j].getFajl()).toString());

				while (matcher2.find()) {
					if (happy.contains(matcher2.group()))
						srecan++;
					if (sad.contains(matcher2.group()))
						tuzan++;
					if (loving.contains(matcher2.group()))
						zaljubljen++;

				}
			}
			// Ispisivanje ko je koliko srecan, tuzan i zaljubljen
			System.out.println(likovi[j].getIme() + " u porukama ima " + srecan + " srecnih emodzija, " + tuzan
					+ " tuznih emodzija i " + zaljubljen + " zaljubljenih emodzija.");

			// postavljanje vrednosti srce tuge i zaljubljenosti svakoj osobi
			likovi[j].postaviSrecu(srecan);
			likovi[j].postaviTugu(tuzan);
			likovi[j].postaviZaljubljenost(zaljubljen);
		}

		// Ispitivanje kakva je dispozicija osoba
		System.out.println("\nDispozicija osoba: ");
		for (Karakter osoba : likovi) {
			osoba.kakvaJeDispozicija();
		}
		
		// Osobe sa najpozitivnijom i najnegativnijom dispozicijom
		najNajDispozicija(likovi);
		
		// Ispitivanje ko koga vise voli
		likovi[0].koViseVoli(likovi[1]);

	}

}
