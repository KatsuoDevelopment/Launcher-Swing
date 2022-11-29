package fr.katsuo.launcher.utils.logger;

import fr.katsuo.launcher.utils.logger.util.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger implements ILogger {

    private final Date date = new Date();
    private final String format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(date);
    private final String loggerName;


    public Logger(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public void log(ELogger logger, String text) {
        switch (logger) {
            case INFO:
            case DEBUG:
                System.out.printf("[" + format + "] " + "[" + loggerName + "] " + "[" + logger.name() + "]: " + text + this.returnLine());
                break;
            case ERROR:
                System.out.printf(Color.RED + "[" + format + "] " + "[" + loggerName + "] " + "[" + logger.name() + "]: " + text + this.returnLine());
                break;
            case SUCCESS:
                System.out.printf(Color.GREEN + "[" + format + "] " + "[" + loggerName + "] " + "[" + logger.name() + "]: " + text + this.returnLine());
                break;
            case WARNING:
                System.out.printf(Color.YELLOW + "[" + format + "] " + "[" + loggerName + "] " + "[" + logger.name() + "]: " + text + this.returnLine());
                break;
        }
    }

    public String returnLine() {
        return Color.RESET + "\n";
    }
}
