package drivers.driverTriangle;

import domaincontrol.*;

import java.util.*;

public class DriverTriangle {
        public static void main(String[] args) {
            Integer decission;
            Utilities u = Utilities.getUtilities();
            Board t = new Triangle();
            Vector<Vector<String>> matrix = new Vector<>();
            Integer lines = null;
            Integer columns = null;
            Scanner scan = new Scanner(System.in);
            System.out.println("TEST TRIANGLE, Tria la opció: ");
            printIntructions();
            decission = scan.nextInt();
            while(decission != 0) {
                switch (decission) {
                    case 1:
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
                        System.out.println("Introudeixi un tipus d'adjacència [C]:");
                        String adjacency = "";
                        while(adjacency.length() == 0) adjacency = scan.nextLine();
                        Hidato hidato = new Hidato();
                        hidato.setHidato(matrix);
                        hidato.setAdjacencytype(adjacency);
                        t.createBoard(hidato);
                        break;
                    case 2:
                        u.printCellPositions(t.getCellPositions());
                        break;
                    case 3:
                        u.printCells(t.getVectorCell());
                        break;
                    case 4:
                        u.printAdjacencyMatrix(t.getAdjacencyMatrix());
                        break;
                    case 5:
                        if(t.solveHidato()) System.out.println("L'Hidato proposat té solució");
                        else System.out.println("L'Hidato proposat NO té solució. Provi definint un altre");
                        break;
                    case 6:
                        System.out.println("S'ha d'haver realitzat satisfactoriament el pas 5");
                        Map<Integer,Integer> cellPositionsProposal = new HashMap<>();
                        cellPositionsProposal = t.getCellPositionsProposalResult();

                        for(int num : cellPositionsProposal.keySet()) {
                            Integer pos = cellPositionsProposal.get(num);
                            Vector<String> vec = matrix.get(pos/lines);
                            vec.set(pos%columns,String.valueOf(num));
                            matrix.set(pos/lines,vec);
                        }
                        for(int i = 0; i < lines; ++i) {
                            Vector<String> v = matrix.get(i);
                            for(int j = 0; j < (columns-1); ++j) {
                                System.out.print(v.get(j) + ",");
                            }
                            System.out.print(v.get(columns-1));
                            System.out.print("\n");
                        }
                        break;
                    case 7:
                        u.setSeed(5);
                        Vector<Vector<String>> matrix2 = new Vector<>();
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
                            matrix2.add(new Vector(Arrays.asList(input.split(","))));
                            input = "";
                        }
                        System.out.println("Introudeixi un tipus d'adjacència [C]:");
                        adjacency = "";
                        while(adjacency.length() == 0) adjacency = scan.nextLine();
                        System.out.println("Introudeixi el nombre de forats");
                        String holes = "";
                        while(holes.length() == 0) holes = scan.nextLine();
                        System.out.println("Introudeixi el nombre de valors a mostrar");
                        String toShow = "";
                        while(toShow.length() == 0) toShow = scan.nextLine();
                        Hidato newHidato = new Hidato();
                        newHidato.setHidato(matrix2);
                        newHidato.setAdjacencytype(adjacency);
                        Integer result = t.generateHidato(newHidato, maxcolumns, Integer.parseInt(holes), Integer.parseInt(toShow));
                        if (result == 1) {
                            Vector<Cell> vectorCell = t.getVectorCell();
                            Vector<Vector<String>> mat = new Vector<>();
                            for(int i = 0; i < lines; ++i) { //LINIA
                                Vector<String> aux = new Vector<>();
                                for(int j = 0; j < columns; ++j) {
                                    Cell c = vectorCell.get(i*columns+j);
                                    switch (c.getNumber()) {
                                        case -3 :
                                            System.out.print("# ");
                                            break;
                                        case -2:
                                            System.out.print("* ");;
                                            break;
                                        case -1:
                                            System.out.print("? ");
                                            break;
                                        default:
                                            System.out.print(c.getNumber()+" ");
                                            break;
                                    }
                                }
                                System.out.print("\n");
                            }
                        }
                        else System.out.println("No s'ha pogut generar Hidato, provi amb un altre combinació");
                        break;
                    case 8:
                        System.out.println("Introudeixi el nombre que vol moure");
                        String positionSource = "";
                        while(positionSource.length() == 0) positionSource = scan.nextLine();
                        System.out.println("Introudeixi l'id de la cel·la on el vol moure");
                        String whereToMove = "";
                        while(whereToMove.length() == 0) whereToMove = scan.nextLine();
                        t.changeCellPositions(Integer.parseInt(positionSource), Integer.parseInt(whereToMove));
                        System.out.println("Posició actualitzada");
                        break;
                    case 9:
                        System.out.println("Introudeixi l'id de la cel·la a modificar");
                        String idSource = "";
                        while(idSource.length() == 0) idSource = scan.nextLine();
                        System.out.println("Introudeixi el nou valor de 'nombre'");
                        String newNumber = "";
                        while(newNumber.length() == 0) newNumber = scan.nextLine();
                        t.changeCellPositions(Integer.parseInt(idSource), Integer.parseInt(newNumber));
                        System.out.println("Cel·la actualitzada");
                        break;
                    default:
                        break;
                }
                printIntructions();
                decission = scan.nextInt();
            }
            System.out.println("Final de TRIANGLE TEST");
        }

    private static void printIntructions() {
        System.out.println("Per a poder provar les opcions 2-9 és condició indispensable fer primer la 1 ");
        System.out.print("1 - Inicialitzar taulell triangle \n2 - Visualitzar Posicions dels nombres\n" +
                "3 - Visualitzar tota la informació de cada cel·la del taulell\n" +
                "4 - Visualitzar matriu d'adjacències\n" +
                "5 - Solventar Hidato\n6 - Solució de l'Hidato\n" +
                "7 - Generar Hidato\n" +
                "8 - Canviar la posició d'un nombre al taulell\n" +
                "9 - Canviar valor d'una cel·la al taulell\n0 - Sortir del sistema\n");
    }
}


