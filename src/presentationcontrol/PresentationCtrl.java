package presentationcontrol;

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

    //NEEDED FOR GENERATOR
    static int holes;
    static int predefined;

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

    public static void extract_data_generator(String input) {
        List<String> data = Arrays.asList(input.split(","));
        celltype = data.get(0).charAt(0);
        adjacencytype = data.get(1);
        lines = Integer.parseInt(data.get(2));
        columns = Integer.parseInt(data.get(3));
        holes = Integer.parseInt(data.get(4));
        predefined = Integer.parseInt(data.get(5));

    }

    public static int matrix_generator(String input) {
        for(int i = 0; i < lines; ++i) {
            String auxiliar[] = new String[columns];
            Arrays.fill(auxiliar,"?");
            hidato.add(new Vector<String>(Arrays.asList(auxiliar)));
        }
        List<String> data = Arrays.asList(input.split(","));
        int hast = data.size();
        if(lines*columns < (hast + holes + predefined)) return 0;
        for(String actual : data) {
            int act = Integer.parseInt(actual);
            Vector<String> mod = hidato.get(act/lines);
            mod.set(act%lines,"#");
            hidato.set(act/lines,mod);
        }
        return 1;
    }

    public static int newGame(String player) {
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
        dc.newGame(username);
        dc.defineBoard(hidato,username,adjacencytype,celltype);
        return lines;
    }

    public static int newGenerator() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Cell Type? [Q,H,T], Adjacency Type? [C,CA], Lines Number, Column Number, Hole Number, Predefined Number\n");
        String input = "";
        while(input.length() == 0) input = scan.nextLine();
        extract_data_generator(input);
        input = "";
        System.out.print("Now all the positions ( Separated with ',' ) that the Hidato will be unaccessible (STARTING AT 0) \n");
        while(input.length() == 0) input = scan.nextLine();
        if(matrix_generator(input) != 0) {
            DomainCtrl dc = new DomainCtrl();
            Vector<Vector<String>> mat = dc.generateHidato(hidato,adjacencytype,celltype,holes,predefined);
            if(mat == null) {
                System.out.print("NO Possible\n");
                return 0;
            }
            else {
                for(int i = 0; i < lines; ++i) {
                    Vector<String> v = mat.get(i);
                    for(int j = 0; j < columns; ++j) {
                        System.out.print(v.get(j) + " ");
                    }
                    System.out.print("\n");
                }
                return 1;
            }
        }
        else {
            System.out.print("Comprova els paràmetres, segur que són correctes? \n");
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.print("Benvingut, indiqui què vol fer \n 1 - GENERAR HIDATO \n 2 - JUGAR HIDATO \n 0 - SORTIR DEL SISTEMA : \n");
        Scanner scan = new Scanner(System.in);
        int decission = scan.nextInt();
        while(decission != 0) {
            switch (decission) {
                case 1:
                    newGenerator();
                    break;

                case 2:
                    newGame("");
                    break;

                default:
                    break;
            }
            System.out.print("Hola de nou, indiqui què vol fer \n 1 - GENERAR HIDATO \n 2 - JUGAR HIDATO \n 0 - SORTIR DEL SISTEMA : \n");
            decission = scan.nextInt();
        }
        System.out.print("Gràcies i fins una altra!");
    }

}
