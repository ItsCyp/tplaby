import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * classe de test fournie destinee a verifier les methodes
 */
public class TestLabyProf {

    private Labyrinthe l;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialise le labyrinthe avant chaque test
        l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
    }

    /**
     * test de chargement + getChar + Constantes
     * (NE PAS MODIFIER)
      */
    @Test
    public void test_charger() throws Exception{
        // verifie labyrinthe
        assertEquals(l.getChar(0,0),Labyrinthe.MUR);
        assertEquals(l.getChar(1,1),Labyrinthe.SORTIE);
        assertEquals(l.getChar(2,3),Labyrinthe.PJ);
        assertEquals(l.getChar(2,1),Labyrinthe.VIDE);
    }

    /**
     * test des methodes publiques
     * (NE PAS MODIFIER)
      */
    @Test
    public void test_methodes() throws Exception{

        // getsuivant et constantes action
        int[] res;
        res = Labyrinthe.getSuivant(1,1,Labyrinthe.HAUT);
        res = Labyrinthe.getSuivant(1,1,Labyrinthe.BAS);
        res = Labyrinthe.getSuivant(1,1,Labyrinthe.DROITE);
        res = Labyrinthe.getSuivant(1,1,Labyrinthe.GAUCHE);

        // deplacerPerso
        l.deplacerPerso(Labyrinthe.HAUT);

        // etrefini
        boolean b = l.etreFini();

        // toString
        String s = l.toString();
    }

    @Test
    public void testDeplacementValid() throws Exception {
        // Tester un déplacement valide qui ne devrait pas entraîner de collision
        // Mettez à jour les coordonnées en fonction de la structure de votre labyrinthe spécifique
        l.deplacerPerso(Labyrinthe.GAUCHE);
        assertEquals(Labyrinthe.PJ, l.getChar(2, 2));
    }

    @Test
    public void testCollisionMur() throws Exception {
        // Tester une tentative de déplacement vers un mur
        // Mettez à jour les coordonnées et les déplacements en fonction de la structure de votre labyrinthe spécifique
        l.deplacerPerso(Labyrinthe.DROITE); // Supposé être un mouvement vers un mur
        assertNotEquals(Labyrinthe.MUR, l.getChar(2, 3));
    }

    @Test
    public void testHorsLimites() {
        // Tester les déplacements hors limites
        Exception exception = assertThrows(ActionInconnueException.class, () -> l.deplacerPerso("une_direction_invalide"));
        String messageAttendu = "Action inconnue";
        String messageObtenu = exception.getMessage();
        assertTrue(messageObtenu.contains(messageAttendu));
    }

    @Test
    public void testFinJeu() throws Exception {
        // Préparez le labyrinthe pour que le personnage soit sur la case de sortie
        // Et vérifiez si le jeu reconnaît correctement cette condition comme étant la fin
        // Vous aurez besoin de manipuler les coordonnées ici
        boolean fin = l.etreFini();
        assertTrue(fin); // Assurez-vous que le jeu identifie correctement que c'est la fin
    }

}
