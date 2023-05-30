package com.example.kyrsach.Metod;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Date;

import static java.nio.file.StandardOpenOption.*;

public class LogFile {
    public void writeFile(String valLog){
        Date date = new Date();
        String text = "Время действия: "+ date +" Само действие: "+ valLog + "\n";

        // `true` добавит новые данные
        try(FileWriter fw = new FileWriter(new File("Kyrsovay/System/Log/LogFile.txt"), true))
        {
            fw.write(text);
            System.out.println("Successfully written data to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}