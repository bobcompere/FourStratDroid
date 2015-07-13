package com.fourstrategery.fourstratdroid.task;
import android.content.Intent;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fourstrategery.fourstratdroid.MainActivity;
import com.fourstrategery.fourstratdroid.R;
import com.fourstrategery.fourstratdroid.SaveSharedPreference;
import com.fourstrategery.fourstratdroid.util.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by REC on 7/12/2015.
 */
public abstract class FourStratTask  extends AsyncTask<Void, Void, JSONObject> {

        private boolean requestComplete;
        private JSONObject returnVal;

        public abstract String getURL();
        public abstract String getParams();

        @Override
        protected JSONObject doInBackground(Void... params) {

            RequestQueue rQueue = VolleyUtil.getRequestQueue();

            JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
                    "https://services-fourstrategery.rhcloud.com/cloud/" + getURL() + "?" + getParams(),
                    // new JSONObject(queryParams),
                    null,
                    createMyReqSuccessListener(),
                    createMyReqErrorListener());

            rQueue.add(myReq);

            while (!requestComplete) {
                try {
                    Thread.sleep(20);
                }
                catch (InterruptedException ioe) {}
            }

            return returnVal;
        }
        private Response.Listener<JSONObject> createMyReqSuccessListener() {
            return new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    returnVal = response;
                    requestComplete = true;
                }
            };
        }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                requestComplete = true;
            }

        };
    }


}
