import java.util.Random;

public class VertragsSucheRandon {

    public static void main(String[] args) {
        int[] vertragsNummern = {1023, 2044, 3333, 1234, 8877};

        boolean gefunden = istEnthalten(1234, vertragsNummern, 5);

        if (gefunden) {
            System.out.println(" Vertrag gefunden!");
        } else {
            System.out.println(" Vertrag nicht gefunden!");
        }
    }

    // Methode zur zufälligen Suche in einem Array
    public static boolean istEnthalten(int wert, int[] feld, int suchlaeufe) {
        // Zähler, wie viele Versuche bisher gemacht wurden
        int aktuellerSuchlauf = 0;
        // Solange noch Suchversuche übrig sind
        while (aktuellerSuchlauf < suchlaeufe) {
            // Zufälliger Index zwischen 0 und der letzten Position des Arrays
            int suchindex = zufallsIndex(0, feld.length - 1);
            // Prüfen: Ist der Wert an der zufällig gewählten Stelle gleich dem gesuchten Wert?
            if (feld[suchindex] == wert) {
                return true; // Wert wurde gefunden, Suche erfolgreich
            }
            // Wenn nicht gefunden: Suchlauf-Zähler erhöhen
            aktuellerSuchlauf++;
        }
        // Nach allen Suchläufen wurde der Wert nicht gefunden
        return false;
    }

    // Hilfsmethode für Zufallsindex
    public static int zufallsIndex(int von, int bis) {
        Random rand = new Random();
        return rand.nextInt((bis - von) + 1) + von;
    }
}
