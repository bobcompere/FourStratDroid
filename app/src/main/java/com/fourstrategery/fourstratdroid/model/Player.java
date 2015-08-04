package com.fourstrategery.fourstratdroid.model;

/**
 * Created by REC on 8/1/2015.
 */
public class Player {
    int playerNumber;
    int playerId;
    int score;
    int activeUnitCount;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getActiveUnitCount() {
        return activeUnitCount;
    }

    public void setActiveUnitCount(int activeUnitCount) {
        this.activeUnitCount = activeUnitCount;
    }
}
