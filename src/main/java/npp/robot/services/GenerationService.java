package npp.robot.services;

import npp.robot.core.Place;
import npp.robot.models.Cell;
import npp.robot.models.Coord;
import npp.robot.models.Unit;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.jboss.weld.util.collections.ArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by mac on 24.05.16.
 */
public class GenerationService {

    private static final Logger log = LoggerFactory.getLogger(GenerationService.class);
    private Place place = Place.getInstance();

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
        log.debug("{} : Full {}",cell.getRadiation(), cell.getFullness());
        log.debug("Cells");
        ArrayList<Double> lengthList = new ArrayList<>();
        for (int i = 0; i < GeneralProperties.cellColCount; i++) {
            lengthList.add(cell.getFullness());
        }
        log.debug("{}", lengthList);
        Random rnd = new Random();
//        while (cell.getRestFullness() > 0.5) {
        for (int i = 0; i < GeneralProperties.cellColCount; i++) {
            double max = lengthList.get(i) > 3.0 ? 3.0 : lengthList.get(i);
            double length = 0.5 + rnd.nextDouble() * (max - 0.5);
            while (cell.getRestFullness(i) > length) {
                lengthList.set(i, lengthList.get(i) - length);
                cell.addUnit(this.generateUnit(cell.getRadiation(), length, i), i);
                max = lengthList.get(i) > 2.5 ? 2.5 : lengthList.get(i);
                length = 0.5 + rnd.nextDouble() * max;
            }
        }
//        }
//        log.debug("{}", cell.getFullness()  );
//        for (int j = 0; j < cell.getUnits().size(); j++) {
//            for (int i = 0; i < cell.getUnits().get(j).size(); i++) {
//                log.debug("{}", cell.getUnits().get(j).get(i).print());
//            }
//        }
        for (int j = 0; j < GeneralProperties.cellColCount; j++) {
            log.debug("{}", cell.getColFullnessByUnits(j));
        }
        log.debug("\n");
        return cell;
    }

    private Unit generateUnit(double cellRadiation, double length, int col) {
        Random rnd = new Random();
//        double radiation = 0.01 + rnd.nextDouble()*0.99;
        double radiation = generateUnitRadiation(cellRadiation);

        Unit unit = new Unit();
        unit.setRadiation(radiation);
        unit.setLength(length);
        unit.setCol(col);
        unit.setBlocked(this.generateBlocked());
//        log.debug(unit.print());
        return unit;
    }

    private double generateRadiation() {
        Random rnd = new Random();
        return 0.01 + rnd.nextDouble()*0.99;

//        return 1 + rnd.nextDouble() * 4;
    }

    private double generateFullness(){
        Random rnd = new Random();
        return Math.floor(11 + rnd.nextDouble()*89)*GeneralProperties.cellHeight/100;
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
