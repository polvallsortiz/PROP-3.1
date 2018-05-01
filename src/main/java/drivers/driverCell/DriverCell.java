package drivers.driverCell;

import domaincontrol.Cell;

import java.util.Scanner;

/**
 * Created by Joan on 29/04/2018.
 */
public class DriverCell {
    public static void main(String[] args) {
        Cell test = null;
        int id;
        int acces;
        boolean accessible;
        int number;
        Integer decission;
        Scanner scan = new Scanner(System.in);
        System.out.println("TEST CELL, Tria la opció: ");
        System.out.println("Per a poder provar les opcions 2-8 és condició indispensable fer primer la 1 ");
        System.out.print("1 - Generar cel·la \n2 - Setter del atribut 'id'\n" +
                "3 - Setter de l'atribut 'accessible'\n4 - Setter de l'atribut 'number'\n5 - Getter de l'atribut 'id'\n" +
                "6 - Getter de l'atribut 'Accessible'\n7 - Getter de l'atribut 'Number'\n8 - Printar la cel·la\n");
        decission = scan.nextInt();
        while (decission != 0){
            switch (decission){
                case 1:
                    System.out.println("Introdueixi id de la cel·la: ");
                    id = scan.nextInt();
                    System.out.println("És la cel·la accessible? SI = 1, NO = 0 ");
                    acces = scan.nextInt();
                    if (acces == 1) accessible=true;
                    else accessible=false;
                    System.out.println("Introdueixi valor de la cel·la: ");
                    number = scan.nextInt();
                    test = new Cell(id,accessible,number);
                    System.out.println("Cel·la creada ");
                    break;
                case 2:
                    System.out.println("Introdueixi el nou id de la cel·la: ");
                    id = scan.nextInt();
                    test.setId(id);
                    System.out.println("Id de la cel·la canviat ");
                    break;
                case 3:
                    System.out.println("Introdueixi la nova accessibilitat de la cel·la: ");
                    acces = scan.nextInt();
                    if (acces == 1) accessible=true;
                    else accessible=false;
                    test.setAccessible(accessible);
                    System.out.println("Accessibilitat de la cel·la canviat ");
                    break;
                case 4:
                    System.out.println("Introdueixi el nou id de la cel·la: ");
                    id = scan.nextInt();
                    test.setNumber(id);
                    System.out.println("Id de la cel·la canviat ");
                    break;
                case 5:
                    System.out.println("L'id de la cel·la és: "+ test.getId());
                    break;
                case 6:
                    boolean isAccessible = test.getAccessible();
                    if(isAccessible)System.out.println("La cel·la és accessible");
                    else System.out.println("La cel·la no és accessible");
                    break;
                case 7:
                    System.out.println("El nombre de la cel·la és: "+ test.getNumber());
                    break;
                case 8:
                    System.out.println("La informació de la cel·la és:");
                    test.printCell();
            }
            System.out.println("TEST CELL, Tria la opció: ");
            decission = scan.nextInt();
        }
    }
}
