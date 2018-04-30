package drivers.driverPlayer;

import domaincontrol.Player;

import java.util.Scanner;

public class DriverPlayer  {
    public static void main(String[] args) {
        Player p = null;
        Integer decission;
        Scanner scan = new Scanner(System.in);
        System.out.println("TEST PLAYER, Tria la opció: ");
        System.out.println("Per a poder provar les opcions 2-3 és condició indispensable fer primer la 1 ");
        System.out.print("1 - Creadora \n2 - Setter de l'atribut 'id' \n" +
                "3 - Getter de l'atribut 'id' \n0 - Sortir del sistema\n");
        decission = scan.nextInt();
        while(decission != 0) {
            switch (decission) {
                case 1:
                    System.out.println("Introduexi un username:");
                    String username = "";
                    while(username.length() == 0) username = scan.nextLine();
                    p = new Player(username);
                    break;
                case 2:
                    System.out.println("Intruodueixi un username:");
                    String username2 = "";
                    while(username2.length() == 0) username2 = scan.nextLine();
                    p.setId(username2);
                    break;
                case 3:
                    System.out.println(p.getId());
                    break;
                default:
                    break;
            }
            System.out.print("1 - Creadora \n2 - Setter de l'atribut 'id' \n" +
                    "3 - Getter de l'atribut 'id' \n0 - Sortir del sistema\n");
            decission = scan.nextInt();
        }
        System.out.println("Final de PLAYER TEST");
    }
}
