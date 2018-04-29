package drivers.driverTriangle;

import drivers.driverTriangle.Triangle;
import domaincontrol.Utilities;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class DriverTriangle {
        public static void main(String[] args) {
            Triangle t = null;
            Integer decission;
            Scanner scan = new Scanner(System.in);
            System.out.println("TEST TRIANGLE, Tria la opció: ");
            System.out.print("1 - Calcular Matriu d'Adjacència \n0 - Sortir del sistema\n");
            decission = scan.nextInt();
            while(decission != 0) {
                switch (decission) {
                    case 1:
                        Vector<Vector<String>> matrix = new Vector<>();
                        System.out.println("Introduexi nombre linies:");
                        Integer lines = null;
                        while(lines == null) lines = scan.nextInt();
                        System.out.println("Introduexi nombre columnes:");
                        Integer columns = null;
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
                        t = new Triangle();
                        t.calculateAdjacencyMatrix(matrix,adjacency);
                        Utilities u = new Utilities();
                        u.printAdjacencyMatrix(t.getAdjacencyMatrix());
                        break;
                    default:
                        break;
                }
                System.out.print("1 - Calcular Matriu d'Adjacència \n0 - Sortir del sistema\n");
                decission = scan.nextInt();
            }
            System.out.println("Final de TRIANGLE TEST");
        }
}


