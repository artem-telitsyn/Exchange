package my.pack;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader {
    
    // Метод занимается тем, что читает строку и делает из нее команду. Поэтому метод лучше назвать просто read, а 
    // InputReaderDto переименовать в MenuCommand
    public InputReaderDto inputReader(BufferedReader reader) {
        InputReaderDto inputReaderDto = new InputReaderDto();
        String allString;
        String[] inputConsole;
        try {
            allString = reader.readLine();
            inputConsole = allString.split(" ");
            // по классике для имзенения dto-шек используются set'еры
            inputReaderDto.command = inputConsole[0];
            // на практике с примитивными массивами мы работаем крайне редко, чаще используем коллекции. Тут подойдет List
            inputReaderDto.parameter = new String[inputConsole.length];
            for (int i = 1; i < inputConsole.length; i++) {
                inputReaderDto.parameter[i - 1] = inputConsole[i];
            }
            return inputReaderDto;
        } catch (IOException e) {
            e.printStackTrace();
            return inputReaderDto;
        }
    }
}
