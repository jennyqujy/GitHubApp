package com.jenny.github.API;

import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Created by Jenny on 10/19/17.
 */

public interface  GetPhotoInterface{
    void onFinished(Bitmap result) throws IOException;
}
