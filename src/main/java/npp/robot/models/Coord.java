package npp.robot.models;

/**
 * Created by mac on 24.05.16.
 */
public class Coord {

    private int coordX;

    private int coordY;

    public Coord() {
        this.coordX = 0;
        this.coordY = 0;
    }

    public Coord(int x,  int y) {
        this.coordX = x;
        this.coordY = y;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
}
