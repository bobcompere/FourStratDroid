package com.fourstrategery.fourstratdroid.task;

import com.fourstrategery.fourstratdroid.LoginActivity;
import com.fourstrategery.fourstratdroid.R;

import org.json.JSONObject;

/**
 * Created by REC on 7/12/2015.
 */
public class UserLoginTask extends FourStratTask {


    private final String mUserName;
    private final String mPassword;
    private final LoginActivity owner;

    public UserLoginTask(String userName, String password, LoginActivity owner) {
        mUserName = userName;
        mPassword = password;
        this.owner = owner;
    }


    @Override
    public String getURL() {
        return "service/mobile/auth.json";
    }

    @Override
    public String getParams() {
        return "name=" + mUserName + "&password=" + mPassword;
    }


    @Override
    protected void onPostExecute(final JSONObject result) {
        owner.finishLogin(result);

    }

    @Override
    protected void onCancelled() {
        owner.loginCanceled();

    }
}

