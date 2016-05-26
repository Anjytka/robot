package npp.robot.models;

import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;

import java.util.ArrayList;

/**
 * Created by mac on 24.05.16.
 */
public class Cell {

    private double radiation;

    private double fullness;

    private boolean opened;

    private double height;

    private double width;

    private Coord coord;

    private ArrayList<Unit> units;

    private int unitCount;

    public Cell() {
        this.radiation = 0;
        this.fullness = 0;
        this.opened = false;
        this.height = 9.0;
        this.width = 0.2;
        this.coord = new Coord();
        this.units = new ArrayList<>();
        this.unitCount = 0;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public double getFullness() {
        return fullness;
    }

    public void setFullness(double fullness) {
        this.fullness = fullness;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getCoord() {
        return coord.getCoordX()+" "+coord.getCoordY();
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public double getFullnessByUnits() {
        double fullness = 0;
        for (Unit unit: units) {
            fullness += unit.getVolume();
        }

        return fullness;
    }
}
