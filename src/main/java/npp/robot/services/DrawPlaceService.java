package npp.robot.services;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import npp.robot.core.GeneralProperties;
import npp.robot.models.Cell;
import npp.robot.models.Unit;
import org.jetbrains.annotations.NotNull;

/**
 * Created by mac on 29.05.16.
 */
public class DrawPlaceService extends BaseService {

    private static int cellWidth = 30;
    private static int cellHeight = 40;
    private static int margin = 10;

    public static void drawCells(GraphicsContext gc) {
        for (int i = 0; i < place.getCells().size(); i++) {
            gc.setLineWidth(2);
            gc.setStroke(Color.BLACK);
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
            Font font = new Font("TimesRoman", 10);
            gc.setLineWidth(0.5);
            gc.setFont(font);
            gc.setTextBaseline(VPos.CENTER);
            gc.strokeText(
                    String.format("%.2f", cell.getRadiation()),
                    1.5*margin + ((cellWidth + (2 * margin)) * cell.getCoord().getCoordX()),
                    2*margin + cellHeight + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                    cellWidth);

            drawUnitsOfCell(gc, cell);
        }

    }

    public static void drawUnitsOfCell(GraphicsContext gc, Cell cell) {
        DrawPlaceService.drawUnitsOfCell(gc, cell,
                margin+((cellWidth + 2*margin)*cell.getCoord().getCoordX()),
                margin + cellHeight + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()),
                1, false);
    }

    public static void drawUnitsOfCell(GraphicsContext gc, Cell cell, int cellLeftDownX, int cellLeftDownY, int flexCoeff, boolean needBorder) {
        for (int i = 0; i < GeneralProperties.getInteger("cell.col.count"); i++) {
            int unitYPrev = cellLeftDownY;
            int unitUpLeftX = cellLeftDownX + (int)Math.round((cellWidth*flexCoeff/GeneralProperties.getInteger("cell.col.count"))*i);
            for (int j = 0; j < cell.getUnits().get(i).size(); j++) {
                Unit unit = cell.getUnits().get(i).get(j);
                int unitUpLeftY = unitYPrev - (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.getDouble("cell.height"));
                gc.setFill(randomColor(unit));
                gc.fillRect(
                    unitUpLeftX,
                    unitUpLeftY,
                    (int)Math.round((cellWidth*flexCoeff/GeneralProperties.getInteger("cell.col.count"))),
                    (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.getDouble("cell.height"))
                );
                if (needBorder) {
                    gc.setLineWidth(1);
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(
                            unitUpLeftX,
                            unitUpLeftY,
                            (int)Math.round((cellWidth*flexCoeff/GeneralProperties.getInteger("cell.col.count"))),
                            (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.getDouble("cell.height"))
                    );
                    Font font = new Font("TimesRoman", 10);
                    gc.setLineWidth(0.5);
                    gc.setFont(font);
                    gc.setStroke(calcTextColor(unit));
                    gc.setTextBaseline(VPos.TOP);
                    gc.strokeText(
                            String.format("%.2f", unit.getRadiation()),
                            unitUpLeftX + (int)Math.round((cellWidth*flexCoeff/GeneralProperties.getInteger("cell.col.count")))/2 - margin,
                            unitUpLeftY + (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.getDouble("cell.height"))/2 - margin/2,
                            (int)Math.round((cellWidth*flexCoeff/GeneralProperties.getInteger("cell.col.count"))));

                }
                unitYPrev -= (int)Math.round(unit.getLength()*cellHeight*flexCoeff/GeneralProperties.getDouble("cell.height"));
            }
        }
    }

    public static void markSelectedCell(GraphicsContext gc, Cell cell) {
        gc.setLineWidth(1);
        gc.setStroke(Color.BLUE);
        // in y +2 & in h -4 for marking cell without intersections with borders and text
        gc.strokeRect(
                margin + ((cellWidth + 2*margin)*cell.getCoord().getCoordX()) - margin/2,
                margin + ((cellHeight + 2*margin)*cell.getCoord().getCoordY()) - margin/2 + 2,
                margin + cellWidth,
                margin + cellHeight - 4
        );
    }

    @NotNull
    public static Color randomColor(Unit unit) {
        double color = unit.getRadiation();
        if (unit.isBlocked()) {
            return Color.color(0.5,0.5,0.5);
        }
        color  = color > 1.0 ? 1.0 : (color < 0.0 ? 0.0 : color);

        return Color.color(color, 0.0, 0.0);
    }

    public static Color calcTextColor(Unit unit) {
        double color = unit.getRadiation();
        color  = color > 1.0 ? 1.0 : (color < 0.0 ? 0.0 : color);
        if (unit.isBlocked() || color <= 0.5) {
            return Color.color(1.0,1.0,1.0);
        }

        return Color.color(0.0, 0.0, 0.0);
    }
}
