package drivers.driverGame;

import domaincontrol.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class DriverGame {
    public static void main (String[] args) {
        System.out.println("TEST GAME, Tria la opció: ");
        printIntructions();
        Integer decission;
        String username = "a";
        Scanner scan = new Scanner(System.in);
        Game g = null;
        Integer lines;
        Integer columns;
        Vector<Vector<String>> matrix = new Vector<>();
        decission = scan.nextInt();
        while (decission != 0) {
            switch (decission) {
                case 1:
                    System.out.println("Introdueixi nom d'usuari: ");
                    username = "";
                    while (username.length() == 0) username = scan.nextLine();
                    g = new Game(username);
                    break;
                case 2:
                    Player p = g.getPlayer();
                    System.out.println("ID de l'usuari: " + p.getId());
                    break;
                case 3:
                    System.out.println("puntuació de la partida: " + g.getScore());
                    break;
                case 4:
                    System.out.println("Introdueixi puntuació de la partida: ");
                    String puntuation = "";
                    while (puntuation.length() == 0) puntuation = scan.nextLine();
                    g.setScore(Double.parseDouble(puntuation));
                    break;
                case 5:
                    System.out.println("dificultat de la partida: " + g.getDifficulty());
                    break;
                case 6:
                    System.out.println("Introdueixi la dificultat de la partida: ");
                    String dificulty = "";
                    while (dificulty.length() == 0) dificulty = scan.nextLine();
                    g.setDifficulty(dificulty);
                    break;
                case 7:
                    Hidato newHidato = new Hidato();
                    newHidato.setHidato(matrix);
                    System.out.println("dificultat de la partida: " + g.defineGame(newHidato));
                    break;
                case 8:
                    Board b = new Square();
                    System.out.println("Introduexi nombre linies:");
                    lines = null;
                    while (lines == null) lines = scan.nextInt();
                    System.out.println("Introduexi nombre columnes:");
                    columns = null;
                    while (columns == null) columns = scan.nextInt();
                    System.out.println("Introduexi cada fila separada per ',':");
                    String input = "";
                    for (int i = 0; i < lines; ++i) {
                        while (input.length() == 0) input = scan.nextLine();
                        matrix.add(new Vector(Arrays.asList(input.split(","))));
                        input = "";
                    }
                    System.out.println("Introudeixi un tipus d'adjacència [C,CA]:");
                    String adjacency = "";
                    while (adjacency.length() == 0) adjacency = scan.nextLine();
                    Hidato hidato = new Hidato();
                    hidato.setHidato(matrix);
                    hidato.setAdjacencytype(adjacency);
                    b.createBoard(hidato);
                    //g.setBoard(b);
                    System.out.println("Taulell definit");
                    break;
                case 9:
                    //Board a = g.getBoard();
                    //if (a.solveHidato()) System.out.println("El taulell que té definit la partida té solució");
                    //else System.out.println("El taulell que té definit la partida no té solució");
                    break;

            }
            printIntructions();
            decission = scan.nextInt();
        }
        System.out.println("Fi del test GAME");
    }

    private static void printIntructions() {
        System.out.println("Per a poder provar les opcions 2-9 és condició indispensable fer primer la 1 ");
        System.out.print("1 - Inicialitzar una nova partida \n2 - Visualitzar el jugador que està jugant la partida\n" +
                "3 - Visualitzar la puntuació de la partida\n" +
                "4 - Canviar la puntuació de la partida\n" +
                "5 - Visualitzar la dificultat de la partida\n6 - Canviar la dificultat de la partida\n" +
                "7 - Definir un nou joc. Aquesta funció calcularà la dificultat de la partida\n" +
                "8 - Definir el taulell de la partida\n" +
                "9 - Obtenir el taulell de la partida\n0 - Sortir del sistema\n");
    }
}
