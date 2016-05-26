package npp.robot.models;

import java.util.ArrayList;

/**
 * Created by mac on 25.05.16.
 */
public class Unit {

    private double radiation;

    private double length;

    private double width;

    private boolean blocked;

    private Coord coord;

    private boolean packed;

    public Unit() {
        this.radiation = 0;
        this.length = 0;
        this.width = 0.05;
        this.blocked = false;
        this.coord = new Coord();
        this.packed = false;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public boolean isPacked() {
        return packed;
    }

    public void setPacked(boolean packed) {
        this.packed = packed;
    }

    public double getVolume() {
        return this.length * this.width;
    }
}
