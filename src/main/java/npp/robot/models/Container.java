package npp.robot.models;


import npp.robot.core.GeneralProperties;

import java.util.ArrayList;

/**
 * Created by mac on 31.05.16.
 */
public class Container {

    private double width = 0.8;

    private double height = 0.8;

    private double restFullness;

    private double radiation = 0;

    private ArrayList<Unit> units;

    public Container() {
        this.units = new ArrayList<>();
        this.restFullness = this.width * this.width * this.height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        this.setRadiation((this.getRadiation()*this.getUnits().size() + unit.getRadiation())/(this.getUnits().size()+1));
        this.units.add(unit);
    }

    public double getRestFullness() {
        return restFullness;
    }

    public void setRestFullness(double restFullness) {
        this.restFullness = restFullness;
    }

    //region Additional methods
    public void changeRestFullness(double unitVolume){
        this.restFullness = this.restFullness - unitVolume;
    }

    public double getFullness() {
        return this.width * this.width * this.height;
    }

    public boolean hasSmallRestVolume() {
        return this.restFullness < GeneralProperties.getDouble("volume.eps");
    }

    public String print() {
        String str = "\nRad: " + this.getRadiation() + "\n" +
                "RestFull: " + this.getRestFullness();
        return str;
    }

    public long getRestFullnessInPercents() {
        return Math.round(this.getRestFullness()/this.getFullness()*100);
    }
    //endregion
}
