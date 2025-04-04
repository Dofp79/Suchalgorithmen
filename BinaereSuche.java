/* Binäre Suche – O(log n)
   Bei der binären Suche („binary search“) wird das Array in der Mitte geteilt – und in der Hälfte weitergesucht,      
   wo der Wert liegen könnte. */

import java.util.Arrays;

public class BinaereSuche {
    public static void main(String[] args) {
        int[] vertragsNummern = {1023, 1234, 2044, 3333, 8877}; // Sortiert!
        int gesucht = 2044;

        int ergebnis = Arrays.binarySearch(vertragsNummern, gesucht);

        if (ergebnis >= 0) {
            System.out.println(" Vertrag " + gesucht + " gefunden an Position " + ergebnis);
        } else {
            System.out.println(" Vertrag nicht gefunden.");
        }
    }
}
