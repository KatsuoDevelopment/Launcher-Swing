package fr.katsuo.launcher.launch;

import fr.katsuo.launcher.LauncherFrame;
import fr.katsuo.launcher.options.EOptions;
import fr.katsuo.launcher.options.Options;
import fr.katsuo.launcher.utils.Constants;
import fr.katsuo.launcher.utils.logger.ELogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private final String username;
    private final String uuid;
    private final String accessToken;
    private final Options ram = new Options();

    public Arguments(String username, String uuid, String accessToken) {
        this.username = username;
        this.uuid = uuid;
        this.accessToken = accessToken;
    }

    public List<String> getVMArguments() {
        List<String> argsVM = new ArrayList<String>();
        argsVM.add("-Djava.library.path=" + Constants.nativePath);
        argsVM.add("-Xmx" + this.ram.getOptions(EOptions.RAM_SIZE) + "G");
        argsVM.add("-cp");
        argsVM.add(getClasspath());
        argsVM.add("net.minecraft.client.main.Main");

        return argsVM;
    }

    public List<String> getArguments() {
        List<String> args = new ArrayList<String>();
        args.add("--username=");
        args.add(this.username);
        args.add("--width");
        args.add(this.getSize(EOptions.WITDH));
        args.add("--height");
        args.add(this.getSize(EOptions.WITDH));
        args.add("--fullscreen");
        args.add("false");
        args.add("--accessToken");
        args.add(this.accessToken);
        args.add("--version");
        args.add(Constants.version);
        args.add("--gameDir");
        args.add(String.valueOf(Constants.gameDir));
        args.add("--assetsDir");
        args.add(String.valueOf(Constants.assetsPath));
        args.add("--assetIndex");
        args.add(Constants.assetIndex);
        args.add("--uuid");
        args.add(this.uuid);
        args.add("--userProperties");
        args.add("{}");
        args.add("--userType");
        args.add("legacy");

        return args;
    }

    public String getClasspath() {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            Files.walk(Constants.librairiesPath)
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        stringBuilder.append(path.toString());
                        stringBuilder.append(File.pathSeparator);
                    });
        } catch (IOException e) {
            LauncherFrame.getInstance().getLogger().log(ELogger.ERROR, e.getMessage());
        }
        stringBuilder.append(Constants.gameDir.resolve("client.jar"));
        return stringBuilder.toString();
    }

    private String getSize(EOptions sizeFrame) {
        String size = this.ram.getOptions(EOptions.WINDOW_SIZE);
        System.out.println(size);
        String[] parts = size.split("X");

        switch (sizeFrame) {
            case WITDH:
                return parts[0];
            case HEIGHT:
                return parts[1];
        }
        return null;
    }


}
