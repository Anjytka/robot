package npp.robot.models;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by mac on 25.05.16.
 */
public class Unit {

    private double radiation;

    private double length;

    private double width;

    private boolean blocked;

    private boolean packed;

    private int col;

    public Unit() {
        this.radiation = 0;
        this.length = 0;
        this.width = 1;
        this.blocked = false;
        this.packed = false;
        this.col = 0;
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

    public boolean isPacked() {
        return packed;
    }

    public void setPacked(boolean packed) {
        this.packed = packed;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getVolume() {
        return this.length * this.width;
    }

    public String print() {
        String str = "\nRad: "+this.getRadiation() + "\n"+
        "Len: "+this.getLength() + "\n"+
        "Col: "+this.getCol() + "\n"+
        "Blocked: "+this.isBlocked() + "\n";
        return str;
    }
}
