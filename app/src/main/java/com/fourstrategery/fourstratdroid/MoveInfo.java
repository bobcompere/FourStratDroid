package com.fourstrategery.fourstratdroid;

/**
 * Created by REC on 8/22/2015.
 */
public class MoveInfo {

    String venueId;
    int unitId;
    String text;
    int gameId;

    public MoveInfo(String text,int unitId,String venueId, int gameId) {
        this.text = text;
        this.unitId = unitId;
        this.venueId = venueId;
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
