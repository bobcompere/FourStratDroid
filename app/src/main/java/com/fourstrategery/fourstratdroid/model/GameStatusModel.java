package com.fourstrategery.fourstratdroid.model;

import java.util.List;

/**
 * Created by REC on 7/29/2015.
 */
public class GameStatusModel {

    private GameModel game;
    private List<Player> players;
    private List<PlayerDetail> playerDetails;

    public List<GameActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<GameActivity> activities) {
        this.activities = activities;
    }

    private List<GameActivity> activities;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<PlayerDetail> getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(List<PlayerDetail> playerDetails) {
        this.playerDetails = playerDetails;
    }

    public GameModel getGame() {
        return game;
    }

    public void setGame(GameModel game) {
        this.game = game;
    }

}

