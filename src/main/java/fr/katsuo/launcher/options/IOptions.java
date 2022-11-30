package fr.katsuo.launcher.options;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public interface IOptions {

    void save(EOptions options, String value);

    Properties read(InputStream outputStream);

    Properties write(OutputStream inputStream, EOptions options, String value);

    String getOptions(EOptions options);
}
