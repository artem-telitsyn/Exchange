package my.pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InputReader {

    public InputReaderDto read(BufferedReader reader) {
        InputReaderDto inputReaderDto = new InputReaderDto();
        String allString;
        List<String> inputConsole;
        try {
            allString = reader.readLine();
            inputConsole = Arrays.asList(allString.split(" "));
            inputReaderDto.setCommand(inputConsole.get(0));
            inputReaderDto.setParameter(inputConsole);
            return inputReaderDto;
        } catch (IOException e) {
            e.printStackTrace();
            return inputReaderDto;
        }
    }
}
