package npp.robot.services;

import npp.robot.core.GeneralProperties;
import npp.robot.core.Place;
import npp.robot.models.Cell;
import npp.robot.models.Coord;
import npp.robot.models.Unit;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mac on 24.05.16.
 */
public class GenerationService extends BaseService {

    public void dataGenerator(int xCellCount, int yCellCount) {
        for (int i = 0; i < xCellCount; i++) {
            for (int j = 0; j < yCellCount; j++) {
                place.addCell(this.generateCell(i, j));
            }
        }
    }

    private Cell generateCell(int x, int y) {
        Cell cell = new Cell();
        cell.setRadiation(this.generateRadiation());
        cell.setFullness(this.generateFullness());

        cell.setCoord(new Coord(x,y));
        ArrayList<Double> lengthList = new ArrayList<>();
        for (int i = 0; i < GeneralProperties.getInteger("cell.col.count"); i++) {
            lengthList.add(cell.getFullness());
        }
        Random rnd = new Random();
        for (int i = 0; i < GeneralProperties.getInteger("cell.col.count"); i++) {
            double max = lengthList.get(i) > 3.0 ? 3.0 : lengthList.get(i);
            double length = 0.5 + rnd.nextDouble() * (max - 0.5);
            while (cell.getRestFullness(i) > length) {
                lengthList.set(i, lengthList.get(i) - length);
                cell.addUnit(this.generateUnit(cell.getRadiation(), length, i), i);
                max = lengthList.get(i) > 2.5 ? 2.5 : lengthList.get(i);
                length = 0.5 + rnd.nextDouble() * max;
            }
        }
        cell.recalcRadiationByUnits();
        return cell;
    }

    private Unit generateUnit(double cellRadiation, double length, int col) {
        double radiation = generateUnitRadiation(cellRadiation);

        Unit unit = new Unit();
        unit.setRadiation(radiation);
        unit.setLength(length);
        unit.setCol(col);
        unit.setBlocked(this.generateBlocked());
        return unit;
    }

    private double generateRadiation() {
        Random rnd = new Random();
        return 0.01 + rnd.nextDouble()*0.99;
    }

    private double generateFullness(){
        Random rnd = new Random();
        return Math.floor(11 + rnd.nextDouble()*89)*GeneralProperties.getDouble("cell.height")/100;
    }

    private double generateUnitRadiation(double cellRadiation) {
        NormalDistribution dstr = new NormalDistribution(cellRadiation, 0.5);
        double sample = dstr.sample();
        if (sample < 0) {
            return this.generateUnitRadiation(cellRadiation);
        }
        return sample;
    }

    private boolean generateBlocked() {
        Random rnd = new Random();
        return rnd.nextInt(100) < 5 ? true : false;
    }
}
