package com.fourstrategery.fourstratdroid.task;

import android.content.Context;

import com.fourstrategery.fourstratdroid.GameListFragment;

import org.json.JSONObject;

/**
 * Created by REC on 7/12/2015.
 */
public class GameListTask extends FourStratAuthorizedTask {

    private GameListFragment owner;

    public GameListTask(Context ctx, GameListFragment own) {
        super(ctx);
        owner = own;
    }

    @Override
    public String getAdditionalParams() {
        return "status=0";
    }

    @Override
    public String getURL() {
        return "/service/mobile/gamelist.json";
    }



    @Override
    protected void onPostExecute(final JSONObject result) {
        owner.listReceived(result);

    }

    @Override
    protected void onCancelled() {
        owner.listCanceled();

    }
}
