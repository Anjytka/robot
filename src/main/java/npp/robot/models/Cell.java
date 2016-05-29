package npp.robot.models;

import npp.robot.services.GeneralProperties;
import org.apache.commons.lang.math.Range;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by mac on 24.05.16.
 */
public class Cell {

    private double radiation;

    private double fullness;

    private boolean opened;

    private Coord coord;

    private ArrayList<ArrayList<Unit>> units;

    public Cell() {
        this.radiation = 0;
        this.fullness = 0;
        this.opened = false;
        this.coord = new Coord();
        this.units = new ArrayList<>();
        IntStream.range(0, GeneralProperties.cellColCount).forEach(
            i -> {
                this.units.add(new ArrayList<>());
            }
        );
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
//
//    public double getHeight() {
//        return height;
//    }
//
//    public void setHeight(double height) {
//        this.height = height;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public void setWidth(double width) {
//        this.width = width;
//    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public ArrayList<ArrayList<Unit>> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<ArrayList<Unit>> units) {
        this.units = units;
    }

    public void addUnit(Unit unit, int colId) {
        this.units.get(colId).add(unit);
    }



    public double getColFullnessByUnits(int colId) {
        double fullness = 0;
        for (Unit unit: this.units.get(colId)) {
            fullness += unit.getVolume();
        }

        return fullness;
    }

    public double getAllFullnessByUnits() {
        double fullness = 0;
        for (int col = 0; col < GeneralProperties.cellColCount-1; col++) {
            for (Unit unit: this.units.get(col)) {
                fullness += unit.getVolume();
            }
        }
        return fullness;
    }

    public double getRestFullness(int col) {
        return this.fullness - getColFullnessByUnits(col);
    }
}
