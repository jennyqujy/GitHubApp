package com.jenny.github.API;

import com.jenny.github.Models.User;

import java.io.IOException;

/**
 * Created by Jenny on 10/19/17.
 */
public interface GetUserInterface {
    void onFinished(User user) throws IOException;
}



