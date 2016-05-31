package npp.robot.services;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import npp.robot.core.Place;
import npp.robot.models.Cell;
import npp.robot.models.Unit;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mac on 29.05.16.
 */
public class DrawPlaceService {


    private static final Logger log = LoggerFactory.getLogger(DrawPlaceService.class);
    private static Place place = Place.getInstance();

    private static int cellWidth = 30;
    private static int cellHeight = 40;
    private static int margin = 10;


    public static void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

    public static void drawCells(GraphicsContext gc) {
        gc.setLineWidth(2);
        for (int i = 0; i < place.getCells().size(); i++) {
            Cell cell = place.getCells().get(i);
            gc.strokePolyline(
                    new double[] {
                        margin+((cellWidth + 2*margin)*cell.getCoord().getCoordX()),
                        margin+((cellWidth + 2*margin)*cell.getCoord().getCoordX()),
                        margin + cellWidth + ((cellWidth + 2*margin)*cell.getCoord().getCoordX()),
                        margin + cellWidth + ((cellWidth + 2*margin)*cell.getCoord().getCoordX())
                    },
                    new double[] {
                        margin+((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                        margin + cellHeight + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                        margin + cellHeight + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                        margin+((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                    }, 4);
            drawUnitsOfCell(gc, cell);
        }

    }

    public static void drawUnitsOfCell(GraphicsContext gc, Cell cell) {
        DrawPlaceService.drawUnitsOfCell(gc, cell,
                margin+((cellWidth + 2*margin)*cell.getCoord().getCoordX()),
                margin + cellHeight + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                1);
    }

    public static void drawUnitsOfCell(GraphicsContext gc, Cell cell, int cellLeftDownX, int cellLeftDownY, int flexCoeff) {
        for (int i = 0; i < GeneralProperties.cellColCount; i++) {
            int unitYPrev = cellLeftDownY;
            int unitUpLeftX = cellLeftDownX + (int)Math.round((cellWidth*flexCoeff/GeneralProperties.cellColCount)*i);
            for (int j = 0; j < cell.getUnits().get(i).size(); j++) {
                Unit unit = cell.getUnits().get(i).get(j);
                int unitUpLeftY = unitYPrev - (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.cellHeight);
                gc.setFill(randomColor(unit));
                gc.fillRect(
                    unitUpLeftX,
                    unitUpLeftY,
                    (int)Math.round((cellWidth*flexCoeff/GeneralProperties.cellColCount)),
                    (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.cellHeight)
                );
                unitYPrev -= (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.cellHeight);
            }
        }
    }

    public static void markSelectedCell(GraphicsContext gc, Cell cell) {
        gc.setLineWidth(2);
        gc.setStroke(Color.BLUE);
        gc.strokeRect(
                margin + ((cellWidth + 2*margin)*cell.getCoord().getCoordX()) - margin/2,
                margin + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()) - margin/2,
                margin + cellWidth,
                margin + cellHeight
        );
    }

    @NotNull
    public static Color randomColor(Unit unit) {
        double color = unit.getRadiation();
        if (unit.isBlocked()) {
            return Color.color(0.5,0.5,0.5);
        }
        color  = color > 1.0 ? 1.0 : (color < 0.0 ? 0.0 : color);

        return Color.color(color, 1 - color, 0.0);
    }
}
