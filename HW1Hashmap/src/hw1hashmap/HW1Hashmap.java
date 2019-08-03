/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1hashmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author chatpisitkaewwata
 */
public class HW1Hashmap {

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/chatpisitkaewwata/NetBeansProjects/HW1Hashmap/src/hw1hashmap/sale.txt");
        String filePath = file.getPath();

        HashMap<String, List<List<String>>> map = new HashMap();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String keyGroup = "first";
        List<String> listValue = new ArrayList();
        List<List<String>> listGroupValue = new ArrayList();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",'");
            
            if (parts.length >= 2) {
                
                String key = parts[1];
                key = key.replaceAll("'", "");
                
                if (!keyGroup.equals(key) && !keyGroup.equals("first")) {
                    map.put(keyGroup, listGroupValue);
                    listGroupValue = new ArrayList();
                }

                String value = parts[2];
                value = value.replaceAll("'", "");
                value = value.replaceAll("\\)", "");
                value = value.replaceAll(";", "");
                String[] values = value.split(",");
                listValue = new ArrayList();
                for (String item: values) {
                    listValue.add(item);
                }
                listGroupValue.add(listValue);
                
                keyGroup = key;

            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        for (Map.Entry<String, List<List<String>>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<List<String>> listGroupVal = entry.getValue();
            System.out.println("");
            System.out.print(key + " : ");
            for (List<String> groupValue: listGroupVal) {
                for (String value: groupValue) {
                    System.out.print(value + " | ");
                }
            }
        }
        reader.close();
    }

}
