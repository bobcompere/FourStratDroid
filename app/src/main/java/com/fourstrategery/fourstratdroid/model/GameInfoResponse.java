package com.fourstrategery.fourstratdroid.model;

/**
 * Created by REC on 7/29/2015.
 */
public class GameInfoResponse {

    private boolean success;
    private String message;
    private GameStatusModel gameStatusModel;

    public GameStatusModel getGameStatusModel() {
        return gameStatusModel;
    }

    public void setGameStatusModel(GameStatusModel gameStatusModel) {
        this.gameStatusModel = gameStatusModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
