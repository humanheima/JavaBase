package com.hm.clone;

import java.io.*;
import java.rmi.server.ExportException;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class ObjCloner {

    public static <T> T cloneObj(T obj) {
        T retVal = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            retVal = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
