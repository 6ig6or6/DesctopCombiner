package com.example.desctopapp.model;

import java.io.*;

public class Divider {
    public void divide(File file, Size size) throws IOException { //"C:\\Users\\PC\\Desktop\\124\\1234.mp4"
        int partCounter = 1; //the first part will be .part1
        int megabytes = switch (size) {
            case MB1 -> 1024;
            case MB10 -> 10240;
            case MB50 -> 50240;
        };
        byte[] buffer = new byte[megabytes * 1024]; // 10MB

        String fileName = file.getName();

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each part of data into new file .partN
                String filePartName = String.format("%s.%s%d", fileName, "part", partCounter++);
                File newFile = new File(file.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
    }
}
