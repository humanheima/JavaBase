package com.hm.picture_of_patten.adapter.A2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        FileIO f = new FileProperties();
        try {
            f.readFromFile("src/com/hm/picture_of_patten/adapter/A2/file.txt");
            f.setValue("year", "2004");
            f.setValue("month", "4");
            f.setValue("day", "21");
            f.writeToFile("src/com/hm/picture_of_patten/adapter/A2/newfile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
