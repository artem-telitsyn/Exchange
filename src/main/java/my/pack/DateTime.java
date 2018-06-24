package my.pack;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    public String currentDateTime () {
        SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formate.format(date);
    }
}
