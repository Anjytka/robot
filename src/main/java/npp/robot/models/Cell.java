package npp.robot.models;

import npp.robot.core.GeneralProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by mac on 24.05.16.
 */
public class Cell {

    public static final Logger log = LoggerFactory.getLogger(Cell.class);

    private double radiation = 0;

    private double fullness = 0;

    private boolean processed = false;

    private int blockedCount = 0;

    private Coord coord;

    private ArrayList<ArrayList<Unit>> units;

    private boolean firstlyProcessed;

    public Cell() {
        this.coord = new Coord();
        this.units = new ArrayList<>();
        IntStream.range(0, GeneralProperties.getInteger("cell.col.count")).forEach(
            i -> {
                this.units.add(new ArrayList<>());
            }
        );
        this.firstlyProcessed = true;
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

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

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

    public boolean isFirstlyProcessed() {
        return firstlyProcessed;
    }

    public void setFirstlyProcessed(boolean firstlyProcessed) {
        this.firstlyProcessed = firstlyProcessed;
    }

    // region Additional methods
    public void addBlockedCount() {
        this.addBlockedCount(1);
    }

    public void addBlockedCount(int count) {
        this.blockedCount += count;
    }

    public void setColUnits(ArrayList<Unit> units, int colId) {
        this.getUnits().set(colId, units);
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

    public double getRestFullness(int col) {
        return this.fullness - getColFullnessByUnits(col);
    }

    public int getCellDistance(Cell cell) {
        return Math.abs(this.getCoord().getCoordX() - cell.getCoord().getCoordX()) +
                Math.abs(this.getCoord().getCoordY() - cell.getCoord().getCoordY());
    }

    public int getAllUnitsCount() {
        int res = 0;
        for (int i = 0; i < GeneralProperties.getInteger("cell.col.count"); i++) {
            res += this.getUnits().get(i).size();
        }
        log.debug("{}", print());
        return res-blockedCount;
    }

    public void recalcRadiationByUnits() {
        double radiation = 0;
        int unitCoint = 0;
        for (ArrayList<Unit> colUnits: this.units) {
            unitCoint += colUnits.size();
            for (Unit unit : colUnits) {
                radiation += unit.getRadiation();
            }
        }
        if (unitCoint == 0) {
            this.radiation = 0;
        } else {
            this.radiation = radiation/unitCoint;
        }
    }

    public String print() {
        String str = "\nRad: "+this.getRadiation() + "\n"+
                "Blocked: " + this.blockedCount +"\n"+
                "Coord: ("+this.getCoord().getCoordX() + ","+ this.getCoord().getCoordY() +")\n";
        return str;
    }
    // endregion
}
