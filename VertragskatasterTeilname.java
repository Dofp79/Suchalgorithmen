/*
OOP + Suche + Benutzerinteraktion im Vertragskataster
Ziel:
- Verträge als Objekte (mit Nummer, Name, Datum)
- Lineare & binäre Suche nach Nummer
- Suche nach Teilbegriffen im Namen (contains)
- Benutzereingabe per Scanner
- Der Benutzer kann wählen:
	- nummer → Nummernsuche mit linear & binär
	- teilname → Teilbegriff im Namen (z. B. „service“)

*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VertragskatasterTeilname {

    // Vertrag als eigene Klasse mit Attributen
    static class Vertrag {
        int nummer;
        String name;
        Date datum;

        public Vertrag(int nummer, String name, String datumStr) {
            this.nummer = nummer;
            this.name = name;
            try {
                this.datum = new SimpleDateFormat("yyyy-MM-dd").parse(datumStr);
            } catch (ParseException e) {
                e.printStackTrace();
                this.datum = new Date();
            }
        }

        @Override
        public String toString() {
            return "📄 Vertrag #" + nummer + " | " + name + " | " + new SimpleDateFormat("yyyy-MM-dd").format(datum);
        }
    }

    public static void main(String[] args) {
        // Beispielhafte Vertragsliste
        List<Vertrag> kataster = new ArrayList<>();
        kataster.add(new Vertrag(1023, "IT-Wartung", "2021-05-15"));
        kataster.add(new Vertrag(2044, "Cloud-Service", "2022-08-01"));
        kataster.add(new Vertrag(3333, "Softwarepflege", "2023-01-10"));
        kataster.add(new Vertrag(1234, "Rechenzentrum", "2020-12-01"));
        kataster.add(new Vertrag(8877, "Supportvertrag", "2022-11-30"));
        kataster.add(new Vertrag(1723, "IT-Wartung", "2021-12-15"));
        kataster.add(new Vertrag(2144, "Cloud-Service", "2022-08-01"));
        kataster.add(new Vertrag(1133, "Softwarepflege", "2023-01-15"));
        kataster.add(new Vertrag(1214, "Rechenzentrum", "2020-12-04"));
        kataster.add(new Vertrag(8817, "Supportvertrag", "2022-01-30"));
	kataster.add(new Vertrag(8817, "Consulting", "2025-03-23"));

        // Für binäre Suche vorsortieren
        kataster.sort(Comparator.comparingInt(v -> v.nummer));

        Scanner sc = new Scanner(System.in);
        System.out.println("Vertragskataster: Nach welchem Feld möchtest du suchen?");
        System.out.println("Optionen: nummer / teilname");
        String feld = sc.nextLine().toLowerCase();

        switch (feld) {
            case "nummer" -> {
                System.out.print("Vertragsnummer eingeben: ");
                int gesucht = sc.nextInt();
                sc.nextLine(); // Scanner-Fix

                // Lineare Suche
                Vertrag gefundenLinear = lineareSuche(kataster, gesucht);
                if (gefundenLinear != null) {
                    System.out.println(" (Lineare Suche) Gefunden:\n" + gefundenLinear);
                } else {
                    System.out.println(" Vertrag nicht gefunden.");
                }

                // Binäre Suche
                Vertrag gefundenBinaer = binaereSuche(kataster, gesucht);
                if (gefundenBinaer != null) {
                    System.out.println(" (Binäre Suche) Gefunden:\n" + gefundenBinaer);
                } else {
                    System.out.println(" Vertrag nicht gefunden (binär).");
                }
            }

            case "teilname" -> {
                System.out.print("Suchbegriff im Vertragsnamen eingeben: ");
                String teil = sc.nextLine();

                List<Vertrag> trefferListe = sucheNachTeilbegriffImNamen(kataster, teil);
                if (!trefferListe.isEmpty()) {
                    System.out.println(" Gefundene Verträge:");
                    for (Vertrag v : trefferListe) {
                        System.out.println(v);
                    }
                } else {
                    System.out.println(" Keine passenden Verträge gefunden.");
                }
            }

            default -> System.out.println(" Ungültige Eingabe.");
        }
    }

    // Lineare Suche nach Nummer
    public static Vertrag lineareSuche(List<Vertrag> liste, int nummer) {
        for (Vertrag v : liste) {
            if (v.nummer == nummer) {
                return v;
            }
        }
        return null;
    }

    // Binäre Suche nach Nummer (Voraussetzung: sortierte Liste)
    public static Vertrag binaereSuche(List<Vertrag> liste, int nummer) {
        int links = 0;
        int rechts = liste.size() - 1;

        while (links <= rechts) {
            int mitte = (links + rechts) / 2;
            Vertrag v = liste.get(mitte);

            if (v.nummer == nummer) {
                return v;
            } else if (v.nummer < nummer) {
                links = mitte + 1;
            } else {
                rechts = mitte - 1;
            }
        }
        return null;
    }

    // Suche nach Teilbegriffen im Vertragsnamen
    public static List<Vertrag> sucheNachTeilbegriffImNamen(List<Vertrag> liste, String teilbegriff) {
        List<Vertrag> treffer = new ArrayList<>();
        for (Vertrag v : liste) {
            if (v.name.toLowerCase().contains(teilbegriff.toLowerCase())) {
                treffer.add(v);
            }
        }
        return treffer;
    }
}
