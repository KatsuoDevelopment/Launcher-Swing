package fr.katsuo.launcher;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

    public static final String name = "TestLauncher";
    public static final String version = "1.8.8";
    public static final Path gameDir = Paths.get(System.getenv("APPDATA") + "/." + name + "/");
    public static final Path nativePath = Paths.get(gameDir + "/natives");
    public static final Path assetsPath = Paths.get(gameDir + "/assets");
    public static final Path librairiesPath = Paths.get(gameDir + "/libraries");
    public static final Path ramFilePath = Paths.get(gameDir + "/ram.properties");
    public static final String assetIndex = "1.8";
}
