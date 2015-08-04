package com.fourstrategery.fourstratdroid.model;

import java.util.Date;

/**
 * Created by REC on 8/1/2015.
 */
public class GameActivity {
    private String message;
    private long createdOn;

    public long getCreatedOn() {
        return createdOn;
    }


//    public void setCreatedOnDate(Date createdOnDate) {
//        this.createdOnDate = createdOnDate;
//    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
