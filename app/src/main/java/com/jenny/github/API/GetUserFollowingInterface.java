package com.jenny.github.API;

import com.jenny.github.Models.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jenny on 10/21/17.
 */

public interface GetUserFollowingInterface {
    void onFinished(List<User> result) throws IOException;
}
