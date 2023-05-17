package com.example.kyrsach.Controller.Terminal;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TerminalThread extends Thread {
    public TerminalThread(){

    }
    public ListView<String> ProcessList(ObservableList<String> list, ListView<String> ListTerminalView, String textComandTerminal){
        try {
            list.clear();
            String process;
            Process p = Runtime.getRuntime().exec(textComandTerminal);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            process = input.readLine();
            while ((process = input.readLine()) != null) {
                list.add(process);// Выписывает результат по строчно
            }
            input.close();
            ListTerminalView.setItems(list);
            return ListTerminalView;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return ListTerminalView;
    }
    @Override
    public void run() {
        super.run();
    }
}
