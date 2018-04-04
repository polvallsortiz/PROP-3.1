package presentationcontrol;

import domaincontrol.DomainCtrl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class PresentationCtrl {
    static String username;
    static Vector<Vector<String>>  hidato= new Vector<Vector<String>>();
    static Character celltype;
    static String adjacencytype;
    static int lines;
    static int columns;

    public static void extract_data(String input) {
        List<String> data = Arrays.asList(input.split(","));
        celltype = data.get(0).charAt(0);
        adjacencytype = data.get(1);
        lines = Integer.parseInt(data.get(2));
        columns = Integer.parseInt(data.get(3));
    }

    public static Vector<String> extract_line(String input) {
        Vector<String> aux = new Vector<>();
        List<String> data = Arrays.asList(input.split(","));
        for(int i = 0; i < columns; ++i) {
            aux.add(data.get(i));
        }
        return aux;
    }

    public static int newGame(String player)    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        username = scan.nextLine();
        System.out.print("Cell Type? [Q,H,T],Adjacency Type? [C,CA],Lines Number,Column Number\n THEN ALL THE LINES OF HIDATO \n");
        String input = "";
        while(input.length() == 0) input = scan.nextLine();
        extract_data(input);
        input = "";
        for(int i = 0; i < lines; ++i) {
            input = scan.nextLine();
            hidato.add(extract_line(input));
        }
        printHidato();
        DomainCtrl dc = new DomainCtrl();
        dc.createGame(username);
        return lines;

    }

    public static void printHidato() {
        System.out.print("Hola " + username + " benvingut al carregador d'Hidatos! \n");
        System.out.print("Has definit el tipus de cel·la com " + celltype + "\n");
        System.out.print("Amb adjacència " + adjacencytype + "\n");
        System.out.print("I finalment amb " + lines + " liníes i "  + columns + " columnes\n");
        System.out.print("\\\\\\\\ HIDATO COMING ///////\n");
        for(int i = 0; i < hidato.size(); ++i) {
            Vector<String> aux = new Vector<>();
            aux = hidato.elementAt(i);
            for(int j = 0; j < aux.size(); ++j) {
                System.out.print(aux.elementAt(j) + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        newGame("");
    }

}
