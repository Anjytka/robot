package npp.robot.models;


import java.util.ArrayList;

/**
 * Created by mac on 31.05.16.
 */
public class Container {

    private double width = 0.8;

    private double height = 0.8;

    private ArrayList<Unit> units;

    public Container() {
        this.units = new ArrayList<>();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }
}
