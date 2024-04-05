import java.util.Scanner;

public class MainLaby {
    public static void main(String[] args) {
        System.out.println("Nom du fichier a charger : " + args[0]);
        try {
            Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/"+args[0]);
            System.out.println("Labyrinthe charge :\n" + l);
            System.out.println("Saisir une action (haut, bas, gauche, droite, exit) : ");
            Scanner sc = new Scanner(System.in);
            String action = sc.nextLine();
            while (!action.equals("exit") && !l.etreFini()) {
                try {
                    l.deplacerPerso(action);
                    System.out.println("Labyrinthe :\n" + l);
                    if (l.etreFini()) {
                        System.out.println("Bravo, vous avez gagne !");
                    } else {
                        System.out.println("Saisir une action (haut, bas, gauche, droite, exit) : ");
                        action = sc.nextLine();
                    }
                } catch (ActionInconnueException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Labyrinthe :\n" + l);
                    System.out.println("Saisir une action (haut, bas, gauche, droite, exit) : ");
                    action = sc.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du labyrinthe : " + e.getMessage());
        }

    }
}
