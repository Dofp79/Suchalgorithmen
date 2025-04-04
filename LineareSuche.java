/* Lineare Suche – O(n)
   Bei der linearen Suche („linear search“) wird jedes Element im Array der Reihe nach geprüft, bis das gesuchte      
   Element gefunden ist – oder das Ende erreicht ist.

   Vorteil: Funktioniert immer, auch bei unsortierten Listen
   Nachteil: Langsam bei großen Datenmengen – weil jedes Element geprüft werden muss */

public class LineareSuche {
    public static void main(String[] args) {
        int[] vertragsNummern = {1023, 2044, 1234, 8877, 3333};
        int gesucht = 1234;

        boolean gefunden = false;
        for (int i = 0; i < vertragsNummern.length; i++) {
            if (vertragsNummern[i] == gesucht) {
                System.out.println(" Vertrag " + gesucht + " gefunden an Position " + i);
                gefunden = true;
                break;
            }
        }

        if (!gefunden) {
            System.out.println(" Vertrag nicht gefunden.");
        }
    }
}
