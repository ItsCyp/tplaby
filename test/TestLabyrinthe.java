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
        assertEquals(2, l.getPersonnage().getY());
    }

    @Test
    public void test14_deplacerperso_droite() throws ActionInconnueException {
        // Teste la méthode deplacerPerso avec la direction "droite"
        l.deplacerPerso("droite");
        assertEquals(2, l.getPersonnage().getX());
        assertEquals(4, l.getPersonnage().getY());
    }

    @Test
    public void test15_deplacerperso_direction_invalide() {
        // Teste la méthode deplacerPerso avec une direction invalide
        assertThrows(ActionInconnueException.class, () -> l.deplacerPerso("milieu"));
    }
}
