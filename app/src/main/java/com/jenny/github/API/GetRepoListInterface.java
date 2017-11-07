package com.jenny.github.API;

import com.jenny.github.Models.Repo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jenny on 10/21/17.
 */

public interface GetRepoListInterface {
    void onFinished(List<Repo> result) throws IOException;
}