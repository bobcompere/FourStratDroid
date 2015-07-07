package com.fourstrategery.fourstratdroid.util;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by REC on 7/6/2015.
 */
public class VolleyUtil {

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

//        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
//                .getMemoryClass();
//        // Use 1/8th of the available memory for this memory cache.
//        int cacheSize = 1024 * 1024 * memClass / 8;
//        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));

        //https://github.com/ogrebgr/android_volley_examples/blob/master/src/com/github/volley_examples/toolbox/BitmapLruCache.java
    }


    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

//    public static ImageLoader getImageLoader() {
//        if (mImageLoader != null) {
//            return mImageLoader;
//        } else {
//            throw new IllegalStateException("ImageLoader not initialized");
//        }
//    }
}
