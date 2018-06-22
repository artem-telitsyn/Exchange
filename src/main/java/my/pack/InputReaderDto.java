package my.pack;

import java.util.List;

public class InputReaderDto {
    private String command;
    private List<String> parameter;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getParameter() {
        return parameter;
    }

    public void setParameter(List<String> parameter) {
        this.parameter = parameter;

    }
}
