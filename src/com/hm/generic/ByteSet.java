package com.hm.generic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class ByteSet {

    Byte[] possibles = {1, 2, 3, 4};

    Set<Byte> mySet = new HashSet<>(Arrays.asList(possibles));

    //Set<Byte>mySet2=new HashSet<>(Arrays.<Byte>asList(1,2,3,4));

}
