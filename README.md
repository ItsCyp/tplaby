### Nom, Prénom :

- **Chailan Cyprian**
- **Thiriet Esteban**

## Difficulté :

Nous pensons que les seules difficultés que nous avons rencontrées seraient le fait de bien comprendre le sujet et de
bien comprendre les règles du jeu sinon il n'y a pas eu trop de difficulté à part peut-être sur la compréhension de
comment écrire la méthode `chargerLabyrinthe()`.

Le seul petit problème que nous avons eu est pour les constantes de type `char` et `String` où, au départ, nous
n'avions pas bien compris qu'elles devaient être `static final` et non pas `final` tout court. Nous l'avons découvert
quand nous devions vérifier les actions (`HAUT`, `BAS`, `GAUCHE`, `DROITE`) dans la méthode `getSuivant()` en utilisant
un `switch case`, mais notre environnement de développement (IntelliJ IDEA) nous affichait une erreur sur les `case`
et nous avons dû aller chercher dans la documentation pour corriger l'erreur.

## Choix de programmation :

Nous avons choisi, en quelque sorte, de séparer la programmation en deux parties : une partie pour le développement du
labyrinthe et une autre pour les tests des méthodes. Nous avons donc complété la classe `Labyrinthe` en premier lieu en
créant les classes utiles pour le développement du labyrinthe (telles que `Personnage`, `Sortie`, `Position`).

Ensuite, nous avons créé la classe `MainLaby` qui est la classe principale où tout se passe : on charge un labyrinthe et
on lance le jeu tout en utilisant les méthodes créées dans `Labyrinthe`.

Enfin, nous avons créé la classe `TestLabyrinthe` dans le répertoire `test/` pour tester toutes les méthodes de la
classe `Labyrinthe` (mais nous y reviendrons plus tard).

## Comment lancer l'application ?

Pour le lancement de l'application, il suffit de lancer la classe `MainLaby` en ajoutant un argument qui représente le
fichier labyrinthe à charger (au format `.txt`). Lorsque le fichier est chargé (*un `X` représente un MUR, un `.`
représente un VIDE, `S` la sortie, et `P` le personnage*) via la méthode `chargerLabyrinthe()`, le labyrinthe est
affiché et le jeu commence. Par exemple, pour charger le fichier `laby0.txt` qui contient la longueur et la largeur du
labyrinthe, la forme du labyrinthe (avec la position du personnage et de la sortie)

**Contenu du fichier `laby0.txt` :**

```text
5
7
XXXXXXX
XS....X
X..P..X
X.....X
XXXXXXX
```

Donc lorsque nous exécutons le programme, cela va charger le fichier `laby1.txt` et le labyrinthe aura donc 5 lignes et
7 colonnes, le personnage sera à la position (2, 3) et la sortie à la position (1, 1).

**Autre exemple avec le fichier `laby2.txt` :**

```text
14
20
XXXXXXXXXXXXXXXXXXXX
XX.......X........XX
X........X.........X
X........X...X.....X
X..X.....X.......SXX
X......XXX.........X
X...X....X.........X
X..X..........X....X
X.....X..X.........X
XX.......XX........X
X........X.........X
X........X.........X
XX...............PXX
XXXXXXXXXXXXXXXXXXXX
```

Ici le labyrinthe aura 14 lignes et 20 colonnes, le personnage sera à la position (13, 18) et la sortie à la position (5, 18).

## Présentation des couverture de test + explications :

Comme nous le disons plus haut, nous avons créé la classe `TestLabyrinthe` dans le répertoire `test/` pour tester toutes
les méthodes. Nous avons donc testé toutes les méthodes de la classe `Labyrinthe`. Nous utilisons un `@BeforeEach` pour
initialiser le labyrinthe avant chaque test. Nous avons aussi ajouté quelques méthodes dans la classe `Labyrinthe` pour
que ce soit plus facile de tester les méthodes. Nous avons donc ajouté les méthodes suivantes :

- `getPersonnage()` : retourne la position du personnage
- `getSortie()` : retourne la position de la sortie
- `getMurs()` : retourne la liste 2D des murs

Nous avons donc testé les méthodes suivantes :

`getChar()` :
L'objectif de cette méthode est de retourner le caractère à la position donnée en paramètre. Nous avons donc testé
cette méthode en vérifiant que le caractère retourné est bien celui attendu. Nous avons également test si on mettait
une position hors limite ce qui renvoie une exception de type `ArrayIndexOutOfBoundsException`.



`getSuivant()` :
Cette méthode a pour but de retourner la position suivante du personnage en fonction de l'action donnée en paramètre (
`HAUT`, `BAS`, `DROITE`, `GAUCHE`). Nous avons testé cette méthode en vérifiant que la position retournée est bien
celle attendue. Avec ce test nous avons remarqué que nous avions oublié de mettre une valeur par défaut si l'action n'est
pas reconnue. Donc nous avons rajouté un `default:` au switch case qui retourne une position illogique (-9999,-9999) et
nous utiliserons cette valeur pour vérifier le déplacement du personnage.



`deplacerPerso()` :
L'objectif de cette méthode est de déplacer le personnage en fonction de l'action donnée en paramètre (`HAUT`, `BAS`,
`DROITE`, `GAUCHE`). Nous avons testé cette méthode en vérifiant que le personnage est bien déplacé à la position
attendue. Nous avons également testé si le personnage ne peut pas se déplacer dans un mur et si le personnage ne peut pas sortir
du labyrinthe. Et comme nous en avons parlé juste avant, nous devions aussi vérifier si l'action était incorrecte (
donc en vérifiant si la position est (-9999,-9999)) ce qui renvoie l'exception `ActionInconnueException` (que nous avons créée).



`toString()` :
Ici, nous devions tout simplement vérifier que le labyrinthe était bien affiché comme voulu.



`etreFini()` :
Pour cette méthode il fallait juste vérifier si le personnage était à la position de la sortie. Nous avons donc testé
cette méthode en vérifiant que si le personnage était sur la position de sortie cela renvoyait `True` sinon `False`.



`chargerLabyrinthe()` :
Et pour finir, la méthode la plus importante, le chargement du labyrinthe. Nous devions vérifier que le labyrinthe
était bien chargé en vérifiant les exceptions prévues (fichier introuvable, fichier mal formé, 0 ou plusieurs
personnages, 0 ou plusieurs sorties, etc...) et bien sûr en vérifiant que le labyrinthe a bien été chargé.
