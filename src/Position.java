public class Position {
    private int x;
    private int y;

    /**
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter pour la position x
     * @return la coordonnee x
     */
    public int getX() {
        return x;
    }

    /**
     * getter pour la position y
     * @return la coordonnee y
     */
    public int getY() {
        return y;
    }

    /**
     * setter pour la position x
     * @param x la coordonnee x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter pour la position y
     * @param y la coordonnee y
     */
    public void setY(int y) {
        this.y = y;
    }
}
