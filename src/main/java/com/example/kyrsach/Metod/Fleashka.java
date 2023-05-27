package com.example.kyrsach.Metod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fleashka {
    public static void main(String[] args) {
        boolean flashDriveInserted = isFlashDriveInserted();

        if (flashDriveInserted) {
            System.out.println("Flash drive inserted");
        } else {
            System.out.println("Flash drive removed");
        }
    }

    private static boolean isFlashDriveInserted() {
        try {
            Process process = Runtime.getRuntime().exec("lsblk -o NAME,TYPE");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("disk")) {
                    String[] tokens = line.split("\\s+");
                    String deviceName = tokens[0];

                    if (deviceName.startsWith("sd") && !deviceName.endsWith("1")) {
                        return true;
                    }
                }
            }

            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}

