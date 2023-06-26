package com.choucair.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeerTxt {

    public static List<String> readTextFile(String fileName) {
        List<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String element = "";
            while (line != null) {
                element += line;
                if (line.contains("}")) {
                    lines.add(element);
                    element = "";
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
        return lines;
    }
}
