package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {
    public Profile getDataFromFile(File file) {
        Map<String, String> keyToValueMap = new HashMap<>();
        try (FileInputStream inputStream = new FileInputStream(file.getAbsolutePath())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    keyToValueMap.put(key, value);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("File not found: " + e);
        }

        return new Profile(
                keyToValueMap.get("Name"),
                Integer.parseInt(keyToValueMap.get("Age")),
                keyToValueMap.get("Email"),
                Long.parseLong(keyToValueMap.get("Phone"))
        );
    }
}
