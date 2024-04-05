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
     * @return la coordonnee x
     */
    public int getX() {
        return x;
    }

    /**
     * @return la coordonnee y
     */
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
