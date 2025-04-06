import java.util.Arrays;

public class VertragsKatasterSuche {

    public static void main(String[] args) {
        // Beispielhafte Vertragsnummern (nicht sortiert)
        int[] vertragsnummern = {2044, 1234, 8877, 1023, 2144, 1034, 1877, 2023, 3333, 1224, 1112, 5474};
        int gesucht = 1234;

        System.out.println(" Suche nach Vertrag: " + gesucht);

        //  1. Lineare Suche (unsortiert erlaubt)
        int linearIndex = lineareSuche(vertragsnummern, gesucht);
        if (linearIndex != -1) {
            System.out.println(" Lineare Suche: Vertrag gefunden an Position " + linearIndex);
        } else {
            System.out.println(" Lineare Suche: Vertrag nicht gefunden.");
        }

        //  2. Binäre Suche (muss sortiert sein!)
        Arrays.sort(vertragsnummern); // Erst sortieren!
        int binaerIndex = binaereSuche(vertragsnummern, gesucht);
        if (binaerIndex != -1) {
            System.out.println(" Binäre Suche: Vertrag gefunden an Position " + binaerIndex + " (in sortierter Liste)");
        } else {
            System.out.println(" Binäre Suche: Vertrag nicht gefunden.");
        }
    }

    // 🔹 Methode: Lineare Suche
    public static int lineareSuche(int[] daten, int ziel) {
        for (int i = 0; i < daten.length; i++) {
            if (daten[i] == ziel) {
                return i; // Vertrag gefunden
            }
        }
        return -1; // Nicht gefunden
    }

    // 🔹 Methode: Binäre Suche (eigene Implementierung, statt Arrays.binarySearch)
    public static int binaereSuche(int[] daten, int ziel) {
        int links = 0;
        int rechts = daten.length - 1;

        while (links <= rechts) {
            int mitte = (links + rechts) / 2;

            if (daten[mitte] == ziel) {
                return mitte; // Vertrag gefunden
            } else if (daten[mitte] < ziel) {
                links = mitte + 1; // Rechts weitersuchen
            } else {
                rechts = mitte - 1; // Links weitersuchen
            }
        }
        return -1; // Nicht gefunden
    }
}
