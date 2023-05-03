package praksa;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		
		List<Karakter> osobe = FileHelper.loadMetaDataKarakter();

		// Ispisivanje poruka Daenerys
		String ime = "Daenerys Stormborn";

		Poruke.ispisPorukaZadateOsobe(ime);
		
		// Obavestenje o broju poruka koje je svaki karakter poslao i ispisivanje ukupnog broja poruka 
		Poruke.ukupanBrojPoruka();

		System.out.println();
		String[] sadrzajPoruka = new String[4];

		for (int i = 0; i < 4; i++) {
			sadrzajPoruka[i] = FileHelper.loadMessages(osobe.get(i).getFajl()).toString();
		}

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
		for (int j = 0; j < osobe.size(); j++) {
			int srecan = 0, tuzan = 0, zaljubljen = 0;
			for (int i = 0; i < emoji.length; i++) {
				// sablon je niz stringova
				Pattern pattern = Pattern.compile(emoji[i]);
				Matcher matcher2 = pattern.matcher(sadrzajPoruka[j]);

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
			System.out.println(osobe.get(j).getIme() + " u porukama ima " + srecan + " srecnih emodzija, " + tuzan
					+ " tuznih emodzija i " + zaljubljen + " zaljubljenih emodzija.");

			// postavljanje vrednosti srece tuge i zaljubljenosti svakoj osobi
			osobe.get(j).postaviSrecu(srecan);
			osobe.get(j).postaviTugu(tuzan);
			osobe.get(j).postaviZaljubljen(zaljubljen);
		}
		System.out.println();
		
		
		// Dispozicija osoba
		Dispozicija.kakvaJeDispozicija(osobe, "Daenerys Stormborn");
		Dispozicija.kakvaJeDispozicija(osobe, "Jon Snow");
		Dispozicija.kakvaJeDispozicija(osobe, "Tyrion Lannister");
		Dispozicija.kakvaJeDispozicija(osobe, "Cersei Lannister");
		
		// Osobe sa najpozitivnijom i najnegativnijom dispozicijom
		Dispozicija.najNajDispozicija(osobe);
		
		// Ko koga vise voli? Jon Snow ili Daenerys Stormborn
		Dispozicija.koViseVoli(osobe.get(0), osobe.get(1));

	}

}
