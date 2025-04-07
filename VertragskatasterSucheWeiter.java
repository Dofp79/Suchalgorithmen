/*
Ziel dieses Schritts:
 - Benutzer kann nach Vertragsnummer, Name oder Datum suchen
 - Verträge werden aus einer Datei geladen
 - Lineare Suche für alle drei Felder
 - Modularer, OOP-tauglicher Code
*/

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class VertragskatasterSucheWeiter {

    // Interne Klasse: Repräsentiert einen Vertrag mit Nummer, Name und Datum
    static class Vertrag {
        int nummer;          // Vertragsnummer
        String name;         // Vertragsbezeichnung
        Date datum;          // Vertragsdatum (als Date-Objekt)

        // Konstruktor für Vertrag – nimmt Strings und wandelt Datum um
        public Vertrag(int nummer, String name, String datumStr) {
            this.nummer = nummer;
            this.name = name;
            try {
                // Datum aus Text in Date konvertieren (Format: yyyy-MM-dd)
                this.datum = new SimpleDateFormat("yyyy-MM-dd").parse(datumStr);
            } catch (Exception e) {
                // Fallback, falls Datum ungültig ist – aktuelles Datum
                this.datum = new Date();
            }
        }

        //  Methode zur Darstellung eines Vertragsobjekts als Text
        @Override
        public String toString() {
            return "📄 Vertrag #" + nummer + " | " + name + " | " + new SimpleDateFormat("yyyy-MM-dd").format(datum);
        }
    }

    public static void main(String[] args) {
        //  Verträge aus CSV-Datei einlesen
        List<Vertrag> kataster = ladeVertraegeAusDatei("vertraege.csv");

        //  Wenn keine Verträge geladen wurden, Programm abbrechen
        if (kataster.isEmpty()) {
            System.out.println("Keine Verträge geladen.");
            return;
        }

        // Benutzerabfrage: Wonach soll gesucht werden?
        Scanner sc = new Scanner(System.in);
        System.out.println(" Nach welchem Feld möchtest du suchen? (nummer/name/datum)");
        String feld = sc.nextLine().toLowerCase(); // Eingabe in Kleinbuchstaben

        //  Auswahl anhand Benutzer-Eingabe
        switch (feld) {
            case "nummer" -> {
                System.out.print("Vertragsnummer eingeben: ");
                int nr = sc.nextInt();
                Vertrag v = sucheNachNummer(kataster, nr);
                ausgabe(v); // Suchergebnis anzeigen
            }
            case "name" -> {
                System.out.print("Vertragsname eingeben: ");
                String name = sc.nextLine(); // String-Eingabe für Name
                Vertrag v = sucheNachName(kataster, name);
                ausgabe(v); // Suchergebnis anzeigen
            }
            case "datum" -> {
                System.out.print("Datum im Format yyyy-MM-dd eingeben: ");
                String datumStr = sc.nextLine(); // Eingabe als Text
                Vertrag v = sucheNachDatum(kataster, datumStr);
                ausgabe(v); // Suchergebnis anzeigen
            }
            default -> System.out.println(" Ungültige Eingabe."); // Fehlermeldung bei falscher Auswahl
        }
    }

    // CSV-Datei einlesen → Liste von Vertrag-Objekten zurückgeben
    public static List<Vertrag> ladeVertraegeAusDatei(String dateiname) {
        List<Vertrag> liste = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dateiname))) {
            String zeile;
            // Zeile für Zeile einlesen
            while ((zeile = br.readLine()) != null) {
                // Spalten aufteilen (Trennzeichen: ;)
                String[] teile = zeile.split(";");
                if (teile.length == 3) {
                    int nr = Integer.parseInt(teile[0]);  // Vertragsnummer
                    String name = teile[1];               // Vertragsname
                    String datum = teile[2];              // Vertragsdatum
                    liste.add(new Vertrag(nr, name, datum)); // Vertrag erstellen und hinzufügen
                }
            }
        } catch (IOException e) {
            // Fehlermeldung, falls Datei nicht gelesen werden kann
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
        return liste;
    }

    // Suche nach Vertragsnummer (lineare Suche)
    public static Vertrag sucheNachNummer(List<Vertrag> liste, int nummer) {
        for (Vertrag v : liste) {
            if (v.nummer == nummer) return v;
        }
        return null;
    }

    //  Suche nach Vertragsname (lineare Suche, case-insensitive)
    public static Vertrag sucheNachName(List<Vertrag> liste, String name) {
        for (Vertrag v : liste) {
            if (v.name.equalsIgnoreCase(name)) return v;
        }
        return null;
    }

    // Suche nach Datum
    public static Vertrag sucheNachDatum(List<Vertrag> liste, String datumStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(datumStr);
            for (Vertrag v : liste) {
                if (v.datum.equals(d)) return v;
            }
        } catch (Exception e) {
            System.out.println(" Fehlerhaftes Datumsformat.");
        }
        return null;
    }

    // Ausgabe-Methode
    public static void ausgabe(Vertrag v) {
        if (v != null) {
            System.out.println(" Vertrag gefunden:\n" + v);
        } else {
            System.out.println(" Kein Vertrag gefunden.");
        }
    }
}
