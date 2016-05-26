package npp.robot.services;

import npp.robot.core.Place;
import npp.robot.models.Cell;
import npp.robot.models.Coord;
import npp.robot.models.Unit;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        log.debug("{} : {}",cell.getRadiation(), cell.getFullness());

//        while (cell.getFullness() > cell.getFullnessByUnits()) {
//            cell.addUnit(this.generateUnit());
//        }
        return cell;
    }

    private double generateUnit() {
        Unit unit = new Unit();

    }

    private double generateRadiation() {
        Random rnd = new Random();
        return rnd.nextDouble();
    }

    private double generateFullness(){
        Random rnd = new Random();
        return rnd.nextDouble();
//        NormalDistribution dstr = new NormalDistribution(0.5, 0.5);
//        double sample = dstr.sample();
//        return sample < 0 ? 0 : sample;
    }
}
