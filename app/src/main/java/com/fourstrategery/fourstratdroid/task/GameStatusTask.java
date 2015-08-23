package com.fourstrategery.fourstratdroid.task;

import android.content.Context;

import com.fourstrategery.fourstratdroid.GameListFragment;
import com.fourstrategery.fourstratdroid.GameStatusActivity;
import com.fourstrategery.fourstratdroid.MoveInfo;
import com.fourstrategery.fourstratdroid.model.GameInfoResponse;
import com.fourstrategery.fourstratdroid.model.GameStatusModel;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by REC on 7/29/2015.
 */
public class GameStatusTask extends FourStratAuthorizedTask {

    private int gameId;
    private GameStatusActivity owner;
    private MoveInfo moveInfo;

    public GameStatusTask(Context ctx, GameStatusActivity own, int gameId) {
        this(ctx,own, gameId, null);
    }

    public GameStatusTask(Context ctx, GameStatusActivity own, int gameId, MoveInfo mi) {
        super(ctx);
        owner = own;
        this.gameId = gameId;
        this.moveInfo = mi;
    }

    @Override
    public String getAdditionalParams() {
        String returnValue = "gameId=" + gameId;
        if (moveInfo != null) {
            returnValue += "&moveUnitId=" + moveInfo.getUnitId() + "&venue=" + moveInfo.getVenueId();
        }
        return returnValue;
    }

    @Override
    public String getURL() {
        return "/service/mobile/gameinfo.json";
    }

    @Override
    protected void onPostExecute(final JSONObject result) {

        GameInfoResponse gir = new Gson().fromJson(result.toString(),GameInfoResponse.class);

        owner.dataReceived(gir);

    }

}
