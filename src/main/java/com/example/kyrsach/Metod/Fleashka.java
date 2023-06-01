package com.example.kyrsach.Metod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fleashka {
    public static void main(String[] args) throws IOException {
            String process;
            Process p = Runtime.getRuntime().exec("ps -aef | grep \"javaagent\" | grep \"MainApplication\"");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            process = input.readLine();
            while ((process = input.readLine()) != null) {
                byte[] arr = process.getBytes("UTF-8");
                process = new String (arr, "utf8");
                System.out.println(process);
            }
            input.close();

        }


    private static boolean isFlashDriveInserted() {
        try {
            Process process = Runtime.getRuntime().exec("ls -l /dev/disk/by-id/usb*");
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

