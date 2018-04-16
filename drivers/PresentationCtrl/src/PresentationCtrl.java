import domaincontrol.DomainCtrl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class PresentationCtrl {
    static Vector<Vector<String>>  hidato= new Vector<Vector<String>>();
    static Character celltype;
    static String adjacencytype;
    static int lines;
    static int columns;
    static String username;

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
        System.out.print("Put your username: ");
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
        DomainCtrl dc = new DomainCtrl();
        dc.defineBoard(hidato,username,adjacencytype,celltype);
        return lines;

    }

    public static void main(String[] args) {
        newGame("");
        /*hidatoPrinter hp = new hidatoPrinter();
        hp.setVisible(true);*/
    }

}
