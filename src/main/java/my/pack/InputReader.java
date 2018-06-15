package my.pack;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader {

    public InputReaderDto read(BufferedReader reader) {
        InputReaderDto inputReaderDto = new InputReaderDto();
        String allString;
        String[] inputConsole;
        try {
            allString = reader.readLine();
            inputConsole = allString.split(" ");
            inputReaderDto.setCommand(inputConsole[0]);
            inputReaderDto.setParameter(new String[inputConsole.length]);
            for (int i = 1; i < inputConsole.length; i++) {
                inputReaderDto.getParameter()[i - 1] = inputConsole[i];
            }
            return inputReaderDto;
        } catch (IOException e) {
            e.printStackTrace();
            return inputReaderDto;
        }
    }
}
