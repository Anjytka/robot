package npp.robot.services;

import npp.robot.core.GeneralProperties;
import npp.robot.models.Cell;
import npp.robot.models.Container;
import npp.robot.models.Unit;

import java.util.*;

/**
 * Created by mac on 31.05.16.
 */
public class GroupingService extends BaseService {

    public class ResultData {
        public final boolean ended;

        public final int movesCount;

        public ResultData(boolean ended, int movesCount) {
            this.ended = ended;
            this.movesCount = movesCount;
        }
    }

    private static final int TYRE_PUT_UNIT_IN_CONTAINER = 1;
    private static final int TYRE_PUT_UNIT_BACK_IN_CELL = 2;
    private static final int TYRE_CHANGE_CONTAINER      = 3;
    private static final int TYPE_MOVE                  = 4;

    private int moveCount = 0;


    public ResultData startGrouping() {
        if (place.getCountCellsWithUnits() <= 0) {
            return new ResultData(true, moveCount);
        }
        if (place.getCurrentCell().isProcessed()) {
            log.debug("IS PROCESSED");
            this.chooseCell();
        } else {
            log.debug("WORK WITH CELL");
            this.workWithUnit();
        }

        return new ResultData(false, moveCount);
    }

    //region work with UNIT
    private void workWithUnit() {
        place.getCurrentCell().setProcessed(true);
        for (int i = 0; i < GeneralProperties.getInteger("cell.col.count"); i++) {
            ArrayList<Unit> unitsLeaved = new ArrayList<>();
            boolean blocked = false;
            for (int j = place.getCurrentCell().getUnits().get(i).size()-1; j >= 0; j--) {
                Unit unit = place.getCurrentCell().getUnits().get(i).get(j);
                if (unit.isBlocked() || blocked) {
                    if (place.getCurrentCell().isFirstlyProcessed()) {
                        place.getCurrentCell().addBlockedCount();
                    }
                    blocked = true;
                    unit.setBlocked(true);
                    unitsLeaved.add(unit);
                } else if (place.getCurrentContainer().hasSmallRestVolume()) {
                    unitsLeaved.add(unit);
                } else {
                        int res = this.makeDecisionForUnit(unit);
                        if (res == TYRE_PUT_UNIT_IN_CONTAINER) {
                            place.getCurrentContainer().addUnit(unit);
                            place.getCurrentContainer().changeRestFullness(unit.getRealVolume());
                        } else {
                            unitsLeaved.add(unit);
                        }
                    }
                }

                Collections.reverse(unitsLeaved);
                place.getCurrentCell().setColUnits(unitsLeaved, i);
                place.getCurrentCell().recalcRadiationByUnits();

        }
        place.getCurrentCell().setFirstlyProcessed(false);
    }

    /**
     * Выбор действия для элемента (опустить его обратно/ положить в контейнер)
     * @param unit
     * @return
     */
    private int makeDecisionForUnit(Unit unit) {
        double usefullCollect = calculateCollectUsefull(unit);log.debug("COLLECT {}\n",usefullCollect);
        double usefullPutBack = calculatePutBackUsefull(unit);log.debug("PUT BACK {}\n",usefullPutBack);
        if ((usefullCollect > usefullPutBack || Math.abs(usefullCollect - usefullPutBack) <= 0.2
                ) && usefullCollect != Double.MIN_VALUE) {
            log.info("UNIT IS COLLECTED");
            return TYRE_PUT_UNIT_IN_CONTAINER;
        }
        log.info("UNIT IS RETURNED");

        return TYRE_PUT_UNIT_BACK_IN_CELL;
    }

    /**
     * Вычисление полезности сбора элемента
     * @param unit
     * @return
     */
    private double calculateCollectUsefull(Unit unit) {
        Container container = place.getCurrentContainer();

        double startRad = container.getUnits().size() > 0 ? container.getRadiation() : place.getCurrentCell().getRadiation();
        double radiationDiff = Math.abs(startRad - unit.getRadiation());
        if (container.getRestFullness() < unit.getRealVolume()) {
                return Double.MIN_VALUE;
        }
        if (container.getRadiation() - unit.getRadiation() < place.getNormRadDiff()) {
            return Double.MAX_VALUE;

        }
        return 1/radiationDiff;
    }

    /**
     * Вычисление полезности опускания элемента в ячейку
     * @param unit
     * @return
     */
    private double calculatePutBackUsefull(Unit unit) {
        Container container = place.getCurrentContainer();
         if (container.getRestFullness() < unit.getRealVolume()) {
            return Double.MAX_VALUE;
        }
        if (place.getDangerBack() >= 1) {
            return Double.MIN_VALUE;
        }
        double startRad = container.getUnits().size() > 0 ? container.getRadiation() : place.getCurrentCell().getRadiation();
        double radiationDiff = Math.abs(startRad - unit.getRadiation());
        return radiationDiff/place.getDangerBack();
    }
    //endregion


    //region work with CELL
    private void chooseCell() {
        int res = makeDecisionForCell();
        if (res == TYRE_CHANGE_CONTAINER) {
            Container container = new Container();
            place.addContainer(container);
            place.refreshProcessed();
        } else {
            List<Cell> cells = place.getClosestCellsByRad();

            if (cells.size() == 0) {
                cells = place.getClosestCellsByRad(true);
            }
            if (cells.size() == 0) {
                Container container = new Container();
                place.addContainer(container);
                place.refreshProcessed();
            } else {
                moveCount += 1;
                place.setCurrentCell(cells.get(0));
            }
        }
    }
    /**
     * Выбор действия для элемента (опустить его обратно/ положить в контейнер)
     * @return
     */

    private int makeDecisionForCell() {
        double usefullChangeContainer = calculateChangeContainerUsefull();log.debug("CHANGE {}\n", usefullChangeContainer);
        double usefullMove            = calculateMoveUsefull();           log.debug("MOVE {}\n", usefullMove);
        if (usefullMove < usefullChangeContainer || place.getNotProcessedCellsCount() == 0) {
            log.info("CONTAINER IS CHANGED");
            return TYRE_CHANGE_CONTAINER;
        }
            log.info("ROBOT IS MOVED");
            return TYPE_MOVE;
    }

    /**
     * Вычисление полезности смены контейнера
     * @return
     */
    private double calculateChangeContainerUsefull() {
        Container container = place.getCurrentContainer();
        if (place.isMaxFilling()){
            if (!(container.getFullness() - container.getRestFullness() < place.getSuffFill() * container.getFullness())) {
                place.setNormRadDiff(place.getPermitDist() + 0.1);
                return Double.MIN_VALUE;
            }
        }
        return (container.getFullness() - container.getRestFullness())/ container.getFullness();
    }

    /**
     * Вычисление полезности перемещения
     * @return
     */
    private double calculateMoveUsefull() {
        Container container = place.getCurrentContainer();
        if (place.getCurrentCell().getAllUnitsCount() == 0) {
            return Double.MAX_VALUE;
        }
        List<Cell> cells = place.getClosestCellsByRad();
        if (cells.size() == 0) {
            return Double.MIN_VALUE;
        }
        int distance = cells.get(0).getCellDistance(place.getCurrentCell());
        return  (container.getRestFullness()/ container.getFullness()) / distance / place.getDangerMove();
    }

    //endregion
}
