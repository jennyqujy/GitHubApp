package com.jenny.github.API;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jenny on 11/6/17.
 */

public interface GetSearchedInterface {
    void onFinished(List<String> list) throws IOException;
}
