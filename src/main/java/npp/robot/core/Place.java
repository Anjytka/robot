package npp.robot.core;

import npp.robot.models.Cell;
import npp.robot.models.Container;

import java.util.ArrayList;

/**
 * Created by mac on 24.05.16.
 */
public class Place {
    private static Place instance;

    private ArrayList<Cell> cells;

    private ArrayList<Container> containers;

    private Place () {
        this.cells = new ArrayList<>();
        this.containers = new ArrayList<>();
    }

    public static synchronized Place getInstance() {
        if (instance == null) {
            instance = new Place();
        }
        return instance;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    public void addContainer(Container container) {
        this.containers.add(container);
    }
}