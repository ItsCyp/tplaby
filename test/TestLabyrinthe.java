import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLabyrinthe {

    /**
     * Attribut contenant le labyrinthe à tester
     */
    private Labyrinthe l;

    /**
     * Méthode exécutée avant chaque test
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Initialise le labyrinthe avant chaque test
        l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
    }

    /**
     * --> Teste la méthode getChar (ou plutot des positions du labyrinthe) <--
     */

    @Test
    public void test1_getchar_position_vide() {
        // Teste la méthode getChar avec un labyrinthe vide
        assertEquals('.', l.getChar(1, 5));
    }

    @Test
    public void test2_getchar_position_mur() {
        // Teste la méthode getChar avec un mur
        assertEquals('X', l.getChar(0, 0));
    }

    @Test
    public void test3_getchar_position_personnage() {
        // Teste la méthode getChar avec un personnage
        assertEquals('P', l.getChar(2, 3));
    }

    @Test
    public void test4_getchar_position_sortie() {
        // Teste la méthode getChar avec une sortie
        assertEquals('S', l.getChar(1, 1));
    }

    @Test
    public void test5_getchar_position_hors_limites() {
        // Teste la méthode getChar avec une position hors limites (renvoie une exception)
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> l.getChar(1000, 1000));
    }


    /**
     * --> Teste la méthode getSuivant <--
     */

    @Test
    public void test6_getsuivant_haut() {
        // Teste la méthode getSuivant avec la direction "haut"
        int[] res = Labyrinthe.getSuivant(2, 3, "haut");
        assertEquals(1, res[0]);
        assertEquals(3, res[1]);
    }

    @Test
    public void test7_getsuivant_bas() {
        // Teste la méthode getSuivant avec la direction "bas"
        int[] res = Labyrinthe.getSuivant(2, 3, "bas");
        assertEquals(3, res[0]);
        assertEquals(3, res[1]);
    }

    @Test
    public void test8_getsuivant_gauche() {
        // Teste la méthode getSuivant avec la direction "gauche"
        int[] res = Labyrinthe.getSuivant(2, 3, "gauche");
        assertEquals(2, res[0]);
        assertEquals(2, res[1]);
    }

    @Test
    public void test9_getsuivant_droite() {
        // Teste la méthode getSuivant avec la direction "droite"
        int[] res = Labyrinthe.getSuivant(2, 3, "droite");
        assertEquals(2, res[0]);
        assertEquals(4, res[1]);
    }

    @Test
    public void test10_getsuivant_direction_invalide() {
        // Teste la méthode getSuivant avec une direction invalide
        int[] res = Labyrinthe.getSuivant(2, 3, "milieu");
        assertEquals(-9999, res[0]);
        assertEquals(-9999, res[1]);
    }

    /**
     * --> Teste la méthode deplacerPerso <--
     */

    @Test
    public void test11_deplacerperso_haut() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec la direction "haut"
        l.deplacerPerso("haut");
        assertEquals(1, l.getPersonnage().getX());
        assertEquals(3, l.getPersonnage().getY());
    }

    @Test
    public void test12_deplacerperso_bas() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec la direction "bas"
        l.deplacerPerso("bas");
        assertEquals(3, l.getPersonnage().getX());
        assertEquals(3, l.getPersonnage().getY());
    }

    @Test
    public void test13_deplacerperso_gauche() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec la direction "gauche"
        l.deplacerPerso("gauche");
        assertEquals(2, l.getPersonnage().getX());
        assertEquals(1, l.getPersonnage().getY());
    }

    @Test
    public void test14_deplacerperso_droite() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec la direction "droite"
        l.deplacerPerso("droite");
        assertEquals(2, l.getPersonnage().getX());
        assertEquals(5, l.getPersonnage().getY());
    }

    @Test
    public void test15_deplacerperso_direction_invalide() {
        // Teste la méthode deplacerPerso avec une direction invalide
        assertThrows(ActionInconnueException.class, () -> l.deplacerPerso("milieu"));
    }

    @Test
    public void test16_deplacerperso_collision() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec une collision
        l.deplacerPerso("haut");
        assertEquals(1, l.getPersonnage().getX());
        assertEquals(3, l.getPersonnage().getY());

        // Sensé ne pas bouger
        l.deplacerPerso("haut");
        assertEquals(1, l.getPersonnage().getX());
        assertEquals(3, l.getPersonnage().getY());
    }

    @Test
    public void test17_deplacerperso_sortie() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec une sortie
        l.deplacerPerso("haut");
        l.deplacerPerso("gauche");
        assertEquals(l.getSortie().getX(), l.getPersonnage().getX());
        assertEquals(l.getSortie().getY(), l.getPersonnage().getY());
    }


    /**
     * --> Teste la méthode toString <--
     */

    @Test
    public void test18_tostring() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < l.getMurs().length; i++){
            for(int j = 0; j < l.getMurs()[0].length; j++){
                res.append(l.getChar(i, j));
            }
            res.append("\n");
        }

        // Teste la méthode toString
        assertEquals(res.toString(), l.toString());
    }

    /**
     * --> Teste la méthode etreFini <--
     */

    @Test
    public void test19_etrefini_non() {
        // Teste la méthode etreFini avec un labyrinthe non fini
        assertFalse(l.etreFini());
    }

    @Test
    public void test20_etrefini_oui() throws ActionInconnueException {
        // Teste la méthode etreFini avec un labyrinthe fini
        l.deplacerPerso("haut");
        l.deplacerPerso("gauche");
        assertTrue(l.etreFini());
    }

    /**
     * --> Teste la méthode chargerLabyrinthe <--
     */

    @Test
    public void test21_chargerlabyrinthe_fichier_incorrect() {
        // Teste la méthode chargerLabyrinthe avec un fichier incorrect
        assertThrows(FichierIncorrectException.class, () -> Labyrinthe.chargerLabyrinthe("laby/laby_incorrect.txt"));
    }

    @Test
    public void test22_chargerlabyrinthe_fichier_correct() throws FichierIncorrectException {
        // Teste la méthode chargerLabyrinthe avec un fichier correct
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
        assertNotNull(l);
    }

    @Test
    public void test23_chargerlabyrinthe_probleme_format() {
        // Teste la méthode chargerLabyrinthe avec un problème de format
        assertThrows(FichierIncorrectException.class, () -> Labyrinthe.chargerLabyrinthe("laby/laby_format_incorrect.txt"));
    }

    @Test
    public void test23_chargerlabyrinthe_deuxsorties() {
        // Teste la méthode chargerLabyrinthe avec deux sorties
        assertThrows(FichierIncorrectException.class, () -> Labyrinthe.chargerLabyrinthe("laby/laby_deuxSortie.txt"));
    }

    @Test
    public void test24_chargerlabyrinthe_pasdesortie() {
        // Teste la méthode chargerLabyrinthe sans sortie
        assertThrows(FichierIncorrectException.class, () -> Labyrinthe.chargerLabyrinthe("laby/laby_pasSortie.txt"));
    }

    @Test
    public void test25_chargerlabyrinthe_deuxpersonnages() {
        // Teste la méthode chargerLabyrinthe avec deux personnages
        assertThrows(FichierIncorrectException.class, () -> Labyrinthe.chargerLabyrinthe("laby/laby_deuxPersonnage.txt"));
    }

    @Test
    public void test26_chargerlabyrinthe_pasdepersonnage() {
        // Teste la méthode chargerLabyrinthe sans personnage
        assertThrows(FichierIncorrectException.class, () -> Labyrinthe.chargerLabyrinthe("laby/laby_pasPersonnage.txt"));
    }
}
