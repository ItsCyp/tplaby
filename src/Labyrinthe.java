import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Squelette de classe labyrinthe
 */
class Labyrinthe{

    // constante de type char
    static final char MUR = 'X';
    static final char PJ = 'P';
    static final char SORTIE = 'S';
    static final char VIDE = '.';

    // constante de type String
    static final String HAUT = "haut";
    static final String BAS = "bas";
    static final String GAUCHE = "gauche";
    static final String DROITE = "droite";

    // attributs
    private boolean[][] murs;
    private Personnage personnage;
    private Sortie sortie;


    /**
     * methode permettant de retourner le caractere decrivant le contenu de la case (x,y)
     * @param x position x
     * @param y position y
     * @return un caractere decrivant le contenu de la case (x,y)
     */
    public char getChar(int x, int y) {
        if((x == this.personnage.getX()) && y == this.personnage.getY()){
            return PJ;
        }

        if((x == this.sortie.getX()) && y == this.sortie.getY()){
            return SORTIE;
        }

        if(!this.murs[x][y]){
            return VIDE;
        }else{
            return MUR;
        }
    }

    /**
     * methode permettant de retourner la case suivante en fonction de l'action
     * @param x position x (la ligne)
     * @param y position y (la colonne)
     * @param action la direction passé en paramètre
     * @return zzzz
     */
    public static int[] getSuivant(int x, int y, String action) {
        int[] res = new int[2];
        switch (action) {
            case HAUT:
                res[0] = x - 1;
                res[1] = y;
                break;
            case BAS:
                res[0] = x + 1;
                res[1] = y;
                break;
            case GAUCHE:
                res[0] = x;
                res[1] = y - 1;
                break;
            case DROITE:
                res[0] = x;
                res[1] = y + 1;
                break;
            default:
                res[0] = -9999;
                res[1] = -9999;
        }
        return res;
    }


    /**
     * methode permettant de deplacer le personnage dans le labyrinthe
     * @param action action a effectuer
     * @throws ActionInconnueException si aucune action ne correspond
     */
    public void deplacerPerso(String action) throws ActionInconnueException {
        boolean collision = false;
        while (!collision) {
            int[] res = getSuivant(this.personnage.getX(), this.personnage.getY(), action);

            if (res[0] == -9999 && res[1] == -9999) {
                throw new ActionInconnueException("Action inconnue : " + action + ". Les actions possibles sont : haut, bas, gauche, droite.");
            }

            if (this.murs[res[0]][res[1]]) {
                collision = true;
            } else {
                this.personnage.setX(res[0]);
                this.personnage.setY(res[1]);
            }
        }
    }


    /**
     * methode permettant de retourner l'état du labyrinthe sous forme de chaine de caractere
     * @return une chaine de caractere representant l'état du labyrinthe
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < this.murs.length; i++){
            for(int j = 0; j < this.murs[0].length; j++){
                res.append(getChar(i, j));
            }
            res.append("\n");
        }
        return res.toString();
    }


    /**
     * methode permettant de savoir si le jeu est fini (le personnage est sur la sortie)
     * @return boolean permettant d'arrêter le jeu lorsque le personnage se trouve sur la sorti
     */
    public boolean etreFini() {
        return (this.personnage.getX() == this.sortie.getX() && this.personnage.getY() == this.sortie.getY());
    }

    /**
     * methode permettant de charger un labyrinthe à partir d'un fichier
     * @param nom nom du fichier à charger
     * @return un labyrinthe
     */
    public static Labyrinthe chargerLabyrinthe(String nom) throws FichierIncorrectException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nom));

            int x, y;
            try{
                x = Integer.parseInt(reader.readLine());
                y = Integer.parseInt(reader.readLine());
            }catch (NumberFormatException e) {
                throw new FichierIncorrectException("Problème de numéro de ligne ou de colonne.");
            }

            boolean[][] murs = new boolean[x][y];
            Personnage personnage = null;
            Sortie sortie = null;

            int nbPersonnage = 0, nbSortie = 0;
            for (int i = 0; i < x; i++) {
                String line = reader.readLine();
                if(line == null || line.length() != y){
                    throw new FichierIncorrectException("Le nombre de colonnes ne correspond pas.");
                }
                for (int j = 0; j < y; j++) {
                    char c = line.charAt(j);
                    switch (c) {
                        case MUR:
                            murs[i][j] = true;
                            break;
                        case VIDE:
                            murs[i][j] = false;
                            break;
                        case PJ:
                            if(personnage != null) throw new FichierIncorrectException("Plusieurs personnage.");
                            personnage = new Personnage(i, j);
                            nbPersonnage++;
                            break;
                        case SORTIE:
                            if (sortie != null) throw new FichierIncorrectException("Plusieurs sorties.");
                            sortie = new Sortie(i, j);
                            nbSortie++;
                            break;
                        default:
                            throw new FichierIncorrectException("Caractère inconnu : " + c);
                    }
                }
            }

            if(nbPersonnage < 1) throw new FichierIncorrectException("Personnage inconnu.");
            if(nbSortie < 1) throw new FichierIncorrectException("Sortie inconnue.");

            if(reader.readLine() != null) throw new FichierIncorrectException("Le nombre de lignes ne correspond pas.");

            reader.close();

            Labyrinthe labyrinthe = new Labyrinthe();
            labyrinthe.murs = murs;
            labyrinthe.personnage = personnage;
            labyrinthe.sortie = sortie;

            return labyrinthe;
        }catch (IOException e) {
            throw new FichierIncorrectException("Erreur de lecture du fichier : " + nom);
        }
    }

    /**
     * methode permettant de retourner le personnage
     * @return le personnage
     */
    public Personnage getPersonnage() {
        return personnage;
    }

    /**
     * methode permettant de retourner la sortie
     * @return la sortie
     */
    public Sortie getSortie() {
        return sortie;
    }
}
