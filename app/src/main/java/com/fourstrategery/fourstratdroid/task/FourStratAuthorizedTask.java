package com.fourstrategery.fourstratdroid.task;

import android.content.Context;

import com.fourstrategery.fourstratdroid.SaveSharedPreference;

/**
 * Created by REC on 7/12/2015.
 */
public abstract class FourStratAuthorizedTask  extends FourStratTask {

    private int playerId;
    private String authenticationToken;

    public FourStratAuthorizedTask(Context ctx) {
        playerId = SaveSharedPreference.getUser(ctx);
        authenticationToken = SaveSharedPreference.getAutheticationToken(ctx);
    }

    public abstract String getAdditionalParams();

    @Override
    public String getParams()  {
        return "playerId=" +playerId  + "&authToken=" + authenticationToken + "&" + getAdditionalParams();
    }
}
