/*
OOP + Suche + Benutzerinteraktion im Vertragskataster
Ziel:
Verträge als Objekte (mit Nummer, Name, Datum)
Lineare & binäre Suche nach Nummer, Name oder Datum
Benutzereingabe per Scanner
Realistische Vertragssimulation
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VertragskatasterOOP {

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
            return " Vertrag #" + nummer + " | " + name + " | " + new SimpleDateFormat("yyyy-MM-dd").format(datum);
        }
    }

    public static void main(String[] args) {
        // Liste mit Beispiel-Verträgen
        List<Vertrag> kataster = new ArrayList<>();
        kataster.add(new Vertrag(1023, "IT-Wartung", "2021-05-15"));
        kataster.add(new Vertrag(2044, "Cloud-Service", "2022-08-01"));
        kataster.add(new Vertrag(3333, "Softwarepflege", "2023-01-10"));
        kataster.add(new Vertrag(1234, "Rechenzentrum", "2020-12-01"));
        kataster.add(new Vertrag(8877, "Supportvertrag", "2022-11-30"));

        // Liste für Binäre Suche sortieren nach Vertragsnummer
        kataster.sort(Comparator.comparingInt(v -> v.nummer));

        Scanner sc = new Scanner(System.in);
        System.out.println("Vertragskataster: Nach welcher Vertragsnummer soll gesucht werden?");
        int gesucht = sc.nextInt();

        // 1. Lineare Suche
        Vertrag gefundenLinear = lineareSuche(kataster, gesucht);
        if (gefundenLinear != null) {
            System.out.println(" (Lineare Suche) Gefunden:\n" + gefundenLinear);
        } else {
            System.out.println(" Vertrag nicht gefunden.");
        }

        // 2. Binäre Suche (sortierte Liste)
        Vertrag gefundenBinaer = binaereSuche(kataster, gesucht);
        if (gefundenBinaer != null) {
            System.out.println(" (Binäre Suche) Gefunden:\n" + gefundenBinaer);
        } else {
            System.out.println(" Vertrag nicht gefunden (binär).");
        }
    }

    // Lineare Suche nach Vertragsnummer
    public static Vertrag lineareSuche(List<Vertrag> liste, int nummer) {
        for (Vertrag v : liste) {
            if (v.nummer == nummer) {
                return v;
            }
        }
        return null;
    }

    // Binäre Suche (nach Vertragsnummer)
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
}
