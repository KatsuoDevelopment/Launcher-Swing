package fr.katsuo.launcher.ram;

import fr.katsuo.launcher.Constants;
import fr.katsuo.launcher.ui.LauncherFrame;
import fr.katsuo.launcher.utils.logger.ELogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;

public class Ram implements IRam {

    private String value = "1G";
    private final File ramFile = new File(Constants.ramFilePath.toUri());
    private final Properties properties = new Properties();
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    public void save(String value) {
        try {
            if (!ramFile.exists()) {
                outputStream = Files.newOutputStream(Constants.ramFilePath);
                properties.setProperty("ram", value + "G");
                properties.store(outputStream, null);
                LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Successfully save (" + value + "G)");
            } else {
                inputStream = Files.newInputStream(Constants.ramFilePath);
                properties.load(inputStream);
                properties.setProperty("ram", value + "G");
                outputStream = Files.newOutputStream(Constants.ramFilePath);
                properties.store(outputStream, null);
                LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Successfully save (" + value + "G)");
            }
        } catch (IOException e) {
            LauncherFrame.getInstance().getLogger().log(ELogger.INFO, e.getMessage());
        }
    }

    @Override
    public String getRam() {
        try {
            if (!ramFile.exists()) {
                this.save("1");
            } else {
                inputStream = Files.newInputStream(Constants.ramFilePath);
                properties.load(inputStream);
                properties.get("ram");
                properties.forEach((k, v) -> value = (String) v);
            }
        } catch (IOException e) {
            LauncherFrame.getInstance().getLogger().log(ELogger.ERROR, e.getMessage());
        }

        return value;
    }
}
