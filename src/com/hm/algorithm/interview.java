package com.hm.algorithm;

import java.util.HashMap;
import java.util.LinkedList;

public class interview {
    public int transfor(String str) throws Exception {
        boolean result=true;
        if (str==null)
            result=false;
        if(str.length()==0)
            result=false;
        int fu=0;
        int i=0;
        if(str.charAt(0)=='-')
        {fu=-1;
        i=1;
        }

        if(str.charAt(0)=='+'||(str.charAt(0)>='0'&&str.charAt(0)<='9'))
        {fu=1;
            System.out.println(fu);
        if(str.charAt(0)=='+')
            i=1;
        }

        int sum=0;

        for(;i<str.length();i++){

            if(str.charAt(i)>'9'||str.charAt(i)<'0')
                result=false;
            else
                sum=sum*10+str.charAt(i)-'0';
        }
        if(sum<0)
            result=false;
        sum=fu*sum;

        if(result==false) {
            throw new Exception("error");

        }
        return sum;
    }
    public static void main(String args[]) throws Exception {
        LinkedList l=new LinkedList();
        HashMap map=new HashMap();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("1", "value3");
        System.out.println("通过Map.entrySet遍历key和value");
        map.forEach((key, value) -> System.out.println("key= " + key + " and value= " + value));

        l.offer("ni");
        l.push("wo");

        System.out.println(l);



    }
}
