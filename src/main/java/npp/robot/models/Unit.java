package npp.robot.models;

/**
 * Created by mac on 25.05.16.
 */
public class Unit {

    private double radiation;

    private double length;

    private double diameter;

    private boolean blocked;

    private int col;

    public Unit() {
        this.radiation = 0;
        this.length = 0;
        this.diameter = 0.05;
        this.blocked = false;
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

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    //region Additional methods
    public double getVolume() {
        return this.length;
    }

    public double getRealVolume() {
        return this.getReadAreaBottom() * this.length;
    }

    public double getReadAreaBottom() {
        return Math.PI*Math.pow(this.diameter, 2)/4;
    }

    public String print() {
        String str = "\nRad: "+this.getRadiation() + "\n"+
        "Vol: "+this.getRealVolume() + "\n"+
        "Col: "+this.getCol() + "\n"+
        "Blocked: "+this.isBlocked();
        return str;
    }
    //endregion


}
