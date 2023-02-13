import java.io.*;

import static java.lang.System.out;

public class NQueenProblem {
    public static int damenAnzahl = 8;

    public static void main(String[] args) throws IOException {


        int[][] schachbrett = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
    };

        if (solve(schachbrett, 0) == false) {
            out.print("Es existiert keine Lösung");
            return;
        }

        ausgabe(schachbrett);
    }


    // Dies ist eine Funktion um zu überprüfen, ob eine Dame
    // auf Schachbrett[row][col] platziert werden kann.

    static boolean isSafe(int[][] schachbrett, int row, int col) {


        // Überprüfe diese Row auf der linken Seite
        for (int i = 0; i < col; i++) {
            if (schachbrett[row][i] == 1)
                return false;
        }

        // Überprüfe obere Diagonale auf der linken Seite
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (schachbrett[i][j] == 1)
                return false;
        }

        // Überprüfe untere Diagonale auf der linken Seite
        for (int i = row, j = col; j >= 0 && i < damenAnzahl; i++, j--) {
            if (schachbrett[i][j] == 1)
                return false;
        }

        return true;
    }


    static boolean solve(int[][] schachbrett, int col) throws IOException {
        // Wenn alle Damen (von damenAnzahl) platziert sind, dann return TRUE



        if (col >= damenAnzahl)
            return true;


        for (int i = 0; i < damenAnzahl; i++) {
            // Kontrolliert, ob die Dame auf schachbrett[i][col] platziert werden kann.


            if (isSafe(schachbrett, i, col)) {
                // Platziert die Dame auf schachbrett[i][col]

                schachbrett[i][col] = 1;

                // Rekursiver Aufruf um den Rest der Damen zu platzieren
                if (solve(schachbrett, col + 1)) {


                    return true;}

                // Wenn die Platzierung auf schachbrett[i][col] nicht zu einem Ergebnis kommt, dann entferne die Dame wieder von
                // schachbrett[i][col]
                schachbrett[i][col] = 0; // BACKTRACK

                //PrintWriter -- ANFANG
                PrintWriter out = new PrintWriter(new FileOutputStream(
                        new File("output.txt"),
                        true /* append = true */));

                String stringForFileOutput = "";
                for (int x = 0; x < schachbrett.length; x++) {
                    for (int j = 0; j < schachbrett.length; j++) {
                        stringForFileOutput+= " " + schachbrett[x][j] + " ";
                    }
                    stringForFileOutput+= "\n";
                }
                out.append(stringForFileOutput.replace('0', '_').replace('1', 'D'));
                out.append("\n");
                out.append("\n");
                out.close();
                //PrintWriter -- ENDE

            }


        }

        // Wenn die Dame nirgends in Row platziert werden kann, return false!
        return false;
    }


    static void ausgabe(int[][] schachbrett) throws IOException {
        String Ausgabe = "";
        for (int i = 0; i < schachbrett.length; i++) {
            for (int j = 0; j < schachbrett.length; j++) {
                Ausgabe+= " " + schachbrett[i][j] + " ";
            }
            Ausgabe+= "\n";
        }
        System.out.println(Ausgabe.replace('0', '_').replace('1', 'D'));

    }

 }
