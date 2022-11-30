package fr.katsuo.launcher.options;

import fr.katsuo.launcher.LauncherFrame;
import fr.katsuo.launcher.utils.Constants;
import fr.katsuo.launcher.utils.logger.ELogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;

public class Options implements IOptions {

    private final File optionFile = new File(Constants.optionsFilePath.toUri());
    private final Properties properties = new Properties();
    String value;
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    public void save(EOptions options, String value) {
        switch (options) {
            case RAM_SIZE:
                if (!this.optionFile.exists()) {
                    this.write(outputStream, EOptions.RAM_SIZE, value + "G");
                    LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Successfully save (" + value + "G)");
                    break;
                } else {
                    this.read(inputStream);
                    this.write(outputStream, EOptions.RAM_SIZE, value + "G");
                    LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Successfully save (" + value + "G)");
                    break;
                }
            case WINDOW_SIZE:
                this.read(inputStream);
                this.write(outputStream, EOptions.WINDOW_SIZE, value);
                LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Successfully save (" + value + ")");
                break;

            case FULLSCREEN:
                this.read(inputStream);
                this.write(outputStream, EOptions.FULLSCREEN, value);
                LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Successfully save (" + value + ")");
                break;
        }
    }

    @Override
    public Properties read(InputStream inputStream) {
        try {
            inputStream = Files.newInputStream(Constants.optionsFilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            LauncherFrame.getInstance().getLogger().log(ELogger.INFO, e.getMessage());
        }

        return properties;
    }

    @Override
    public Properties write(OutputStream outputStream, EOptions options, String value) {
        try {
            properties.setProperty(String.valueOf(options), value);
            outputStream = Files.newOutputStream(Constants.optionsFilePath);
            properties.store(outputStream, null);
        } catch (IOException e) {
            LauncherFrame.getInstance().getLogger().log(ELogger.INFO, e.getMessage());
        }
        return properties;
    }

    @Override
    public String getOptions(EOptions options) {
        if (!this.optionFile.exists()) {
            this.save(EOptions.RAM_SIZE, "1");
            this.save(EOptions.WINDOW_SIZE, "1920X1280");
            this.save(EOptions.FULLSCREEN, "false");
        }
        switch (options) {
            case RAM_SIZE:
            case WINDOW_SIZE:
            case FULLSCREEN:
                this.getInformationInFile(options);
                break;
        }

        return value;
    }


    private void getInformationInFile(EOptions options) {
        read(inputStream);
        String valueFromProperties = properties.getProperty(options.name());
        switch (options) {
            case RAM_SIZE:
                value = valueFromProperties.replace("G", "");
                break;
            case WINDOW_SIZE:
            case FULLSCREEN:
                value = valueFromProperties;
                break;
        }


    }
}
