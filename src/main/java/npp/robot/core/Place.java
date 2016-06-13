package npp.robot.core;

import npp.robot.models.Cell;
import npp.robot.models.Container;
import npp.robot.models.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mac on 24.05.16.
 */
public class Place {
    private static Place instance;

    private ArrayList<Cell> cells;

    private ArrayList<ArrayList<Integer>> cellsIndexCoord;

    private ArrayList<Container> containers;

    private Cell currentCell;

    private Container currentContainer;

    private int xMaxCellCount = 0;

    private int yMaxCellCount = 0;

    private double suffFill = 0;

    private double normRadDiff = 0;

    private double dangerBack = 0;

    private double dangerMove = 0;

    private int permitDist = 0;

    private boolean maxFilling = false;

    private Place () {
        this.cells = new ArrayList<>();
        this.cellsIndexCoord = new ArrayList<>();
        this.containers = new ArrayList<>();
        this.currentCell = null;
        this.currentContainer = new Container();
        this.containers.add(this.currentContainer);
    }

    public static synchronized Place getInstance() {
        if (instance == null) {
            instance = new Place();
        }
        return instance;
    }

    public int getxMaxCellCount() {
        return xMaxCellCount;
    }

    public void setxMaxCellCount(int xMaxCellCount) {
        this.xMaxCellCount = xMaxCellCount;
        for (int i = 0; i < xMaxCellCount; i++){
            cellsIndexCoord.add(i, new ArrayList<>());
        }
    }

    public int getyMaxCellCount() {
        return yMaxCellCount;
    }

    public void setyMaxCellCount(int yMaxCellCount) {
        this.yMaxCellCount = yMaxCellCount;
        for (int i = 0; i < xMaxCellCount; i++) {
            for (int j = 0; j < yMaxCellCount; j++) {
                cellsIndexCoord.get(i).add(j, 0);
            }
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
        this.cellsIndexCoord.get(cell.getCoord().getCoordX()).set(cell.getCoord().getCoordY(), this.cells.size()-1);
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    public void addContainer(Container container) {
        this.containers.add(container);
        this.currentContainer = container;
    }

    public static void setInstance(Place instance) {
        Place.instance = instance;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Container getCurrentContainer() {
        return currentContainer;
    }

    public void setCurrentContainer(Container currentContainer) {
        this.currentContainer = currentContainer;
    }

    public double getSuffFill() {
        return suffFill;
    }

    public void setSuffFill(double suffFill) {
        this.suffFill = suffFill;
    }

    public double getNormRadDiff() {
        return normRadDiff;
    }

    public void setNormRadDiff(double normRadDiff) {
        this.normRadDiff = normRadDiff;
    }

    public double getDangerBack() {
        return dangerBack;
    }

    public void setDangerBack(double dangerBack) {
        this.dangerBack = dangerBack;
    }

    public double getDangerMove() {
        return dangerMove;
    }

    public void setDangerMove(double dangerMove) {
        this.dangerMove = dangerMove;
    }

    public int getPermitDist() {
        return permitDist;
    }

    public void setPermitDist(int permitDist) {
        this.permitDist = permitDist;
    }

    public boolean isMaxFilling() {
        return maxFilling;
    }

    public void setMaxFilling(boolean maxFilling) {
        this.maxFilling = maxFilling;
    }

    //region Additional methods
    public List<Cell> getClosestCellsByRad () {
        return this.getClosestCellsByRad (false);
    }

    public List<Cell> getClosestCellsByRad (boolean needAny) {
        ArrayList<Cell> cellsNew = new ArrayList<>(this.cells);
        return cellsNew.stream()
                .filter(c -> ((c.getCellDistance(this.getCurrentCell()) <= this.getPermitDist() || needAny)
                        && c.getAllUnitsCount() > 0
                        && !this.getCurrentCell().equals(c)
                        && !c.isProcessed()
                ))
                .sorted((o1, o2) ->
                        Double.compare(
                                o2.getRadiation() - this.getCurrentCell().getRadiation(),
                                o1.getRadiation() - this.getCurrentCell().getRadiation()
                        )
                )
                .collect(Collectors.toList());
    }

    public void refreshProcessed() {
        for (Cell cell : this.cells) {
            cell.setProcessed(false);
        }
    }

    public int getCountCellsWithUnits() {
        int count = 0;
        for (Cell cell : this.cells) {
            if (cell.getAllUnitsCount() > 0) {
                count++;
            }
        }
        return count;
    }

    public int getNotProcessedCellsCount() {
        int count = 0;
        for (Cell cell : this.cells) {
            if (!cell.isProcessed()) {
                count++;
            }
        }
        return count;
    }
    //endregion


}