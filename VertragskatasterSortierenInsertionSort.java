/*
Vertragskataster: Sortieren der Verträge nach Vertragsnummer aufsteigend
mit Insertion Sort
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VertragskatasterSortierenInsertionSort {

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
        // Beispielhafte Vertragsliste (unsortiert!)
        List<Vertrag> kataster = new ArrayList<>();
        kataster.add(new Vertrag(2044, "Cloud-Service", "2022-08-01"));
        kataster.add(new Vertrag(1023, "IT-Wartung", "2021-05-15"));
        kataster.add(new Vertrag(3333, "Softwarepflege", "2023-01-10"));
        kataster.add(new Vertrag(1234, "Rechenzentrum", "2020-12-01"));
        kataster.add(new Vertrag(8877, "Supportvertrag", "2022-11-30"));

        System.out.println("🔍 Vor dem Sortieren:");
        printKataster(kataster);

        // 🛠️ Sortieren mit Insertion Sort
        sortiereAufsteigend(kataster);

        System.out.println("\n Nach dem Sortieren:");
        printKataster(kataster);
    }

    // Hilfsmethode zum Anzeigen der Verträge
    public static void printKataster(List<Vertrag> liste) {
        for (Vertrag v : liste) {
            System.out.println(v);
        }
    }

    // Methode: Sortiert ein Array in aufsteigender Reihenfolge
    public static void sortiereAufsteigend(List<Vertrag> kataster) {
        // Äußere Schleife: Gehe von links nach rechts durch das Array
        for (int i = 1; i < kataster.size(); i++) {
            // Innere Schleife: Vergleicht aktuelles Element rückwärts mit vorherigen
            for (int j = i; j > 0; j--) {
                 // Wenn aktuelles Element kleiner ist als das linke Nachbarelement
                if (kataster.get(j - 1).nummer > kataster.get(j).nummer) {
                    //  Tausch der Verträge (Swap)
                    Vertrag temp = kataster.get(j);
                    kataster.set(j, kataster.get(j - 1));
                    kataster.set(j - 1, temp);
                } else {
                    // Sobald sortiert, abbrechen
                    break;
                }
            }
        }
    }
}
