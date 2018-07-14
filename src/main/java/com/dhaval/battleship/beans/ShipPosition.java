package com.dhaval.battleship.beans;

public class ShipPosition {

    private String shipId;
    private int startColumn;
    private int startRow;
    private Direction direction;
    private int length;

    public ShipPosition() {
    }

    public ShipPosition(String shipId, int startColumn, int startRow, Direction direction, int length) {
        this.shipId = shipId;
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.direction = direction;
        this.length = length;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
