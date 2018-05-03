package drivers.driverDomainCtrl;


import domaincontrol.DomainCtrl;
import domaincontrol.Utilities;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class DriverDomainCtrl {
    public static void main (String[] args){
        Integer decission;
        Scanner scan = new Scanner(System.in);
        showInstructions();
        Utilities u = Utilities.getUtilities();
        decission = scan.nextInt();
        DomainCtrl dc = new DomainCtrl();
        String username = "a";
        Vector<Vector<String>> matrix = new Vector<>();
        Integer lines= null;
        Integer columns = null;
        String adjacency = "";
        String cellType = "";
        while(decission != 0) {
            switch (decission){
                case 1:
                    System.out.println("Introdueixi nom d'usuari: ");
                    username = "";
                    while(username.length() == 0) username = scan.nextLine();
                    dc.newGame(username);
                    System.out.println("Variables de partida creades");
                    break;
                case 2:
                    System.out.println("Introduexi nombre linies:");
                    lines = null;
                    while(lines == null) lines = scan.nextInt();
                    System.out.println("Introduexi nombre columnes:");
                    columns = null;
                    while(columns == null) columns = scan.nextInt();
                    System.out.println("Introduexi cada fila separada per ',':");
                    String input = "";
                    for(int i = 0; i < lines; ++i) {
                        while(input.length() == 0) input = scan.nextLine();
                        matrix.add(new Vector(Arrays.asList(input.split(","))));
                        input = "";
                    }
                    System.out.println("Introudeixi un tipus d'adjacència [C,CA]:");
                    adjacency = "";
                    while(adjacency.length() == 0) adjacency = scan.nextLine();
                    System.out.println("Introudeixi un tipus de cel·la [Q,H,T]:");
                    cellType = "";
                    while(cellType.length() == 0) cellType = scan.nextLine();
                    Vector<Vector<String>> matrix2 = dc.defineBoard(matrix, username, adjacency, cellType.charAt(0));
                    if (matrix2 != null) {
                        for (int i = 0; i < lines; ++i) {
                            Vector<String> v = matrix2.get(i);
                            for (int j = 0; j < (columns - 1); ++j) {
                                System.out.print(v.get(j) + ",");
                            }
                            System.out.print(v.get(columns - 1));
                            System.out.print("\n");
                        }
                    }
                    else System.out.println("L'Hidato no té solució. Provi un altre.");
                    break;
                case 3:
                    u.setSeed(5);
                    Vector<Vector<String>> matrix3 = new Vector<>();
                    System.out.println("Introduexi nombre linies:");
                    lines = null;
                    while(lines == null) lines = scan.nextInt();
                    System.out.println("Introduexi nombre columnes:");
                    columns = null;
                    while(columns == null) columns = scan.nextInt();
                    Integer maxcolumns = columns;
                    System.out.println("Introduexi cada fila separada per ',':");
                    input = "";
                    for(int i = 0; i < lines; ++i) {
                        while(input.length() == 0) input = scan.nextLine();
                        matrix3.add(new Vector(Arrays.asList(input.split(","))));
                        input = "";
                    }
                    System.out.println("Introudeixi un tipus d'adjacència [C,CA]:");
                    adjacency = "";
                    while(adjacency.length() == 0) adjacency = scan.nextLine();
                    System.out.println("Introudeixi un tipus de cel·la [Q,H,T]:");
                    cellType = "";
                    while(cellType.length() == 0) cellType = scan.nextLine();
                    System.out.println("Introudeixi el nombre de forats");
                    String holes = "";
                    while(holes.length() == 0) holes = scan.nextLine();
                    System.out.println("Introudeixi el nombre de valors a mostrar");
                    String toShow = "";
                    while(toShow.length() == 0) toShow = scan.nextLine();
                    Vector<Vector<String>> matrix4 = dc.generateHidato(matrix3,adjacency,cellType.charAt(0),Integer.parseInt(holes),Integer.parseInt(toShow));
                    if (matrix4 != null) {
                        for (int i = 0; i < lines; ++i) {
                            Vector<String> v = matrix4.get(i);
                            for (int j = 0; j < (columns - 1); ++j) {
                                System.out.print(v.get(j) + ",");
                            }
                            System.out.print(v.get(columns - 1));
                            System.out.print("\n");
                        }
                    }
                    else System.out.println("No s'ha pogut generar un Hidato. Provi un altre combinació.");
            }
            showInstructions();
            decission = scan.nextInt();
        }
    }

    private static void showInstructions() {
        System.out.println("TEST DOMAIN CONTROL, Tria la opció: ");
        System.out.println("Per a poder provar les opcions 2-3 és condició indispensable fer primer la 1 ");
        System.out.print("1 - Inicialitzar les variables d'un joc nou\n" +
                "2 - Solventar Hidato\n3 - Generar Hidato\n0 - Sortir del sistema");
    }
}
