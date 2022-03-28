package com.hm.picture_of_patten.adapter.A2;

import java.io.IOException;

/**
 * Created by dumingwei on 2022/3/27.
 * <p>
 * Desc:
 */
public interface FileIO {

    public void readFromFile(String filename) throws IOException;

    public void writeToFile(String filename) throws IOException;

    public void setValue(String key, String value);

    public String getValue(String key);
}
